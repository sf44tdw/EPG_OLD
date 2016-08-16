/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.common;

import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * テスト用の共通設定。
 *
 * @author dosdiaopfhj
 */
public final class Initializer {

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
     * 設定ファイル項目名 ログ出力設定ファイルのパス
     */
    private static final String LOGGING_PROPERTY_PATH = "LoggingPropertyPath";

    private static final Logger log = LoggerConfigurator.getCallerLogger();
//    private final File PF = new File("/home/normal/ProgrammeReserver/ProgrammeReserver.properties");
    private final File PF = new File("j:/ProgrammeGetter.properties");
    private Reader R;

    private String url = "";
    private String user = "";
    private String password = "";
    private JDBCAccessor Accesser;

    public final synchronized boolean loadConfig() {
        try {
            //設定ファイルのロード
            R = new BufferedReader(new InputStreamReader(new FileInputStream(PF), "UTF-8"));
            Properties conf = new Properties();
            conf.load(R);
            url = conf.getProperty(Initializer.DB_URL);
            user = conf.getProperty(Initializer.DB_USER);
            password = conf.getProperty(Initializer.DB_PASSWORD);
            LoggerConfigurator.setlogproperties(new File(conf.getProperty(Initializer.LOGGING_PROPERTY_PATH)));
            R.close();

            this.Accesser = JDBCAccessor.getInstance();
            this.Accesser.connect(url, user, password);

            return true;
        } catch (UnsupportedEncodingException ex) {
            log.log(Level.SEVERE, "設定のロード中にエラーが発生しました。", ex);
            return false;
        } catch (IOException ex) {
            log.log(Level.SEVERE, "設定のロード中にエラーが発生しました。", ex);
            return false;
        }
    }

    public final synchronized JDBCAccessor getAccessor() {
        return this.Accesser;
    }

}
