/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve2.programme;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * 番組情報の保持を行うクラス
 *
 * @author dosdiaopfhj
 */
public final class Programme {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    //物理チャンネル番号
    private final int physicalChannelNumber;

    //開始時刻
    private final java.sql.Timestamp startDatetime;

    //終了時刻
    private final java.sql.Timestamp stopDatetime;

    /**
     * 番組データ
     *
     * @param physicalChannelNumber:物理チャンネル番号
     * @param startDatetime:放送開始時刻
     * @param stopDatetime :放送終了時刻
     */
    public Programme(int physicalChannelNumber, Timestamp startDatetime, Timestamp stopDatetime) {
        this.physicalChannelNumber = physicalChannelNumber;
        this.startDatetime = new Timestamp(startDatetime.getTime());
        this.stopDatetime = new Timestamp(stopDatetime.getTime());
    }

    public synchronized int getPhysicalChannelNumber() {
        return physicalChannelNumber;
    }

    public synchronized Timestamp getStartDatetime() {
        return this.copyTimestamp(this.startDatetime);
    }

    public synchronized Timestamp getStopDatetime() {
        return this.copyTimestamp(this.stopDatetime);
    }

    /**
     * このクラスが保持する放送時間の改変対策として、コピーを渡す。
     *
     * @return 放送時間のコピー。
     */
    private synchronized Timestamp copyTimestamp(Timestamp ts) {
        return new Timestamp(ts.getTime());
    }

    /**
     * 番組の放送時間を秒数で取得する。
     *
     * @return 放送時間の秒数。
     */
    public synchronized long getAirtime() throws IllegalArgumentException {
        long at = (this.stopDatetime.getTime() - this.startDatetime.getTime()) / 1000;
        if (at < 0) {
            MessageFormat format = new MessageFormat("放送時間がマイナスの値になっています。放送時間(秒)={0}");
            throw new IllegalArgumentException( format.format(at));
        } else {
            return at;
        }
    }

    /**
     * 番組の放送時間が、現在の時刻より未来のものであることを確認する。
     *
     */
    public synchronized final void checkFuture() throws IllegalArgumentException {
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        if ((this.startDatetime.compareTo(nowTime) > 0) && (this.stopDatetime.compareTo(nowTime) > 0)) {
        } else {
            throw new IllegalArgumentException("放送開始、終了時刻のいずれかがが現在以前になっています。");
        }
    }

    @Override
    public String toString() {

        return "programme{"
                + "物理チャンネル番号=" + physicalChannelNumber
                + ", 放送開始日時=" + startDatetime.toString()
                + ", 放送終了日時=" + stopDatetime.toString() + '}';

    }

}
