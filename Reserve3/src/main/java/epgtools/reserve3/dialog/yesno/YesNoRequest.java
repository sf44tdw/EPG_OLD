/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.yesno;

import epgtools.reserve3.dialog.Inputprocessor.InputRequest;



/**
 * 事前に設定された文字列か、YかNのどちらかを入力するよう促す文字列を表示する。
 * @author dosdiaopfhj
 */
public class YesNoRequest implements InputRequest {

    private final String preInputMessage;

    public YesNoRequest(String preInputMessage) {
        this.preInputMessage = preInputMessage;
    }

    /**
     * コンストラクタで受け取った文字列を入力処理に受け渡す。
     * @return コンストラクタで受け取った文字列。nullか空文字列の場合はY/N?となる。
     */
    @Override
    public String inputRequestMessages() {
        if ((this.preInputMessage != null) && (!this.preInputMessage.equals(""))) {
            return this.preInputMessage;
        } else {
            return "Y/N?";
        }
    }

}
