/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.reserveexecutor;

import epgtools.commandexecutor.CommandExecutor;
import epgtools.commandexecutor.CommandResult;
import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 選択された番組情報から予約のために使用するat用のコマンドを生成し、実行する。
 * 録画開始時刻は放送開始時刻の1分前とし、録画時間は放送時間より2分長くする。
 * 録画に使用するコマンドは、物理チャンネル番号と録画時間の秒数を引数に持つものとする。
 *
 * @author dosdiaopfhj
 */
public class ReserveExecutorImpl implements ReserveExecutor<IllegalArgumentException> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    private final MessageFormat format = new MessageFormat("#{0}**{1} *->* {2}");

    private static final String DT = "yyyyMMddHHmm";

    @Override
    public synchronized boolean executeReserveCommand(ProgrammeChannelNo_ReadOnly p, String recordCommand) throws IllegalArgumentException {
        try {

            //自動削除されるテンポラリファイルを作成。
            File tempFile = File.createTempFile("myApp", ".tmp");
            tempFile.deleteOnExit();
            try ( //テンポラリファイルに録画コマンドを書き込む。
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)));) {
                Object[] parameters = {p.getTitle(), p.getStart_datetime(), p.getStop_datetime()};
                String s0 = format.format(parameters);

                log.log(Level.INFO, s0);
                bw.write(s0);
                bw.newLine();

                //放送時間に120秒足す。
                String s1 = recordCommand + " " + p.getChannel_no() + " " + (this.getAirtime(p) + 120);
                log.log(Level.INFO, s1);
                bw.write(s1);
                bw.newLine();
            }

            //放送開始1分前
            Timestamp t2 = new Timestamp(p.getStart_datetime().getTime() - 60000);
            String s2 = new SimpleDateFormat(ReserveExecutorImpl.DT).format(t2);

            CommandResult res = new CommandExecutor().execCommand("at", "-t", s2, "-f", tempFile.getAbsolutePath());
            if (res != null) {
                log.log(Level.INFO, "予約が行われました。");
                log.log(Level.FINE, res.toString());
                //何故かatコマンドの出力はエラー出力のほうに出てくるので、そっちを表示する。
                log.log(Level.INFO, res.getStandardError());
                if (res.getReturnCode() > 0) {
                    log.log(Level.WARNING, "予約に失敗した可能性があります。");
                    return false;
                } else {
                    log.log(Level.FINE, "予約が行われました。");
                    return true;
                }
            } else {
                log.log(Level.WARNING, "予約コマンドの実行中に何らかの問題が発生しました。予約の成功は保証されません。");
                return false;
            }

        } catch (IOException ex) {
            log.log(Level.SEVERE, "予約コマンドの生成に失敗しました。", ex);
            return false;
        }
    }

    /**
     * 完全な情報がそろっているとき、番組の放送時間を秒数で取得する。
     *
     * @param p 番組情報
     * @return 放送時間の秒数。
     * @throws ReserveExecutor.IllegalArgumentException
     * 番組情報の、放送時間フィールドのうち、いずれかがnull。もしくは録画時間が負の数。
     */
    private synchronized long getAirtime(ProgrammeChannelNo_ReadOnly p) throws IllegalArgumentException {
        Long at = 0L;
        StringBuilder buf = new StringBuilder();
        final String CRLF = (System.getProperty("line.separator"));

        if (!((p.getStart_datetime() == null) || (p.getStop_datetime() == null))) {
            at = (p.getStop_datetime().getTime() - p.getStart_datetime().getTime()) / 1000;
        } else {
            buf.append("番組情報の、放送時間フィールドのうち、いずれかがnullです。");
            buf.append(CRLF);
            buf.append(p.toString());
            throw new IllegalArgumentException(buf.toString());
        }
        if (0 > at) {
            buf.append("録画時間に異常があります。録画時間(秒)=");
            buf.append(at);
            buf.append(CRLF);
            buf.append(p.toString());
            throw new IllegalArgumentException(buf.toString());
        }
        return at;
    }

}
