package epgtools.consoleinput;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//http://www.javaspecialists.eu/archive/Issue153.html

/**
 * 入力待ちを行う。
 * @author dosdiaopfhj
 */
public final class ConsoleInput{

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final long timeout;
    private final TimeUnit unit;
    /**
     * 入力待ち制御系
     * @param wait 
     */
    public ConsoleInput(WatitngTime wait) {
        this.timeout = wait.getTimeout();
        this.unit = wait.getUnit();
    }

    /**
     * 入力待ちタスクを使って、キーボードからの入力を受け付ける。
     * @return 入力された文字列。タイムアウト、もしくは割り込みがによって入力待ちが中断された時はnull
     * @throws java.io.IOException ストリームのオープンに失敗した場合。
     * @throws java.util.concurrent.TimeoutException
     *
     */
    public final synchronized String readLine() throws IOException, TimeoutException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String ret = "";
        try {
            Future<String> result = ex.submit(new ConsoleInputReadTask());
            try {
                ret = result.get(timeout, unit);
            } catch (ExecutionException e) {
                log.log(Level.SEVERE, "入力待ちタスクの実行中に例外が発生", e);
            } catch (TimeoutException e) {
                result.cancel(true);
                throw e;
            } catch (InterruptedException e) {
                log.log(Level.WARNING, "割り込みのため、入力処理がキャンセルされました。");
                ret = null;
            }
        } finally {
            ex.shutdownNow();
        }
        return ret;
    }

}
