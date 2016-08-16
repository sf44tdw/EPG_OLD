/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.updator;

import DBInserter.Channel.ChannelInserter;
import DataExtractor.Channel.Channel;
import DataExtractor.Programme.Programme;
import epgtools.libepgupdate.dataextractor.channel.AllChannelDataExtractor;
import epgtools.libepgupdate.dataextractor.programme.AllProgrammeDataExtractor;
import epgtools.libepgupdate.dbinserter.programme.ProgrammeInserter;
import epgtools.libepgupdate.listmaker.EpgListMaker;
import epgtools.libepgupdate.updator.config.Config;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 * DBのアップデートを行う。
 *
 * @author normal
 */
public final class Updater {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    private enum SQL_STATEMENTS {

        /**
         * チャンネルテーブルの内容を全て削除するSQL
         */
        DELETE_ALL_CHANNEL_TABLE("DELETE FROM  channel ;"),
        /**
         * 番組テーブルの内容を全て削除するSQL
         */
        DELETE_ALL_PROGRAMME_TABLE("DELETE FROM programme;"),
        /**
         * 有料局テーブルの内容を全て削除するSQL
         */
        DELETE_ALL_PAIDBROADCASTING_TABLE("DELETE FROM paidBroadcasting;"),
        /**
         * ストアドプロシージャのテンプレート 有料局テーブル(一時)への追加用 パラメータ 1:int 物理チャンネル番号
         */
        C_INSERT_TEMP_PAIDBROADCASTING("{CALL INSERT_temp_PaidBroadcasting(?) }"),
        /**
         * ストアドプロシージャのテンプレート 有料局テーブル(本番)への追加用
         */
        C_INSERT_PAIDBROADCASTING("{CALL INSERT_PAIDBROADCASTING() }"),
        /**
         * :有料局テーブル(一時)の内容を消去する。
         */
        DELETE_ALL_TEMP_PAID_CHANNEL_NO("DELETE FROM temp_PaidBroadcasting;"),;

        private final String sqlStatement;

        SQL_STATEMENTS(String sqlStatement) {
            this.sqlStatement = sqlStatement;
        }

        /**
         * @return SQL文
         */
        public String getSqlStatement() {
            return sqlStatement;
        }

    }

    private final Config cfg;

    /**
     * @param cfg 各種設定。
     */
    public Updater(Config cfg) {
        if (cfg != null) {
            this.cfg = cfg;
        } else {
            throw new NullPointerException("設定がありません。");
        }

    }

    private synchronized List<Document> getEPGs() throws NullPointerException {
        EpgListMaker m = new EpgListMaker(cfg.getXMLDirectory(), cfg.getXMLFileCharCode());
        List<Document> EPGs = m.seek();
        if ((EPGs == null) || (EPGs.size() <= 0)) {
            throw new NullPointerException("EPG XMLファイルが見つかりません。");
        }
        return EPGs;
    }

    /**
     * チャンネル情報を取得する。
     */
    private synchronized List<Channel> extractChannels(List<Document> EPGs) throws NullPointerException {
        log.log(Level.INFO, "チャンネルの抽出を開始、");
        AllChannelDataExtractor chproc = new AllChannelDataExtractor(EPGs);
        List<Channel> chs = chproc.getAllEPGRecords();
        log.log(Level.INFO, "チャンネルの抽出を完了。");
        if ((chs == null) || (chs.size() <= 0)) {
            throw new NullPointerException("チャンネル情報がありません。");
        }
        return Collections.unmodifiableList(chs);
    }

    /**
     * 番組情報を取得する。
     */
    private synchronized List<Programme> extractProgrammes(List<Document> EPGs) throws NullPointerException {
        log.log(Level.INFO, "番組の抽出を開始。");
        AllProgrammeDataExtractor prproc = new AllProgrammeDataExtractor(EPGs);
        List<Programme> prs = prproc.getAllEPGRecords();
        log.log(Level.INFO, "番組の抽出を完了。");
        if ((prs == null) || (prs.size() <= 0)) {
            throw new NullPointerException("番組情報がありません。");
        }
        return Collections.unmodifiableList(prs);
    }

    /**
     * データベースを更新する。
     *
     * @return 成功した場合はtrue
     * @throws java.sql.SQLException
     */
    public synchronized boolean update() throws NullPointerException, SQLException {

        List<Document> EPGs = this.getEPGs();

        List<Channel> chs = this.extractChannels(EPGs);

        List<Programme> prs = this.extractProgrammes(EPGs);

        try (java.sql.Connection con = cfg.getConention()) {
            con.setAutoCommit(false);

            //テーブルの内容を消去
            //有料局
            log.log(Level.INFO, "有料局テーブル消去");
            Statement stmt_del_pb = con.createStatement();
            stmt_del_pb.executeUpdate(SQL_STATEMENTS.DELETE_ALL_PAIDBROADCASTING_TABLE.getSqlStatement());

            //番組
            log.log(Level.INFO, "番組テーブル消去");
            Statement stmt_del_pr = con.createStatement();
            stmt_del_pr.executeUpdate(SQL_STATEMENTS.DELETE_ALL_PROGRAMME_TABLE.getSqlStatement());

            //チャンネル
            log.log(Level.INFO, "チャンネルテーブル消去");
            Statement stmt_dl_ch = con.createStatement();
            stmt_dl_ch.executeUpdate(SQL_STATEMENTS.DELETE_ALL_CHANNEL_TABLE.getSqlStatement());

            log.log(Level.INFO, "チャンネルの登録を開始、");
            new ChannelInserter(con, chs).execute();
            log.log(Level.INFO, "チャンネルの登録を完了。");

            log.log(Level.INFO, "番組の登録を開始。");
            new ProgrammeInserter(con, prs).execute();
            log.log(Level.INFO, "番組の登録を完了。");

            //念のため、一時テーブルの内容を消去する。
            log.log(Level.INFO, "有料局テーブル(一時)の内容を消去。");
            Statement stmt_del_t_pb = con.createStatement();
            stmt_del_t_pb.executeUpdate(SQL_STATEMENTS.DELETE_ALL_TEMP_PAID_CHANNEL_NO.getSqlStatement());

            for (Integer ch : cfg.getPaidBroadcastings()) {	//JDK1.5以降
                log.log(Level.INFO, "Channel:{0}", ch);
                log.log(Level.INFO, "有料局テーブル(一時)が空になっていることを前提として、以降の処理を実施。");
                CallableStatement stmt_temp_PaydChannels = con.prepareCall(SQL_STATEMENTS.C_INSERT_TEMP_PAIDBROADCASTING.getSqlStatement());
                stmt_temp_PaydChannels.setInt(1, ch);
                stmt_temp_PaydChannels.executeUpdate();
            }
            log.log(Level.INFO, "有料局テーブル(一時)の内容を有料局テーブルに転記");
            CallableStatement stmt_PaydChannels = con.prepareCall(SQL_STATEMENTS.C_INSERT_PAIDBROADCASTING.getSqlStatement());
            stmt_PaydChannels.executeUpdate();

            log.log(Level.INFO, "データベース更新完了。");

            con.commit();

            log.log(Level.INFO, "データベースコミット完了。");

            con.setAutoCommit(true);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "データベース操作中に問題が発生しました。", ex);
            throw ex;
        }
        return true;
    }
}
