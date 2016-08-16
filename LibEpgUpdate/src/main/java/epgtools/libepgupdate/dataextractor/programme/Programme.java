package DataExtractor.Programme;

import java.sql.Timestamp;
import java.util.Objects;
import DataExtractor.EpgData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 番組情報の保持を行うクラス
 *
 * @author dosdiaopfhj
 */
public final class Programme implements EpgData {

    //最初のフィルタ。
    //開始時刻
    private final java.sql.Timestamp startDatetime;

    //ある1つの番組の指定に必要な2項目。
    //チャンネルid
    private final String id;
    //番組id
    private final int eventId;

    //最終的に取り出したい情報。
    //番組名
    private final String title;

    //終了時刻
    private final java.sql.Timestamp stopDatetime;

    /**
     * 番組データ
     *
     * @param id:チャンネルID
     * @param eventId:番組ID
     * @param title:番組名
     * @param startDatetime:放送開始時刻
     * @param stopDatetime :放送終了時刻
     */
    public Programme(String id, int eventId, String title, Timestamp startDatetime, Timestamp stopDatetime) throws IllegalArgumentException {

        if (id != null && !"".equals(id)) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("チャンネルIDがありません。");
        }
        if (eventId >= 0) {
            this.eventId = eventId;
        } else {
            throw new IllegalArgumentException("番組IDが0未満です。");
        }
        if (title != null && !"".equals(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("番組名がありません。");
        }

        //下記のように、放送開始日時より前に放送終了日時をセットした番組情報が配信されてくることもあるので、一応チェックする。
        //情報: programme{チャンネルID=BS_101, 番組ID=12189, 番組名=田中先発　ＭＬＢ・アメリカ大リーグ「ヤンキース」対「ブレーブス」【二】, 放送開始日時=2015-08-29 08:30:00.0, 放送終了日時=2015-08-29 08:29:59.0}
        if (startDatetime != null && stopDatetime != null) {
            if ((stopDatetime.getTime() - startDatetime.getTime()) >= 0) {
                this.startDatetime = new Timestamp(startDatetime.getTime());
                this.stopDatetime = new Timestamp(stopDatetime.getTime());
            } else {
                throw new IllegalArgumentException("放送開始時刻が放送終了時刻より後になっています。");
            }
        } else {
            throw new IllegalArgumentException("放送開始時刻、放送終了時刻のいずれかがありません。");
        }

    }

    @Override
    public synchronized String getId() {
        return id;
    }

    public synchronized int getEventId() {
        return eventId;
    }

    public synchronized String getTitle() {
        return title;
    }

    public synchronized Timestamp getStartDatetime() {
        return copyTimestamp(startDatetime);
    }

    public synchronized Timestamp getStopDatetime() {
        return copyTimestamp(stopDatetime);
    }

    /**
     * このクラスが保持する放送時間の改変対策として、コピーを渡す。
     *
     * @return 放送時間のコピー。
     */
    private synchronized final Timestamp copyTimestamp(Timestamp ts) {
        return new Timestamp(ts.getTime());
    }

    @Override
    public synchronized int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.startDatetime);
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + this.eventId;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.stopDatetime);
        return hash;
    }

    /**
     * 保持している値がすべて等しければtrue
     */
    @Override
    public synchronized boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.startDatetime.getTime(), other.startDatetime.getTime())) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.eventId != other.eventId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.stopDatetime.getTime(), other.stopDatetime.getTime())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "programme{" + "チャンネルID=" + id
                + ", 番組ID=" + eventId
                + ", 番組名=" + title
                + ", 放送開始日時=" + startDatetime.toString()
                + ", 放送終了日時=" + stopDatetime.toString() + '}';
    }

}
