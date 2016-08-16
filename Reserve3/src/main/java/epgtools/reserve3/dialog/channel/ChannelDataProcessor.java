/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.channel;

import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve3.dialog.Inputprocessor.GenerationInabilityException;
import epgtools.reserve3.dialog.Inputprocessor.PostProcessor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dosdiaopfhj
 */
public class ChannelDataProcessor
        implements PostProcessor<Useablechannels_ReadOnly> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final Map<Integer, Useablechannels_ReadOnly> map;

    /**
     * @param map チャンネルの一覧
     */
    public ChannelDataProcessor(Map<Integer, Useablechannels_ReadOnly> map) {
        this.map = map;
    }

    @Override
    public Useablechannels_ReadOnly process(String s) throws IllegalArgumentException, GenerationInabilityException {
        Useablechannels_ReadOnly ch = null;
        if (this.map != null) {
            try {
                log.log(Level.INFO, "入力された物理チャンネル番号:{0}", s);
                int chno = Integer.parseInt(s);
                if (map.containsKey(chno)) {
                    ch = map.get(chno);
                    log.log(Level.INFO, "選択されたチャンネル:{0}", new ChannelStringMaker().makeChannelString(ch));
                } else {
                    throw new IllegalArgumentException("一覧に無いチャンネル番号です。");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("入力内容の変換に失敗しました。", e);
            }
        } else {
            throw new GenerationInabilityException("オブジェクトを生成する手段がありません。");
        }
        return ch;
    }
}
