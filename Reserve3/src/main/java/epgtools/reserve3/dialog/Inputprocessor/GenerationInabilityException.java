/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.Inputprocessor;

/**
 * AbstractInputProcessorのサブクラスにおいて、実装されたprocess()が戻り値とするオブジェクトを生成する手段を持っていないときに投げる例外。
 * @author dosdiaopfhj
 */
public class GenerationInabilityException extends Exception {

    private static final long serialVersionUID = 45564684618435164L;

    public GenerationInabilityException() {
    }

    public GenerationInabilityException(String message) {
        super(message);
    }

    public GenerationInabilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenerationInabilityException(Throwable cause) {
        super(cause);
    }

    public GenerationInabilityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
