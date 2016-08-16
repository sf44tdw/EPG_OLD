/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dbinserter;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.Connection;
import DataExtractor.EpgData;

/**
 * SQLの実行を行う。
 *
 * @author dosdiaopfhj
 * @param <T> EPGDataインタフェースを実装したクラス。
 */
public abstract class AbstractInserter<T extends EpgData> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final Connection con;
    private final List<T> data;

    /**
     * @param con データベース接続オブジェクト
     * @param data データベースに追加したいレコードが格納されたリスト。
     */
    public AbstractInserter(Connection con, List<T> data) {
        this.con = con;
        this.data = data;
    }

    /**
     * リストから1件ずつレコードを取り出し、データベースに追加する。
     * @throws java.sql.SQLException SQL実行の際に問題が起きた。(何か問題があったときはロールバックしたいので、最上位まで例外を投げる。)
     */
    public final synchronized void execute() throws SQLException {

        Iterator<T> it = this.data.iterator();
        while (it.hasNext()) {
            T record = it.next();
            this.processRecord(record, this.con);
        }
    }

    /**
     * レコードをSQLに仕立て、実行する。execute() によって呼び出される。
     *
     * @param record レコード
     * @param con データベース接続オブジェクト
     * @throws java.sql.SQLException
     */
    abstract protected void processRecord(T record, Connection con) throws SQLException;

}
