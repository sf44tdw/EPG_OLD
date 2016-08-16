/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.consts;

/**
 *
 * @author dosdiaopfhj
 */
public class DbConsts {

    /**
     * ストアドプロシージャの引数名  _channel_no
     * :対応する物理チャンネル番号(例:東京タワーからのnhk総合なら27)
     */
    public static final String CHANNEL_NO = "_channel_no";

    /**
     * ストアドプロシージャの引数名 _start_datetime :開始時刻
     */
    public static final String START_DATETIME = "_start_datetime";

    
    /**
     * ストアドプロシージャのテンプレート 番組名取得用
     */
    public static final String GET_TITLE_S = "{CALL GET_TITLE(?,?) }";

}
