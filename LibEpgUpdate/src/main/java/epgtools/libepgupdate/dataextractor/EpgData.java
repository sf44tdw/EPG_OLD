/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataExtractor;

/**
 * EPGから取り出した内容を保持する。
 *
 * @author dosdiaopfhj
 */
public interface EpgData {

    /**
     * チャンネルIDを取得する。
     * @return チャンネルID
     */
    public abstract String getId();

}
