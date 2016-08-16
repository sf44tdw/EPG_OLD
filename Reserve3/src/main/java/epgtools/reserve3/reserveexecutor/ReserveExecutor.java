/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.reserveexecutor;

import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;

/**
 * 選択された番組情報から予約のために使用するコマンドを生成し、実行する。
 *
 * @author dosdiaopfhj
 * @param <T> executeReserveCommandが投げる例外。
 */
public interface ReserveExecutor<T extends Throwable> {
    
    /**
 * 選択された番組情報から予約のために使用するコマンドを生成し、実行する。
 * @author dosdiaopfhj
     * @param p              録画予約対象の番組情報
     * @param recordCommand  録画に使用するコマンド
     * @return 問題なく処理が行われればtrue それ以外はfalse
     * @throws T
 */
     public boolean executeReserveCommand(ProgrammeChannelNo_ReadOnly p, String recordCommand) throws T;
}
