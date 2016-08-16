/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.updator.config;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

/**
 * ディレクトリのパスと文字コードを指定して設定ファイル(Updater用とロガー用)を読み込み、Configオブジェクトの生成とロガーの設定を行う。
 *
 * @author normal
 */
public class ConfigFactory {

    private enum CONFIG_PARAMS {

        /**
         * ログ出力設定ファイル名
         */
        LOGGER_CONFIG_FILE_NAME("logger.properties"),
        /**
         * ログ出力設定ファイルのパス
         */
        LOGGER_CONFIG_FILE_PATH("properties/" + LOGGER_CONFIG_FILE_NAME.getparam()),
        /**
         * 設定ファイル項目名 DBのurl
         */
        DB_URL("url"),
        /**
         * 設定ファイル項目名 DBのユーザー名
         */
        DB_USER("user"),
        /**
         * 設定ファイル項目名 DBのパスワード
         */
        DB_PASSWORD("password"),
        /**
         * 設定ファイル項目名 XMLファイルのあるディレクトリのパス
         */
        XML_DIRECTORY_PATH("XMLPath"),
        /**
         * 設定ファイル項目名 XMLファイルの文字コード
         */
        XML_CHARCODE("XMLcharcode"),
        /**
         * 設定ファイル項目名 見られない(有料)チャンネル一覧
         */
        PAIDBROADCASTING("paidBroadcasting");
        private final String param;

        CONFIG_PARAMS(String param) {
            this.param = param;
        }

        /**
         * @return SQL文
         */
        public String getparam() {
            return param;
        }
    }

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final File configFile;
    private final Charset configFileCharCode;

    /**
     * 設定ファイルの各項目の読み込みチェック。チェック対象がnullもしくは空文字列ならIllegalArgumentExceptionを投げる。
     *
     * @param target チェック対象。
     * @param param エラーメッセージに付け加える項目名
     */
    private void nullCheckToConfigParameters(Object target, String param) throws IllegalArgumentException {
        /**
         * 設定ファイル読み込み時エラーメッセージ
         */
        final MessageFormat ERROR_MESSAGE_READ_CONFIG = new MessageFormat("設定ファイル項目{0}の内容がありません。");
        if (target == null || target.equals("")) {
            throw new IllegalArgumentException(ERROR_MESSAGE_READ_CONFIG.format(new String[]{param}));
        }
    }

    /**
     * @param configFile 設定ファイルのパス。
     * @param configFileCharCode 設定ファイルの文字コード。
     */
    public ConfigFactory(String configFile, Charset configFileCharCode) throws IllegalArgumentException {
        try {
            this.configFile = new File(configFile);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("設定ファイルが確認できません。", ex);
        }
        if (!this.configFile.isFile()) {
            throw new IllegalArgumentException("指定されたパスは存在しないか、ファイルではありません。 パス = " + this.configFile.getAbsolutePath());
        }
        if (configFileCharCode != null) {
            this.configFileCharCode = configFileCharCode;
        } else {
            throw new IllegalArgumentException("文字コードが確認できません。");
        }
    }

