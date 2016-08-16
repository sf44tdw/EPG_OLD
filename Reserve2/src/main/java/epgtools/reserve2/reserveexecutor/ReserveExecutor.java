/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve2.reserveexecutor;

import epgtools.commandexecutor.CommandExecutor;
import epgtools.commandexecutor.CommandResult;
import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve2.programme.Programme;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
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
public class ReserveExecutor implements ReserveExecutorInterface {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    public static final String DT = "yyyyMMddHHmm";

    @Override
    public synchronized boolean executeReserveCommand(Programme p, String recordCommand) {
        try {

            //自動削除されるテンポラリファイルを作成。
            File tempFile = File.createTempFile("myApp", ".tmp");
            tempFile.deleteOnExit();
            try ( //テンポラリファイルに録画コマンドを書き込む。
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)));) {
                String s0 = "録画時間: " + p.getStartDatetime().toString() + " *->* " + p.getStopDatetime().toString();
                log.log(Level.INFO, s0);
                bw.write(s0);
                bw.newLine();

                //録画時間は放送時間より2分長くする。
                String s1 = recordCommand + " " + p.getPhysicalChannelNumber() + " " + (p.getAirtime() + 120);
                log.log(Level.INFO, s1);
                bw.write(s1);
                bw.newLine();

            }

            //放送開始1分前の時間を取得
            Timestamp t2 = new Timestamp(p.getStartDatetime().getTime() - 60000);
            String s2 = new SimpleDateFormat(ReserveExecutor.DT).format(t2);

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
}
