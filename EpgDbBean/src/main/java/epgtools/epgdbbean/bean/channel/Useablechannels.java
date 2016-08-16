/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.channel;

import MutableObject.MutableObject;
import Util.TimeStampCopier;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * useablechannelsビューのレコードを保持する。
 *
 * @author dosdiaopfhj
 */
public final class Useablechannels extends MutableObject implements Useablechannels_ReadOnly {

//    private static final long serialVersionUID = 5348673415846658753L;

    private final TimeStampCopier cp = new TimeStampCopier();

    private String channel_id = "";
    private Integer channel_no = 0;
    private String display_name = "";
    private Timestamp insert_datetime = null;

    public Useablechannels() {
    }

    /**
     * チャンネルIDを取得
     *
     * @return チャンネルID 初期値は空文字列。
     */
    @Override
    public synchronized String getChannel_id() {
        return channel_id;
    }

    /**
     * チャンネルIDを設定
     *
     * @param channel_id チャンネルID 初期値は空文字列。
     */
    public synchronized void setChannel_id(String channel_id) {
        this.checkNull(channel_id);
        String oldValue = this.getChannel_id();
        this.channel_id = channel_id;
        this.propertyChangeSupport.firePropertyChange("channel_id", oldValue, channel_id);
    }

    /**
     * 物理チャンネル番号を取得
     *
     * @return 物理チャンネル番号 初期値は0。
     */
    @Override
    public synchronized Integer getChannel_no() {
        return channel_no;
    }

    /**
     * 物理チャンネル番号を設定
     *
     * @param channel_no チャンネルID 初期値は0。
     */
    public synchronized void setChannel_no(Integer channel_no) {
        this.checkNull(channel_no);
        Integer oldValue = this.getChannel_no();
        this.channel_no = channel_no;
        this.propertyChangeSupport.firePropertyChange("channel_no", oldValue, channel_no);
    }

    /**
     * 放送局名を取得
     *
     * @return 放送局名 初期値は空文字列。
     */
    @Override
    public synchronized String getDisplay_name() {
        return display_name;
    }

    /**
     * 放送局名を設定
     *
     * @param display_name 放送局名 初期値は空文字列。
     */
    public synchronized void setDisplay_name(String display_name) {
        String oldValue = this.getDisplay_name();
        this.display_name = display_name;
        this.propertyChangeSupport.firePropertyChange("display_name", oldValue, display_name);
    }

    /**
     * 追記時刻を取得
     *
     * @return 追記時刻 初期値はnull。
     */
    @Override
    public synchronized Timestamp getInsert_datetime() {
        return cp.copyTimestamp(this.insert_datetime);
    }

    /**
     * 追記時刻を設定
     *
     * @param insert_datetime 追記時刻
     */
    public synchronized void setInsert_datetime(Timestamp insert_datetime) {
        this.checkNull(insert_datetime);
        Timestamp oldValue = this.getInsert_datetime();
        this.insert_datetime = cp.copyTimestamp(insert_datetime);
        this.propertyChangeSupport.firePropertyChange("insert_datetime", oldValue, insert_datetime);
    }

    /**
     * 他のオブジェクトと保持している内容を比較する。タイムスタンプはLong値に直して比較する。
     *
     * @return 同型オブジェクトで、全ての項目について同じ内容ならtrue そうでないならfalse。
     */
    @Override
    public synchronized boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Useablechannels_ReadOnly) {
            Useablechannels_ReadOnly o = (Useablechannels_ReadOnly) obj;
            if (!(this.getChannel_id() == null ? o.getChannel_id() == null : this.getChannel_id().equals(o.getChannel_id()))) {
                return false;
            }
            if (!(Objects.equals(this.getChannel_no(), o.getChannel_no()))) {
                return false;
            }
            if (!(this.getDisplay_name() == null ? o.getDisplay_name() == null : this.getDisplay_name().equals(o.getDisplay_name()))) {
                return false;
            }
            if (!(this.getInsert_datetime().getTime()==o.getInsert_datetime().getTime())) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.channel_id);
        hash = 79 * hash + this.channel_no;
        hash = 79 * hash + Objects.hashCode(this.display_name);
        hash = 79 * hash + Objects.hashCode(this.insert_datetime);
        return hash;
    }
}
