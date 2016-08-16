/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Timestamp;

/**
 *
 * @author dosdiaopfhj
 */
public final class TimeStampCopier {

    public TimeStampCopier() {
    }

    /**
     * タイムスタンプの改変対策として、コピーしたタイムスタンプを渡す。
     * @param ts
     * @return タイムスタンプのコピー。
     */
    public synchronized Timestamp copyTimestamp(Timestamp ts) {
        if (ts != null) {
            return new Timestamp(ts.getTime());
        } else {
            return null;
        }
    }

}
