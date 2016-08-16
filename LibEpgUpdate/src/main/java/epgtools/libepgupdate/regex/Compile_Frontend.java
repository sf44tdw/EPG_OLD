/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.regex;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * String_Regex.isPattern_Exist()が、正規表現をコンパイルするために使うクラス。
 * デフォルトではフラグなしでコンパイルするようになっている。
 * (フラグの値を設定する方法については、マジックナンバーにするか、サブクラスで別途用意する必要がある。)
 **/
public class Compile_Frontend {
    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * 正規表現をコンパイルし、取得するために使うメソッド。
     * @param RegexPattern 正規表現
     * @return  コンパイルに成功すれば正規表現オブジェクト。そうでなければnull.
     */
    public final synchronized Pattern compile(String RegexPattern) {
        Pattern Ret = null;
        try {
            if (RegexPattern == null ? "" == null : RegexPattern.equals("")) {
                log.log(Level.SEVERE, "  正規表現パターンがありません。");
                Ret = null;
            } else {
                Ret = this._compile(RegexPattern);
            }
            return Ret;
        } catch (Exception e) {
            log.log(Level.SEVERE, "  正規表現パターンのコンパイルに失敗しました。",e);
            return null;
        }
    }

    /**
     * 正規表現をコンパイルするために使うメソッド。
     * デフォルトではフラグなしでコンパイルするようになっている。
     * フラグありでコンパイルしたい場合は、このメソッドをオーバーライドする。
     * (フラグの値を設定する方法については、マジックナンバーにするか、サブクラスで別途用意する必要がある。)
     * @param RegexPattern 正規表現
     * @return  正規表現オブジェクト。
     */
    protected synchronized Pattern _compile(String RegexPattern) {
        return Pattern.compile(RegexPattern);
    }

    public synchronized String getClassName() {
        return this.getClass().getName();
    }
}
