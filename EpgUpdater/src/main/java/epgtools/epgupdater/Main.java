/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgupdater;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.libepgupdate.updator.Updater;
import epgtools.libepgupdate.updator.config.Config;
import epgtools.libepgupdate.updator.config.ConfigFactory;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.nio.charset.Charset;

/**
 * From the time of onset of a channel number and a program, I search the data
 * of XML. And display a title.
 *
 * @author dosdiaopfhj
 */
public class Main {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.log(Level.INFO, "第1引数は設定ファイルのパス。 第2引数は設定ファイルの文字コード。");
        String configFileDirectory = null;
        Charset configFileCharCode = null;
        try {
            configFileDirectory = args[0];
            configFileCharCode = Charset.forName(args[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new Error("引数が不足しています。強制終了します。", ex);
        }

        Config cfg = new ConfigFactory(configFileDirectory, configFileCharCode).getConfig();

        try {

            Updater u = new Updater(cfg);

            u.update();

        } catch (SQLException ex) {
            throw new Error("SQL実行中に問題が発生しました。強制終了します。", ex);
        }

        System.exit(0);
    }

}
