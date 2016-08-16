/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.regex.stringreplacer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import epgtools.libepgupdate.regex.Compile_Frontend;
import epgtools.libepgupdate.regex.String_Regex;
import epgtools.loggerconfigurator.LoggerConfigurator;

/**
 * 正規表現を用いて、文字列の置き換えを行うクラス
 *
 * @author kido
 */
public class StringReplacer_Regex extends String_Regex {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private static final long serialVersionUID = 1L;
    private String ReplaceTo_Value = "";

    public StringReplacer_Regex() {
        super();
    }

    public StringReplacer_Regex(Compile_Frontend com) {
        super(com);
    }

    /**
     * 置き換えパターンにマッチする部分を置き換える文字列を取得する
     * @return  マッチする部分を置き換える文字列
     */
    public synchronized final String getReplaceTo() {
        return ReplaceTo_Value;
    }

    /**
     * 置き換えパターンにマッチする部分を置き換える文字列を設定する。
     * @param ReplaceTo  マッチする部分を置き換える文字列
     */
    public synchronized final void setReplaceTo(String ReplaceTo) {
        this.ReplaceTo_Value = ReplaceTo;
    }

    /**
     * 置き換えパターンにマッチする部分を置き換える文字列がセットされているかチェックする。空文字でも可
     *
     * @return あり true なし false
     */
    public synchronized final boolean isReplaceTo_Exist() {
        return this.ReplaceTo_Value != null;
    }

    /**
     * 置き換えパターンに当てはまる部分をすべて置き換える
     *
     * @param ReplaceString 置き換え処理を行う文字列
     * @return 置き換え処理が行われた文字列か、空文字列(置き換えパターンが無い場合。マッチした部分を置き換える文字列がセットされていない場合。)
     */
    public synchronized final String replaceAll(String ReplaceString) {
        String s;
        try {
            log.log(Level.FINE, "置き換え前文字列={0}", ReplaceString);
            if (this.getPattern()!=null && this.isReplaceTo_Exist() == true) {
                log.log(Level.FINE,"置き換えパターン={0}",this.getRegexPattern());
                Matcher m = this.getPattern().matcher(ReplaceString);
                s = m.replaceAll(this.getReplaceTo());
            } else {
                s = "";
            }
           log.log(Level.FINE, "置き換え後文字列={0}", s);
            return s;
        } catch (Exception e) {
            log.log(Level.SEVERE," 文字列の置き換え中に問題が発生しました。",e);
            return "";
        }
    }
}
