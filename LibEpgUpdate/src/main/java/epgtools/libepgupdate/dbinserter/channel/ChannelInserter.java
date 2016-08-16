/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBInserter.Channel;

import DataExtractor.Channel.Channel;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.libepgupdate.dbinserter.AbstractInserter;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.Connection;

/**
 *
 * @author dosdiaopfhj
 */
public class ChannelInserter extends AbstractInserter<Channel> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    /**
     * ストアドプロシージャのテンプレート チャンネルテーブルへの追加用 
     * パラメータ 
     * 1:String チャンネルID 
     * 2:Int 物理チャンネル番号
     * 3:String 放送局名
     */
    private static final String C_INSERT_CHANNEL_S = "{CALL INSERT_CHANNEL(?,?,?)}";

    public ChannelInserter(Connection con, List<Channel> data) {
        super(con, data);
    }

    @Override
    protected final synchronized void processRecord(Channel record, Connection con) throws SQLException {
        log.log(Level.INFO, "下記のチャンネル情報を追加。");
        log.log(Level.INFO, record.toString());
        CallableStatement cstmt;
        cstmt = con.prepareCall(ChannelInserter.C_INSERT_CHANNEL_S);
        cstmt.setString(1, record.getId());
        cstmt.setInt(2, record.getPhysicalChannelNumber());
        cstmt.setString(3, record.getBroadcastingStationName());
        cstmt.executeUpdate();
        log.log(Level.INFO, "チャンネルの追加を完了。");
    }

}
