/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.consoleinput;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 入力待ち時間を保持するクラス。
 *
 * @author dosdiaopfhj
 */
public final class WatitngTime {

    private final long timeout;
    private final TimeUnit unit;
    /**
     * 他の同型オブジェクトの値をコピーして新規作成する。
     * @param wait コピー元
     */
    public WatitngTime(WatitngTime wait) {
        this(wait.timeout,wait.unit);
    }
    
    /**
     * 待ち時間を設定して新規作成する。
     * @param timeout タイムアウトまでの値
     * @param unit タイムアウトまでの値の単位(秒,分,時間...)
     *
     */
    public WatitngTime(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        StringBuilder strings = new StringBuilder();
        strings.append("WatitngTime{");
        strings.append("timeout=");
        strings.append(this.getTimeout());
        strings.append(" , ");
        strings.append("unit=");
        if (this.getUnit() != null) {
            strings.append(this.getUnit().toString());
        } else {
            strings.append("");
        }
        strings.append("}");
        return strings.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.timeout ^ (this.timeout >>> 32));
        hash = 53 * hash + Objects.hashCode(this.unit);
        return hash;
    }

    /**
     * 同じ型で保持している内容が同じであるか判定する。
     * @return 同じ型で、保持している値が同じならtrue。それ以外はfalse。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WatitngTime other = (WatitngTime) obj;
        if (this.timeout != other.timeout) {
            return false;
        }
        if (this.unit != other.unit) {
            return false;
        }
        return true;
    }

}
