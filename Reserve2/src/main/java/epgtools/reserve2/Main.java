/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve2;

import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve2.programme.Programme;
import epgtools.reserve2.reserveexecutor.ReserveExecutor;
import epgtools.reserve2.reserveexecutor.ReserveExecutorInterface;
import java.io.File;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * チャンネル番号、放送開始時刻、放送終了時刻、録画コマンドを指定して録画予約を行う。 放送時間が正の数であるかどうか以外はチェックしない。
 * EPGがまだ存在しない番組の予約用。
 *
 * @author dosdiaopfhj
 */
public class Main {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    //録画コマンド
    public static final String RECORDCOMMAND = "recDTV.sh";
    
    //設定ファイル名
    private static final String LOGGER_PROPERTYFILE="Logger.properties";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //物理チャンネル番号、放送開始時刻、放送終了時刻、録画コマンドを受け取る。

        int pch = 0;
        Timestamp startTime = null;
        Timestamp endTime = null;
        String com = null;

        try {
            //ログ設定ファイルを読み込む。ファイル名がLogger.propertiesであることを前提とする。
            File dirPath = new File(args[0]);
            if (dirPath.isDirectory()) {
                LoggerConfigurator.setlogproperties(new File(dirPath, LOGGER_PROPERTYFILE));
            } else {
                System.out.println("設定ファイルのあるディレクトリを開けませんでした。強制終了します。");
                System.exit(-1);
            }
            //物理チャンネル番号を取得する。
            log.log(Level.FINE, "取得された物理チャンネル番号={0}", args[1]);
            pch = Integer.parseInt(args[1]);

            //放送開始時刻を取得する。
            log.log(Level.FINE, "取得された放送開始時刻={0}", args[2]);
            startTime = Timestamp.valueOf(args[2]);

            //放送終了時刻を取得する。
            log.log(Level.FINE, "取得された放送終了時刻={0}", args[3]);
            endTime = Timestamp.valueOf(args[3]);

            //入力に問題が無ければ予約を行う。
            Programme p = new Programme(pch, startTime, endTime);
            log.log(Level.INFO, "この内容で予約を実施します。");
            log.log(Level.INFO, "物理チャンネル番号={0}", pch);
            log.log(Level.INFO, "放送開始時刻={0}", startTime.toString());
            log.log(Level.INFO, "放送終了時刻={0}", endTime.toString());
            log.log(Level.INFO, p.toString());

            ReserveExecutorInterface r = new ReserveExecutor();
//            ReserveExecutorInterface r = new DummyExecutor();
            p.checkFuture();

            if (r.executeReserveCommand(p, Main.RECORDCOMMAND)) {
                log.log(Level.INFO, "予約完了。");
            } else {
                log.log(Level.WARNING, "予約失敗。");
            }

            System.exit(0);
        } catch (IllegalArgumentException e) {
            log.log(Level.SEVERE, null, e);
        } catch (Exception e) {
            log.log(Level.INFO, "第1引数は設定ファイルのディレクトリ。第2引数は物理チャンネル番号。第3引数は放送開始時刻。第4引数は放送終了時刻");
            log.log(Level.INFO, "時刻はyyyy-mm-dd hh:mm:ssの形式。");
            log.log(Level.INFO, "設定ファイル名={0}",Main.LOGGER_PROPERTYFILE);
            log.log(Level.INFO, "録画に使用するコマンド={0}",Main.RECORDCOMMAND);
            log.log(Level.SEVERE, null, e);

        }

    }

}
