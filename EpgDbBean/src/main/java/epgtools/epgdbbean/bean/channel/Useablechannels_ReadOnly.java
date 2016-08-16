/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.channel;

import java.sql.Timestamp;

/**
 * useablechannelsビューのための読み込み専用インターフェース
 *
 * @author dosdiaopfhj
 */
public interface Useablechannels_ReadOnly {

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
     * 放送局名を取得
     *
     * @return 放送局名 初期値は空文字列。
     */
    String getDisplay_name();

    /**
     * 追記時刻を取得
     *
     * @return 追記時刻 初期値はnull。
     */
    Timestamp getInsert_datetime();

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
