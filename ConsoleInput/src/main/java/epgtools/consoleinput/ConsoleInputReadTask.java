package epgtools.consoleinput;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 入力待ちを行う。 http://www.javaspecialists.eu/archive/Issue153.html
 */
public final class ConsoleInputReadTask implements Callable<String> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     */
    public ConsoleInputReadTask() {
    }

    /**
     * 入力待ちを行い、キーボードからの入力を受け付ける。空の文字列については無視し、再度入力を受け付ける。
     *
     * @return 入力された文字列。 割り込みによって入力待ちをキャンセルした場合はnull。
     *
     * @throws java.io.IOException ストリームのオープンに失敗した場合。
     */
    @Override
    public synchronized String call() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = "";

            try {
                // wait until we have data to complete a readLine()
                while (!br.ready()) {
                    Thread.sleep(200);
                }
                input = br.readLine();

            } catch (InterruptedException e) {
                log.log(Level.WARNING, "割り込みのため、入力待ちがキャンセルされました。");
                return null;
            }

            //入力が空文字列以外なら入力内容を返す。
            if (!(input.equals(""))) {
                return input;
            }

        }
    }

}
