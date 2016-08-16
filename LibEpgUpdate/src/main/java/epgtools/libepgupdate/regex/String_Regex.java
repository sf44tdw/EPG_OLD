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
 * 正規表現のコンパイルを行うだけの仮想クラス。
 * デフォルトでは、フラグなしのコンパイルを行うようになっている。(コンストラクタでフラグつきコンパイルを行うクラスを指定すれば、フラグつきコンパイルも可能)
 * コンパイルが正常に終了したかどうかのチェックくらいは出来る。
 * コンパイルに失敗すると、このクラスが管理している正規表現パターンオブジェクトはnullになる。
 *
 * @author kido
 */
public abstract class String_Regex {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private static final long serialVersionUID = 1L;
    private String RegexPattern = "";
    private Compile_Frontend com = null;
    private Pattern pattern = null;

    public String_Regex() {
        this(new Compile_Frontend());
    }

    public String_Regex(Compile_Frontend com) {
        log.log(Level.WARNING, "この文字が表示されない場合、このクラスを直接的あるいは間接的(これを使ったライブラリを使ってるとか)"
                + "に使用しているプロジェクトに、このライブラリが登録されていない場合がある。");
        this.com = com;
    }

    /**
     * 正規表現パターンをセット。有効なものならコンパイル
     *
     * @param RegexPattern 正規表現
     */
    public synchronized final void setRegexPattern(String RegexPattern) {
        this.RegexPattern = RegexPattern;
        try {
            if (this.com == null) {
                log.log(Level.SEVERE, "  正規表現をコンパイルするオブジェクトが見つかりません。");
                this.pattern = null;
            } else {
                this.pattern = this.com.compile(RegexPattern);
            }
        } catch (Exception e) {
            this.pattern = null;
            log.log(Level.SEVERE, "  正規表現パターンのコンパイルに失敗しました。", e);
        }
    }

    /**
     * 現在セットされている正規表現パターンを取得
     *
     * @return セットされている正規表現
     */
    public synchronized final String getRegexPattern() {
        return RegexPattern;
    }

    /**
     * サブクラスで、正規表現パターンオブジェクトを取得
     *
     * @return このクラスが保持している正規表現パターンオブジェクト
     */
    protected synchronized final Pattern getPattern() {
        return pattern;
    }

}
