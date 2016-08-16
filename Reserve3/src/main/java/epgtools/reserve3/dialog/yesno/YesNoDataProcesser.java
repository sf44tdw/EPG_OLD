/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.yesno;


import epgtools.reserve3.dialog.Inputprocessor.GenerationInabilityException;
import epgtools.reserve3.dialog.Inputprocessor.PostProcessor;

/**
 * YもしくはNの1文字のみに反応する。 Yならtrue Nならfalse を返す。 それ以外の入力は無視し、再入力を促す。
 *
 * @author dosdiaopfhj
 */
public class YesNoDataProcesser implements PostProcessor<Boolean> {

    /**
     * @return Yならtrue Nもしくはnullならfalse を返す。それ以外の入力は例外を投げる。
     */
    @Override
    public Boolean process(String s) throws IllegalArgumentException, GenerationInabilityException {
        if (null != s) {
            switch (s) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    throw new IllegalArgumentException("YもしくはN以外が入力されました。");
            }
        } else {
            return false;
        }
    }

}
