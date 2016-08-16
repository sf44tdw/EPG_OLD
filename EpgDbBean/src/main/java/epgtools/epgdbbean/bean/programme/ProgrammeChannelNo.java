/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.programme;

import MutableObject.MutableObject;
import Util.TimeStampCopier;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * programme_channel_noビューのレコードを保持する。
 *
 * @author dosdiaopfhj
 */
public final class ProgrammeChannelNo extends MutableObject implements ProgrammeChannelNo_ReadOnly {

//    private static final long serialVersionUID = 47832168783456056L;
    private final TimeStampCopier cp = new TimeStampCopier();
    
    private String channel_id = "";
    private Integer channel_no = 0;
    private Integer event_id = 0;
    private String title = "";
    private Timestamp start_datetime = null;
    private Timestamp stop_datetime = null;
    
    public ProgrammeChannelNo() {
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
     * 番組IDを取得
     *
     * @return 番組ID 初期値は0。
     */
    @Override
    public synchronized Integer getEvent_id() {
        return event_id;
    }

    /**
     * 番組IDを設定
     *
     * @param event_id 番組ID 初期値は空文字列。
     */
    public synchronized void setEvent_id(Integer event_id) {
        this.checkNull(event_id);
        Integer oldValue = this.getEvent_id();
        this.event_id = event_id;
        this.propertyChangeSupport.firePropertyChange("event_id", oldValue, event_id);
    }

    /**
     * 番組名を取得
     *
     * @return 番組名 初期値は0。
     */
    @Override
    public synchronized String getTitle() {
        return this.title;
    }

    /**
     * 番組名を設定
     *
     * @param title 番組名
     */
    public synchronized void setTitle(String title) {
        this.checkNull(title);
        String oldValue = this.getTitle();
        this.title = title;
        this.propertyChangeSupport.firePropertyChange("title", oldValue, title);
    }

    /**
     * 放送開始時刻を取得
     *
     * @return 放送開始時刻 初期値はnull。
     */
    @Override
    public synchronized Timestamp getStart_datetime() {
        return cp.copyTimestamp(this.start_datetime);
    }

    /**
     * 放送開始時刻を設定
     * 放送開始時刻、放送終了時刻が設定済みか、片方が設定済みのときにもう片方を設定しようとした時、設定された放送開始時刻が放送終了時刻以前であることを確認する。
     * 上記に当てはまらない場合、IllegalArgumentExceptionを発生させる。 例外発生時に設定しようとしていた値は設定されない。
     *
     * @param start_datetime 放送開始時刻
     */
    public synchronized void setStart_datetime(Timestamp start_datetime) {
        this.checkNull(start_datetime);
        Timestamp oldValue = this.getStart_datetime();
        this.checkValidAirTime(start_datetime, this.stop_datetime);
        this.start_datetime = cp.copyTimestamp(start_datetime);
        this.propertyChangeSupport.firePropertyChange("start_datetime", oldValue, start_datetime);
    }

    /**
     * 放送終了時刻を取得
     *
     * @return 放送終了時刻 初期値はnull。
     */
    @Override
    public synchronized Timestamp getStop_datetime() {
        return cp.copyTimestamp(this.stop_datetime);
    }

    /**
     * 放送終了時刻を設定。
     * 放送開始時刻、放送終了時刻が設定済みか、片方が設定済みのときにもう片方を設定しようとした時、設定された放送終了時刻が放送開始時刻以後であることを確認する。
     * 上記に当てはまらない場合、IllegalArgumentExceptionを発生させる。 例外発生時に設定しようとしていた値は設定されない。
     *
     * @param stop_datetime 放送終了時刻
     */
    public synchronized void setStop_datetime(Timestamp stop_datetime) {
        this.checkNull(stop_datetime);
        Timestamp oldValue = this.getStop_datetime();
        this.checkValidAirTime(this.start_datetime, stop_datetime);
        this.stop_datetime = cp.copyTimestamp(stop_datetime);
        this.propertyChangeSupport.firePropertyChange("stop_datetime", oldValue, stop_datetime);
    }
    

    
    private synchronized void checkValidAirTime(Timestamp start_datetime, Timestamp stop_datetime) {
        if ((start_datetime != null) && (stop_datetime != null)) {
            if (!(start_datetime.getTime() <= stop_datetime.getTime())) {
                final String CRLF = System.getProperty("line.separator");
                StringBuilder strings = new StringBuilder();
                strings.append("放送開始時刻が放送終了時刻より後になっています。");
                strings.append(CRLF);
                strings.append("設定しようとした状態");
                strings.append(" = ");
                strings.append("{");
                strings.append("放送開始時刻 = ");
                strings.append(start_datetime.toString());
                strings.append(",");
                strings.append("放送終了時刻 = ");
                strings.append(stop_datetime.toString());
                strings.append("{");
                strings.append(CRLF);
                throw new IllegalArgumentException(strings.toString());
            }
        }
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
        if (obj instanceof ProgrammeChannelNo_ReadOnly) {
            ProgrammeChannelNo_ReadOnly o = (ProgrammeChannelNo_ReadOnly) obj;
            if (!(this.getChannel_id() == null ? o.getChannel_id() == null : this.getChannel_id().equals(o.getChannel_id()))) {
                return false;
            }
            if (!(Objects.equals(this.getChannel_no(), o.getChannel_no()))) {
                return false;
            }
            if (!(Objects.equals(this.getEvent_id(), o.getEvent_id()))) {
                return false;
            }
            if (!(this.getTitle() == null ? o.getTitle() == null : this.getTitle().equals(o.getTitle()))) {
                return false;
            }
            if (!(this.start_datetime.getTime() == o.getStart_datetime().getTime())) {
                return false;
            }
            if (!(this.stop_datetime.getTime() == o.getStop_datetime().getTime())) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public synchronized int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.channel_id);
        hash = 29 * hash + Objects.hashCode(this.channel_no);
        hash = 29 * hash + Objects.hashCode(this.event_id);
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.start_datetime);
        hash = 29 * hash + Objects.hashCode(this.stop_datetime);
        return hash;
    }
    
}
