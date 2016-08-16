/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.consoleinput.WatitngTime;
import epgtools.epgdbbean.bean.channel.ChannelGetter;
import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import epgtools.epgdbbean.bean.programme.ProgrammeGetter;
import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve3.dialog.Inputprocessor.GenerationInabilityException;
import epgtools.reserve3.dialog.Inputprocessor.InputProcessor;
import epgtools.reserve3.dialog.Programme.ProgrammeDataProcessor;
import epgtools.reserve3.dialog.Programme.ProgrammeRequest;
import epgtools.reserve3.dialog.channel.ChannelDataProcessor;
import epgtools.reserve3.dialog.channel.ChannelRequest;
import epgtools.reserve3.dialog.yesno.YesNoDataProcesser;
import epgtools.reserve3.dialog.yesno.YesNoRequest;
import epgtools.reserve3.reserveexecutor.DummyExecutor;
import epgtools.reserve3.reserveexecutor.ReserveExecutor;
import epgtools.reserve3.reserveexecutor.ReserveExecutorImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * DBから番組を選択して録画予約を行う。予約した後のことは感知しない。
 *
 * @author dosdiaopfhj
 */
public class Main {

    /**
     * ログ出力設定ファイル名
     */
    private static final String LOGGER_CONFIG_FILE_NAME = "Logger.properties";

    /**
     * 設定ファイル名
     */
    private static final String CONFIG_FILE_NAME = "ProgrammeReserver.properties";

    /**
     * 設定ファイル項目名 DBのurl
     */
    private static final String DB_URL = "url";
    /**
     * 設定ファイル項目名 DBのユーザー名
     */
    private static final String DB_USER = "user";
    /**
     * 設定ファイル項目名 DBのパスワード
     */
    private static final String DB_PASSWORD = "password";
    /**
     * 設定ファイル項目名 録画コマンド(引数やオプションは指定できない)
     */
    private static final String RECORD_COMMAND = "recordcommand";

    /**
     * 番組表示用テンプレート
     */
    private static final MessageFormat PROGRAMME_FORMAT = new MessageFormat("チャンネルID={0}, 物理チャンネル番号={1,number,#}, 番組ID={2,number,#}, 番組名={3}, 放送開始日時={4}, 放送終了日時={5}");

    /**
     * 入力待ち時間
     */
    private static final WatitngTime WAIT = new WatitngTime(10, TimeUnit.MINUTES);

    /**
     * 予約処理強制終了用入力項目
     */
    private static final String ABORT = "abort";
    /**
     * 実行終了用入力項目
     */
    private static final String EXIT = "exit";
    private static final String[] DISCONTINUATION = new String[]{Main.ABORT, Main.EXIT};

    /**
     * 予約処理強制終了メッセージ
     */
    public static final String ABORT_MESSAGE = "予約は中止されました。";

    /**
     * 実行終了用入力項目入力項目表示用テンプレート
     */
    private static final MessageFormat EXIT__CONFIRMATION_MESSAGE = new MessageFormat("全ての処理を終了する場合は、入力待ちになったときに{0} と入力してください。");

    /**
     * 実行終了用入力項目表示用テンプレート用メッセージ
     */
    private static final Object[] EXIT_CONFIRMATION_MESSAGE_PARAMETER = {Main.EXIT};

    /**
     * 実行終了メッセージ
     */
    public static final String EXIT_MESSAGE = "予約処理を終了します。";

    //理由は不明だが、シェルスクリプト経由で、シェルスクリプトが置いてある場所と違うディレクトリにあるこれを
    //実行した場合、System.out.Printlnから出力されるメッセージがすべて表示されなくなるので、
    //回避策として出力すべてをlogを使って行うことにした。
    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "";
        String user = "";
        String password = "";
        String recordCommand = "";

        File configPathF = new File(args[0]);
        if (!configPathF.isDirectory()) {
            log.log(Level.SEVERE, "設定ファイルのあるディレクトリを開けませんでした。強制終了します。");
            log.log(Level.SEVERE, "第1引数は設定ファイルのあるディレクトリ。、第2引数は設定ファイルの文字コード。");
            log.log(Level.SEVERE, "設定ファイル名={0}", CONFIG_FILE_NAME);
            log.log(Level.SEVERE, "ロガー用設定ファイル名={0}", LOGGER_CONFIG_FILE_NAME);
            System.exit(-1);
        }

        //設定ファイル(相対指定にすると、キッカースクリプトの置いてある場所を基準にしてファイルを探そうとするので、絶対指定で行う。)
        File Pf = new File(configPathF, CONFIG_FILE_NAME);

        log.log(Level.INFO, "設定ファイル={0}", Pf.getAbsolutePath());

        //自動クローズしない場合があるので、ネストせず個別に変数定義する。
        try (FileInputStream fis = new FileInputStream(Pf);
                InputStreamReader isr = new InputStreamReader(fis, args[1]);
                Reader R = new BufferedReader(isr);) {
            //設定ファイルのロード
            Properties conf = new Properties();
            conf.load(R);
            url = conf.getProperty(Main.DB_URL);
            user = conf.getProperty(Main.DB_USER);
            password = conf.getProperty(Main.DB_PASSWORD);
            recordCommand = conf.getProperty(Main.RECORD_COMMAND);
            LoggerConfigurator.setlogproperties(new File(configPathF, LOGGER_CONFIG_FILE_NAME));
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            log.log(Level.SEVERE, "設定ファイルの文字コードが相違しているか、ファイルが見つかりませんでした。", ex);
        } catch (IOException ex) {
            log.log(Level.SEVERE, "設定ファイルの読み込み中に問題が発生しました。", ex);
        }

