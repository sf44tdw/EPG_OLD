/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.jdbcaccessor.util;

import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 現在接続中の環境に関して、DBへの接続ユーザ名、クエリのパラメータ指定法がログ上に表示される。
 * @author dosdiaopfhj
 */
public class GetDBInfo {
    private static final Logger log = LoggerConfigurator.getCallerLogger();
    /**
     * メッセージが表示される環境では、ストアドプロシージャに値を入れるときにフィールド名で指定できる。
     */
    public final void getsupportsNamedParameters() {
        try {
            DatabaseMetaData dbmd = JDBCAccessor.getInstance().getCon().getMetaData();
            if (dbmd.supportsNamedParameters() == true) {
                log.log(Level.INFO, "NAMED PARAMETERS FOR CALLABLE"
                        + "STATEMENTS IS SUPPORTED");
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 接続に使っているユーザー名を表示する。
     */
    public final void getLoginUser() {
        try (ResultSet rs = JDBCAccessor.getInstance().getCon().prepareCall("select CURRENT_USER").executeQuery()) {
            while (rs.next()) {
                log.log(Level.INFO, rs.getString(1));
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
}
