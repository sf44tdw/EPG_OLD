/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.channel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanMapHandler;

/**
 * DBから視聴可能なチャンネルの一覧を取得する。
 *
 * @author dosdiaopfhj
 */
public class ChannelGetter {

    private final QueryRunner runner = new QueryRunner();
    private final Connection con;

    /**
     * SQLのテンプレート 視聴可能チャンネル一覧取得用 地上波の場合、複数のチャンネルIDが１つの物理チャンネルに紐付けされていることがあるので、
     * それに当てはまる物理チャンネルについては、チャンネルIDが最大の項目のみをチャンネルテーブルから取得するビューを使用する。
     */
    public static final String GET_CHANNELS = "SELECT * FROM useablechannels ORDER BY channel_no";

    /**
     * @param con DBへの接続。
     */
    public ChannelGetter(Connection con) {
        this.con = con;
    }

    /**
     * DBから視聴可能なチャンネルの一覧を取得する。
     *
     * @return 視聴可能なチャンネルの一覧(変更不可) キーは物理チャンネル番号。
     * @throws java.sql.SQLException
     */
    public synchronized Map<Integer, Useablechannels_ReadOnly> getChannels() throws SQLException {
        //取得
        Map<Integer, Useablechannels> Channels;
        Channels = Collections.synchronizedMap(runner.query(con, ChannelGetter.GET_CHANNELS, new BeanMapHandler<Integer, Useablechannels>(Useablechannels.class, "channel_no")));
        Map<Integer, Useablechannels_ReadOnly> Channels_Ro;
        Channels_Ro = Collections.synchronizedMap(new TreeMap<Integer, Useablechannels_ReadOnly>());
        Channels_Ro.putAll(Channels);
        return Collections.unmodifiableMap(Channels_Ro);
    }

}