    /**
     * ロガーの設定と設定ファイルの読み込みを行う。
     *
     * @return 設定オブジェクト。
     */
    public synchronized Config getConfig() throws IllegalArgumentException {

        log.log(Level.INFO, "ロガー用設定ファイル読み込み開始");
        try {
            LoggerConfigurator.setlogproperties(new File(CONFIG_PARAMS.LOGGER_CONFIG_FILE_PATH.getparam()));
        } catch (IOException ex) {
            log.log(Level.WARNING, "ロガー用設定ファイル読み込み失敗。");
        }
        log.log(Level.INFO, "ロガー用設定ファイル読み込み完了");

        String url = null;
        String user = null;
        String password = null;
        File xMLDirectory = null;
        Charset xMLFileCharCode = null;
        Set<Integer> chSet = null;

        log.log(Level.INFO, "設定ファイル読み込み開始");
        //ネストさせるとクローズしないことがあるので個別に宣言する。
        try (
                FileInputStream fis = new FileInputStream(this.configFile);
                InputStreamReader isr = new InputStreamReader(fis, configFileCharCode);
                Reader R = new BufferedReader(isr);) {
            Properties conf = new Properties();
            conf.load(R);

            url = conf.getProperty(CONFIG_PARAMS.DB_URL.getparam());
            nullCheckToConfigParameters(url, CONFIG_PARAMS.DB_URL.getparam());

            user = conf.getProperty(CONFIG_PARAMS.DB_USER.getparam());
            nullCheckToConfigParameters(user, CONFIG_PARAMS.DB_USER.getparam());

            password = conf.getProperty(CONFIG_PARAMS.DB_PASSWORD.getparam());
            nullCheckToConfigParameters(password, CONFIG_PARAMS.DB_PASSWORD.getparam());

            String xMLDirectory_S = conf.getProperty(CONFIG_PARAMS.XML_DIRECTORY_PATH.getparam());
            nullCheckToConfigParameters(xMLDirectory_S, CONFIG_PARAMS.XML_DIRECTORY_PATH.getparam());
            xMLDirectory = new File(xMLDirectory_S);
            if (!xMLDirectory.isDirectory()) {
                throw new IllegalArgumentException("XMLファイルのあるディレクトリを開けません。");
            }

            xMLFileCharCode = Charset.forName(conf.getProperty(CONFIG_PARAMS.XML_CHARCODE.getparam()));
            nullCheckToConfigParameters(xMLFileCharCode, CONFIG_PARAMS.XML_CHARCODE.getparam());

            String paidBroadcasting = conf.getProperty(CONFIG_PARAMS.PAIDBROADCASTING.getparam());
            chSet = new HashSet<>();
            //一覧が無い場合は空の一覧を作る。
            if (paidBroadcasting != null && !paidBroadcasting.equals("")) {
                String[] PaydChannels = null;
                try {
                    PaydChannels = paidBroadcasting.split(",", 0);
                } catch (PatternSyntaxException ex) {
                    throw new IllegalArgumentException("視聴できないチャンネルの一覧に問題があります。", ex);
                }
                for (String ch_S : PaydChannels) {
                    chSet.add(Integer.parseInt(ch_S));
                }
            }

            log.log(Level.INFO, "設定ファイル読み込み完了");
            log.log(Level.INFO, "XMLDirectory:{0}_charcode:{1}_paidBroadcasting{2}", new String[]{xMLDirectory.getAbsolutePath(), xMLFileCharCode.name(), paidBroadcasting});

            final boolean DEBUG = false;
            if (DEBUG == true) {
                log.log(Level.FINEST, "DB_URL = {0}", url);
                log.log(Level.FINEST, "DB_USER = {0}", user);
                log.log(Level.FINEST, "DB_PASSWORD = {0}", password);
            }

            Connection con = null;

            con = DriverManager.getConnection(url, user, password);

            Config cfg = new Config(con, xMLDirectory, xMLFileCharCode, chSet);

            return cfg;

        } catch (IOException ex) {
            throw new IllegalArgumentException("設定ファイルの読み込み中に入出力エラーが発生しました。", ex);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("設定ファイルの項目、" + CONFIG_PARAMS.PAIDBROADCASTING + "を読み込み中に問題が発生しました。数字以外が混じっています。", ex);
        } catch (IllegalCharsetNameException ex) {
            throw new IllegalArgumentException("設定ファイルの項目、" + CONFIG_PARAMS.XML_CHARCODE + "を読み込み中に問題が発生しました。文字コードではありません。", ex);
        } catch (SQLException ex) {
            throw new IllegalArgumentException("設定ファイルの内容では、DBに接続できません。", ex);
        }

    }

}
