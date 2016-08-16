/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.programmegetter;

import epgtools.consts.ConfigConsts;
import epgtools.consts.DbConsts;
import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * チャンネル番号と放送開始時刻(これが実行された時刻の1分後までに始まる番組)から、番組のタイトルを返す。タイトルが複数見つかった場合は、最初のものを返す。
 *
 * @author dosdiaopfhj
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final Logger log = LoggerConfigurator.getCallerLogger();
    public static final String DT = "yyyyMMddHHmmss";

    private static final File LOGGER_PROPERTYDIR = new File("./loggerproperties");

    private static final File LOGGER_PROPERTYFILE = new File(LOGGER_PROPERTYDIR, "logger.properties");

    private static final boolean x = false;

    public static void main(String[] args) {

        try {

            String url = "";
            String user = "";
            String password = "";
            int channel_no = 0;
            ;

            File propertyFile = new File(args[0]);
            if (!propertyFile.isFile()) {
                log.log(Level.SEVERE, "設定ファイルを開けませんでした。強制終了します。");
                log.log(Level.SEVERE, "第1引数は設定ファイル。第2引数は設定ファイルの文字コード。第3引数は物理チャンネル番号。");
                log.log(Level.SEVERE, "開こうとした設定ファイル={0}", args[0]);
                System.exit(-1);
            }
            //設定ファイルを読み込む。設定ファイルディレクトリにProgrammeGetter.propertiesというファイルが存在することを前提とする。

            //自動クローズしない場合があるので、ネストせず個別に変数定義する。
            try (
                    FileInputStream fis = new FileInputStream(propertyFile);
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName(args[1]));
                    Reader R = new BufferedReader(isr);) {
                Properties conf = new Properties();
                conf.load(R);
                url = conf.getProperty(ConfigConsts.DB_URL);
                user = conf.getProperty(ConfigConsts.DB_USER);
                password = conf.getProperty(ConfigConsts.DB_PASSWORD);
            } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                log.log(Level.SEVERE, "設定ファイルの文字コードが相違しているか、ファイルが見つかりませんでした。", ex);
            } catch (IOException ex) {
                log.log(Level.SEVERE, "設定ファイルの読み込み中に問題が発生しました。", ex);
            }

            //ログ設定ファイルを読み込む。設定ファイルディレクトリにlogger.propertiesというファイルが存在することを前提とする。
            LoggerConfigurator.setlogproperties(LOGGER_PROPERTYFILE);

            channel_no = Integer.parseInt(args[2]);

            String s = "";
            try (JDBCAccessor Accesser = JDBCAccessor.getInstance()) {
                Accesser.connect(url, user, password);
                java.sql.Connection con = Accesser.getCon();

                //フォーマットパターンを指定
                SimpleDateFormat sdf = new SimpleDateFormat(DT);

                //現在日時を取得する
                Calendar c = Calendar.getInstance();

                // ステートメントを作成
                CallableStatement cstmt = con.prepareCall(DbConsts.GET_TITLE_S);
                cstmt.setInt(DbConsts.CHANNEL_NO, channel_no);
                cstmt.setTimestamp(DbConsts.START_DATETIME, new java.sql.Timestamp(c.getTimeInMillis()));
                // 問合せの実行
                ResultSet rset = cstmt.executeQuery();
                // 問合せ結果の表示
                while (rset.next()) {
                    // 列番号による指定
                    s = rset.getString(1);
                    System.out.println(s);
                    break;
                }
                // 結果セットをクローズ
                rset.close();
                // ステートメントをクローズ
                cstmt.close();
                log.log(Level.INFO, "取得された番組名:{0}", s);
            } catch (SQLException ex) {
                log.log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

}
