/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.programme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 *
 * @author dosdiaopfhj
 */
public class ProgrammeGetter {

    /**
     * ストアドプロシージャのテンプレート 番組情報の取得用 指定されたチャンネルの、指定時刻に始まる番組情報を取得する。 ?1個目:チャンネルID
     * String ?2個目:物理チャンネル番号 int ?3個目:指定時刻 Timestamp
     */
    private static final String GET_PROGRAMME_FUZZY = "{CALL GET_PROGRAMME_FUZZY(?,?,?) }";

    private final QueryRunner runner = new QueryRunner();
    private final Connection con;

    public ProgrammeGetter(Connection con) {
        this.con = con;
    }

    /**
     * DBから指定チャンネル、指定時刻に始まる番組の情報を1件検索する。
     * 仕様上、同じ物理チャンネル番号、同じ放送開始時刻の番組が2件以上表示されることはない。
     * (同じ物理チャンネル、同じ放送開始時刻の番組は、チャンネルIDのみが異なっているものしか存在しない。)
     *
     * @param Channel_id チャンネルID
     * @param Channel_no 物理チャンネル番号
     * @param StartDatetime 放送開始時刻
     * @return 当該番組1件分の情報(物理チャンネル番号,番組ID,番組名,放送開始時刻,放送終了時刻)
     * @throws java.sql.SQLException
     */
    public synchronized ProgrammeChannelNo_ReadOnly getProgramme(String Channel_id, int Channel_no, Timestamp StartDatetime) throws SQLException {
//
//        try (CallableStatement ps = con.prepareCall(ProgrammeGetter.GET_PROGRAMME_FUZZY)) {
//            //名前つきパラメータはサポートしていないらしい。
//            ps.setString(1, Channel_id);//チャンネルID
//            ps.setInt(2, Channel_no);//物理チャンネル番号
//            ps.setTimestamp(3, StartDatetime);//放送開始時刻
//
//            // クエリーを実行して結果セットを取得
//            ResultSet rs = ps.executeQuery();
//            return new BeanHandler<>(ProgrammeChannelNo.class).handle(rs);
//        }
        return runner.query(con, ProgrammeGetter.GET_PROGRAMME_FUZZY, new BeanHandler<ProgrammeChannelNo>(ProgrammeChannelNo.class), Channel_id, Channel_no, StartDatetime);
    }

}
