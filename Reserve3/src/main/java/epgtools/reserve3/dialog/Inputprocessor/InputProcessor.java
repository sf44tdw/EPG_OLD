/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.Inputprocessor;


import epgtools.consoleinput.ConsoleInput;
import epgtools.consoleinput.WatitngTime;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 入力受付処理の実行と、入力された文字列をオブジェクトに変換する処理を行う。 デフォルトでは再入力異要求が100回に達すると入力待ちを終了する。
 */
public class InputProcessor<T> {

    protected static final Logger log = LoggerConfigurator.getCallerLogger();
    private final String[] discontinuation;
    private final WatitngTime wait;
    private final InputRequest req;
    private final PostProcessor<T> pproc;

    private String discontinuationString = "";
    private int retryLimit = 100;

    /**
     *
     * @param discontinuation
     * 入力待ちをキャンセルするための文字列。複数指定可。指定された文字列のどれか1つが入力されると入力待ちをキャンセルする。この項目がnullの場合、入力内容による入力待ちのキャンセルは行わない。
     * @param wait 待ち時間。
     * @param req 入力待ち開始時に表示するメッセージ。
     * @param pproc 変換処理クラス
     */
    public InputProcessor(String[] discontinuation, WatitngTime wait, InputRequest req, PostProcessor<T> pproc) {
        this(discontinuation, wait, req, pproc, 100);
    }

    /**
     *
     * @param discontinuation
     * 入力待ちをキャンセルするための文字列。複数指定可。指定された文字列のどれか1つが入力されると入力待ちをキャンセルする。この項目がnullの場合、入力内容による入力待ちのキャンセルは行わない。
     * @param wait 待ち時間。
     * @param req 入力待ち開始時に表示するメッセージ。
     * @param pproc 変換処理クラス
     * @param retryLimit 変換失敗時のリトライ回数。デフォルトは100回。0未満の場合は、無視してデフォルトの回数を使用する。
     */
    public InputProcessor(String[] discontinuation, WatitngTime wait, InputRequest req, PostProcessor<T> pproc, int retryLimit) {
        
        if (discontinuation != null) {
            this.discontinuation = Arrays.copyOf(discontinuation, discontinuation.length);
        } else {
            //渡された配列がnullだった場合は、空の配列で初期化する。
            this.discontinuation = new String[0];
        }
        
        this.wait = new WatitngTime(wait);
        this.req = req;
        this.pproc = pproc;
        
        
        if (retryLimit >= 0) {
            this.retryLimit = retryLimit;
        }
    }

    /**
     * 入力待ちタスクを使って、キーボードからの入力を受け付け、必要なオブジェクトを生成する。
     *
     * @return 入力された文字列から作ったオブジェクト。リトライ制限を超えた場合はnull
     * @throws java.io.IOException 入力ストリームの確保に失敗。
     * @throws Dialog.InputProcessor.GenerationInabilityException
     * オブジェクトの生成手段が無い。
     * @throws java.util.concurrent.TimeoutException
     * 時間切れ。
     */
    public final synchronized T readLine() throws IOException, GenerationInabilityException, TimeoutException {
        T ret = null;
        String s = "";
        int x = 1;
        while (x <= this.retryLimit) {
            log.log(Level.INFO, this.req.inputRequestMessages());
            if (this.discontinuation.length != 0) {
                log.log(Level.INFO, "入力待ちをキャンセルする場合は、下記のいずれかを入力してください。");
                log.log(Level.INFO, Arrays.toString(this.discontinuation));
            }

            ConsoleInput inp = new ConsoleInput(wait);

            s = inp.readLine();
            //入力キャンセル条件に一致した場合は、nullを返す。
            if (this.discontinuation != null) {
                if (Arrays.asList(this.discontinuation).contains(s)) {
                    log.log(Level.FINE, "入力終了条件として設定された入力内容だったため、入力待ちがキャンセルされました。");
                    this.discontinuationString = s;
                    return null;
                }
            }
            try {
                ret = this.pproc.process(s);
                return ret;
            } catch (IllegalArgumentException ex) {
                log.log(Level.WARNING, "{0} 再入力してください。", ex.getMessage());
                log.log(Level.WARNING, "残り再試行回数={0}", this.retryLimit - x);
            }
            x++;
        }
        log.log(Level.WARNING, "再試行回数制限を超えました。");
        return null;
    }

    /**
     * readLine()が入力終了条件として設定された入力内容で入力待ちをキャンセルしたときに入力された文字列を取得する。
     */
    public final synchronized String getDiscontinuationString() {
        return discontinuationString;
    }

}
