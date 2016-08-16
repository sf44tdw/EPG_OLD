/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.Programme;


import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import epgtools.reserve3.dialog.Inputprocessor.InputRequest;
import epgtools.reserve3.dialog.channel.ChannelStringMaker;
/**
 *
 * @author dosdiaopfhj
 */
public class ProgrammeRequest implements InputRequest {

    private static final String CRLF = System.getProperty("line.separator");
    private final Useablechannels_ReadOnly ch;
    

    public ProgrammeRequest(Useablechannels_ReadOnly ch) {
        this.ch = ch;
    }

    @Override
    public String inputRequestMessages() {
        StringBuilder strings = new StringBuilder();
        if (this.ch != null) {
            strings.append("チャンネル情報=").append(new ChannelStringMaker().makeChannelString(ch));
            strings.append(CRLF);
            strings.append("上記のチャンネルから、番組を検索します。yyyy-mm-dd hh:mm:ssの形式で、放送開始時刻を入力してください。");
            strings.append(CRLF);
            return strings.toString();
        } else {
            return "";
        }
    }
    
}
