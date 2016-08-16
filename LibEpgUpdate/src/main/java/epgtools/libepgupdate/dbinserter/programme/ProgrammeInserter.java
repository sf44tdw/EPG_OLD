/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dbinserter.programme;

import DataExtractor.Programme.Programme;
import epgtools.libepgupdate.dbinserter.AbstractInserter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.Connection;

/**
 *
 * @author dosdiaopfhj
 */
public class ProgrammeInserter extends AbstractInserter<Programme> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    /**
     * ストアドプロシージャのテンプレート 番組情報テーブルへの追加用
     * パラメータ
     * 1:String     チャンネルID
     * 2:Int        番組ID
     * 3:String     番組名
     * 4:Timestamp 放送開始時刻
     * 5:Timestamp 放送終了時刻
     */
    private static final String C_INSERT_PROGRAMM_S = "{CALL INSERT_PROGRAMME(?,?,?,?,?) }";

    public ProgrammeInserter(Connection con, List<Programme> data) {
        super(con, data);
    }

    @Override
    protected final synchronized void processRecord(Programme record, Connection con) throws SQLException {
        log.log(Level.INFO, "下記の番組情報を追加。");
        log.log(Level.INFO, record.toString());                   
        CallableStatement cstmt;
        cstmt = con.prepareCall(ProgrammeInserter.C_INSERT_PROGRAMM_S);
        cstmt.setString(1, record.getId());
        cstmt.setInt(2, record.getEventId());
        cstmt.setString(3, record.getTitle());
        cstmt.setTimestamp(4, record.getStartDatetime());
        cstmt.setTimestamp(5, record.getStopDatetime());
        cstmt.executeUpdate();
        log.log(Level.INFO, "番組情報の追加を完了。");
    }

}
