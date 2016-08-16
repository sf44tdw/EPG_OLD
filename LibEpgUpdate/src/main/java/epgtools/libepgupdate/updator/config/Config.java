/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.updator.config;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Updaterの設定を保持するクラス。
 *
 * @author normal
 */
public final class Config {

    private final java.sql.Connection connection;

    private final File xMLDirectory;
    private final Charset xMLFileCharCode;
    private final Set<Integer> paidBroadcastings;

    /**
     * Updaterの設定を受け取る。
     *
     * @param connection 更新対象のDBへの接続。
     * @param xMLDirectory EPG XMLファイルの入っているディレクトリ。サブディレクトリのファイルは無視する。
     * @param xMLFileCharCode EPG XMLファイルの文字コード。
     * @param paidBroadcastings 視聴できないチャンネルのリスト(主に有料放送)。
     */
    public Config(java.sql.Connection connection, File xMLDirectory, Charset xMLFileCharCode, Set<Integer> paidBroadcastings) throws NullPointerException, IllegalArgumentException {
        if (connection != null) {
            this.connection = connection;
        } else {
            throw new NullPointerException("DBへの接続がありません。");
        }
        if (xMLDirectory != null && xMLDirectory.isDirectory()) {
            this.xMLDirectory = new File(xMLDirectory.getAbsolutePath());
        } else {
            throw new IllegalArgumentException("XMLファイルの入っているディレクトリへのパスが不正です。");
        }
        if (xMLFileCharCode != null) {
            this.xMLFileCharCode = xMLFileCharCode;
        } else {
            throw new NullPointerException("XMLファイルの読み込み用文字コードがありません。");
        }
        if (paidBroadcastings != null && paidBroadcastings.size() >= 0) {
            Set<Integer> tempPaidBroadcastings = new HashSet<>();
            tempPaidBroadcastings.addAll(paidBroadcastings);
            this.paidBroadcastings = Collections.unmodifiableSet(tempPaidBroadcastings);
        } else {
            throw new IllegalArgumentException("視聴できないチャンネルのリストがありません。");
        }
    }

    /**
     * @return DB接続。
     */
    public synchronized java.sql.Connection getConention() {
        return connection;
    }

    /**
     * @return XMLファイルの入っているディレクトリ。
     */
    public synchronized File getXMLDirectory() {
        return new File(xMLDirectory.getAbsolutePath());
    }

    /**
     * @return XMLファイルの文字コード。
     */
    public synchronized Charset getXMLFileCharCode() {
        return xMLFileCharCode;
    }

    /**
     * @return 見られないチャンネルの一覧。
     */
    public synchronized Set<Integer> getPaidBroadcastings() {
        Set<Integer> tempPaidBroadcastings = new HashSet<>();
        tempPaidBroadcastings.addAll(paidBroadcastings);
        return Collections.unmodifiableSet(tempPaidBroadcastings);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.connection);
        hash = 43 * hash + Objects.hashCode(this.xMLDirectory);
        hash = 43 * hash + Objects.hashCode(this.xMLFileCharCode);
        hash = 43 * hash + Objects.hashCode(this.paidBroadcastings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Config other = (Config) obj;
        if (!Objects.equals(this.connection, other.connection)) {
            return false;
        }
        if (!Objects.equals(this.xMLDirectory, other.xMLDirectory)) {
            return false;
        }
        if (!Objects.equals(this.xMLFileCharCode, other.xMLFileCharCode)) {
            return false;
        }
        if (!Objects.equals(this.paidBroadcastings, other.paidBroadcastings)) {
            return false;
        }
        return true;
    }

    /**
     * テキストに変換可能なものを全てダンプする。
     *
     * @return このオブジェクトが保存している内容を文字列に変換したもの。
     */
    @Override
    public synchronized String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
