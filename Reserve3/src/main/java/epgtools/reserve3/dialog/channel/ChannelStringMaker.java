/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.channel;

import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import java.text.MessageFormat;

/**
 *
 * @author dosdiaopfhj
 */
public class ChannelStringMaker {

    /**
     * チャンネル表示用テンプレート
     */
    private static final MessageFormat CHANNEL_FORMAT = new MessageFormat("チャンネルID={0}, 物理チャンネル番号={1,number,#}, 放送局名={2}, 記録日時={3}");

    public String makeChannelString(Useablechannels_ReadOnly channel) {

        final String NL = "NULL";
        String ins = NL;

        if (channel.getInsert_datetime()
                != null) {
            ins = channel.getInsert_datetime().toString();
        }
        Object[] parameters = {channel.getChannel_id(), channel.getChannel_no(), channel.getDisplay_name(), ins};

        return CHANNEL_FORMAT.format(parameters);
    }
}
