/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.channel;

import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import epgtools.reserve3.dialog.Inputprocessor.InputRequest;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author dosdiaopfhj
 */
public class ChannelRequest implements InputRequest {

    private static final String CRLF = System.getProperty("line.separator");
    private final Map<Integer, Useablechannels_ReadOnly> map;

    public ChannelRequest(Map<Integer, Useablechannels_ReadOnly> map) {
        this.map = map;
    }

    @Override
    public String inputRequestMessages() {
        StringBuilder strings = new StringBuilder();
        if (this.map != null) {
            Iterator<Integer> it = this.map.keySet().iterator();
            //チャンネルの一覧を表示
            while (it.hasNext()) {
                int o = it.next();
                strings.append(new ChannelStringMaker().makeChannelString(map.get(o)));
                strings.append(CRLF);
            }
            strings.append("物理チャンネル番号を入力してください。");
            strings.append(CRLF);
            return strings.toString();
        } else {
            return "";
        }
    }
}