        //brは入力用。クローズしないこと。
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (JDBCAccessor Accessor = JDBCAccessor.getInstance()) {
            Accessor.connect(url, user, password);
            java.sql.Connection con = Accessor.getCon();

            MENU:
            {
                while (true) {
                    log.log(Level.INFO, "予約処理を実施します。");
                    log.log(Level.INFO, EXIT__CONFIRMATION_MESSAGE.format(EXIT_CONFIRMATION_MESSAGE_PARAMETER));

                    RESERVE:
                    {
                        //チャンネルの一覧を取得し、Mapに格納する。Mapのキーはチャンネル番号とする。
                        Map<Integer, Useablechannels_ReadOnly> Channels_Ro = new ChannelGetter(con).getChannels();
                        Useablechannels_ReadOnly ch;

                        //チャンネルの一覧を表示し、録画予約を行いたい物理チャンネル番号を選択させる。(10分待つ。)
                        InputProcessor<Useablechannels_ReadOnly> inpp_ch = new InputProcessor<>(DISCONTINUATION, WAIT, new ChannelRequest(Channels_Ro), new ChannelDataProcessor(Channels_Ro));
                        Useablechannels_ReadOnly res_ch = inpp_ch.readLine();

                        if (res_ch == null) {
                            if (inpp_ch.getDiscontinuationString().equals(ABORT)) {
                                log.log(Level.WARNING, ABORT_MESSAGE);
                                break RESERVE;
                            }

                            if (inpp_ch.getDiscontinuationString().equals(EXIT)) {
                                log.log(Level.INFO, EXIT_MESSAGE);
                                break MENU;
                            }
                        }

                        //放送開始時刻の入力を要求する。(10分待つ。)
                        InputProcessor<ProgrammeChannelNo_ReadOnly> inpp_pr = new InputProcessor<>(DISCONTINUATION, WAIT, new ProgrammeRequest(res_ch), new ProgrammeDataProcessor(res_ch, new ProgrammeGetter(con)));
                        ProgrammeChannelNo_ReadOnly res_p = inpp_pr.readLine();

                        if (res_p == null) {
                            if (inpp_pr.getDiscontinuationString().equals(ABORT)) {
                                log.log(Level.WARNING, ABORT_MESSAGE);
                                break RESERVE;
                            }

                            if (inpp_pr.getDiscontinuationString().equals(EXIT)) {
                                log.log(Level.INFO, EXIT_MESSAGE);
                                break MENU;
                            }
                        }

                        ProgrammeChannelNo_ReadOnly p = res_p;

                        final String NL = "NULL";
                        String start = NL;
                        String stop = NL;
                        if (p.getStart_datetime() != null) {
                            start = p.getStart_datetime().toString();
                        }
                        if (p.getStop_datetime() != null) {
                            stop = p.getStop_datetime().toString();
                        }
                        Object[] parameters = {p.getChannel_id(), p.getChannel_no(), p.getEvent_id(), p.getTitle(), start, stop};
                        log.log(Level.INFO, PROGRAMME_FORMAT.format(parameters));

                        //予約内容の確認を要求する。(10分待つ。)
                        InputProcessor<Boolean> inpp_yn1 = new InputProcessor<>(null, WAIT, new YesNoRequest("この内容で予約を実施します。よろしいですか? Y/N"), new YesNoDataProcesser());
                        Boolean res_YN1 = inpp_yn1.readLine();

                        if (res_YN1 == null) {
                            log.log(Level.WARNING, "入力の処理に問題が発生したため、{0}", EXIT_MESSAGE);
                            break MENU;
                        }
                        if (res_YN1 == false) {
                            log.log(Level.INFO, ABORT_MESSAGE);
                            break RESERVE;
                        }

                        //録画予約を行う。(USE_DUMMYをtrueにすると何もしないダミーを使う)
                        ReserveExecutor r;
                        final boolean USE_DUMMY = false;
                        if (!USE_DUMMY) {
                            r = new ReserveExecutorImpl();
                        } else {
                            r = new DummyExecutor();
                        }

                        //放送開始>=放送終了となるケースはDB上には存在しない。(禁止している)
                        if (r.executeReserveCommand(p, recordCommand)) {
                            log.log(Level.INFO, "予約完了。");
                        } else {
                            log.log(Level.WARNING, "予約失敗。");
                        }

                        //別の予約を行うか確認する。(10分待つ。)
                        InputProcessor<Boolean> inpp_yn2 = new InputProcessor<>(null, WAIT, new YesNoRequest("続けて別の予約を行いますか? Y/N"), new YesNoDataProcesser());
                        Boolean res_YN2 = inpp_yn2.readLine();

                        if (res_YN2 == null) {
                            log.log(Level.WARNING, "入力の処理に問題が発生したため、{0}", EXIT_MESSAGE);
                            break MENU;
                        }
                        if (res_YN2 == false) {
                            log.log(Level.INFO, ABORT_MESSAGE);
                            break MENU;
                        }

                    }

                }

            }

        } catch (GenerationInabilityException ex) {
            log.log(Level.SEVERE, "入力された情報をオブジェクトに変換できません。", ex);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "SQL発行中にエラー発生。", ex);
        } catch (IOException ex) {
            log.log(Level.SEVERE, "入力を受け付けられません。", ex);
        } catch (TimeoutException ex) {
            MessageFormat mf = new MessageFormat("入力待ち時間を超過しました。待ち時間={0}");
            String[] message = {Main.WAIT.toString()};
            log.log(Level.SEVERE, mf.format(message), ex);
        } catch (Throwable ex) {
            log.log(Level.SEVERE, "その他の例外発生。", ex);
        }
        System.exit(0);
    }

}
