/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.jdbcaccessor.jdbcaccessor;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.loggerconfigurator.LoggerConfigurator;

/**
 *
 * @author dosdiaopfhj
 */
public class JDBCAccessor implements AutoCloseable {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private static JDBCAccessor instance;
    private static java.sql.Connection con = null;

    private JDBCAccessor() {
        // 最初はインスタンスを生成しない
        instance = null;
    }

    public static synchronized JDBCAccessor getInstance() {
        // メソッド呼び出しがあったときに、
        // 初めてインスタンスを生成
        if (instance == null) {
            instance = new JDBCAccessor();
        }
        return instance;
    }

    /**
     * jdbcでDBに接続する。既に接続中の場合は一度クローズして再接続を試みる。
     *
     * @author dosdiaopfhj
     * @see java.lang.Class.forName
     * @param url 接続文字列(DB名を含む)
     * @param user DBのユーザ名
     * @param password DBのパスワード
     *
     */
    public final synchronized void connect(String url, String user, String password) {
        try {
            this.close();
            // DBに接続
            con = DriverManager.getConnection(url, user, password);
            log.log(Level.FINE, "DB接続成功。");
        } catch (SQLException ex) {
            con = null;
            log.log(Level.SEVERE, "DB接続失敗。", ex);
        }
    }

    /**
     * 接続オブジェクトを取得する
     *
     * @return 接続オブジェクト。未接続の状態ではnull
     */
    public final synchronized java.sql.Connection getCon() {
        return con;
    }

    /**
     * DB接続を終了し、接続オブジェクトを開放。 開放済みのときは何もしない。
     *
     */
    @Override
    public final synchronized void close() {
        log.log(Level.FINE, "DB接続をクローズします。");
        try {
            if (con != null) {
                con.close();
                log.log(Level.FINE, "DBクローズ成功。");
            } else {
                log.log(Level.FINE, "接続中のDBが存在しないため、何もせず終了します。");
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "DBクローズ失敗。", ex);
        } finally {
            con = null;
        }
    }

}
