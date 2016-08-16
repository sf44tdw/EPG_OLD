/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.loggerconfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author dosdiaopfhj
 */
public final class LoggerConfigurator {

    private LoggerConfigurator() {
    }

    // クラス名をセットしたロガーを取得
    public final static synchronized Logger getCallerLogger() {
        return Logger.getLogger(new Throwable().getStackTrace()[1].getClassName());
    }

    /**
     * ロガーに設定ファイルをセットする。 ファイルが見つからない場合は何も設定しない。 設定中にエラーが起きた場合、例外を発生させる。
     * @param f 設定ファイル
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public final static synchronized void setlogproperties(File f) throws FileNotFoundException, IOException {
        final File f_i=new File(f.getAbsolutePath());
        if (f_i.isFile()) {
            try (InputStream in = new FileInputStream(f_i);) {
                LogManager.getLogManager().readConfiguration(in);
                LoggerConfigurator.getCallerLogger().log(Level.INFO, "ロガーの設定を行いました。設定ファイル={0}", f_i.getAbsolutePath());
            }
        } else {
            LoggerConfigurator.getCallerLogger().log(Level.WARNING, "設定ファイルが見つかりません。パス={0}", f_i.getAbsolutePath());
        }
    }

}
