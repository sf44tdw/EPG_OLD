/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.commandexecutor;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author normal
 *
 * 元ネタ:http://chat-messenger.net/blog-entry-52.html
 */
public class CommandExecutor {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * 外部コマンドを実行します。 またリターン値で、標準出力、エラー出力 、リターンコードを取得します。 例：
     * execCommand("notepad.exe");
     *
     * @see execCommand(String[] cmds) ※実行するコマンドに引数（パラメータ）がある場合は、 以下を使用してください。
     * @param cmd 実行するコマンド
     * @return コマンド実行結果情報を保持するCommandResult
     */
    public synchronized CommandResult execCommand(String cmd) {
        return execCommand(new String[]{cmd});
    }

    /**
     * 外部コマンドを引数（パラメータ）を指定して実行します。 また、標準出力、エラー出力 、リターンコードを取得します。 例：
     * execCommand(new String[]{"notepad.exe","C:\test.txt"});
     *
     * Process.waitFor()を実行していますので、外部コマンドの実行が 終了するまでこのメソッドは待機します。
     *
     * @see execCommand(String cmd) ※実行するコマンドに引数がない場合は簡易的にこちらを 使用してください。
     * @param cmds 実行するコマンドと引数(コマンド,引数,引数,...)
     * @return コマンド実行結果情報を保持するCommandResult
     */
    public synchronized CommandResult execCommand(String... cmds) {

        try {
            long c = 0;
            String ss="";
            for (String s : cmds) {
                if (c == 0) {
                    ss = s;
                } else {
                     ss = ss + " " + s;
                }
                c++;
            }
            log.log(Level.FINE, "コマンド={0}", ss);
            
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(cmds);
            InputStream in;

            //標準出力
            in = p.getInputStream();
            String stdOut = processMessage(in);
            in.close();

            //標準エラー出力
            in = p.getErrorStream();
            String stdErr = processMessage(in);
            in.close();

            //リターンコード
            int rC = p.waitFor();

            CommandResult returns = new CommandResult(stdOut, stdErr, rC);
            return returns;
        } catch (IOException ex) {
            log.log(Level.SEVERE, "コマンド出力結果の取り込みに失敗。", ex);
            return null;
        } catch (InterruptedException ex) {
            log.log(Level.SEVERE, "コマンドの実行が中断されました。", ex);
            return null;
        }
    }

    private synchronized String processMessage(InputStream in) {
        StringBuilder sB = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String LINE_SEPA = System.getProperty("line.separator");
            String line;
            while ((line = br.readLine()) != null) {
                sB.append(line).append(LINE_SEPA);
            }
        } catch (IOException ex) {
            log.log(Level.SEVERE, "コマンド出力結果の処理に失敗。", ex);
        }
        return sB.toString();
    }

}
