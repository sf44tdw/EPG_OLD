/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.programme;

import java.sql.Timestamp;

/**
 * programme_channel_noビューのための読み込み専用インタフェース
 *
 * @author dosdiaopfhj
 */
public interface ProgrammeChannelNo_ReadOnly  {

    /**
     * チャンネルIDを取得
     *
     * @return チャンネルID 初期値は空文字列。
     */
    String getChannel_id();

    /**
     * 物理チャンネル番号を取得
     *
     * @return 物理チャンネル番号 初期値は0。
     */
    Integer getChannel_no();

    /**
     * 番組IDを取得
     *
     * @return 番組ID 初期値は0。
     */
    Integer getEvent_id();

    /**
     * 放送開始時刻を取得
     *
     * @return 放送開始時刻 初期値はnull。
     */
    Timestamp getStart_datetime();

    /**
     * 放送終了時刻を取得
     *
     * @return 放送終了時刻 初期値はnull。
     */
    Timestamp getStop_datetime();

    /**
     * 番組名を取得
     *
     * @return 番組名 初期値は0。
     */
    String getTitle();

    /**
     * 他のオブジェクトと保持している内容を比較する。タイムスタンプはテキストに直して比較する。
     *
     * @return 同型オブジェクトで、全ての項目について同じ内容ならtrue そうでないならfalse。
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Beanが保持しているデータを文字列に変換する。
     */
    @Override
    String toString();

}
