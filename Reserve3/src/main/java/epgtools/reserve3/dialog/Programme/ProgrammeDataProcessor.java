/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.Programme;

import epgtools.epgdbbean.bean.channel.Useablechannels_ReadOnly;
import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import epgtools.epgdbbean.bean.programme.ProgrammeGetter;
import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve3.dialog.Inputprocessor.GenerationInabilityException;
import epgtools.reserve3.dialog.Inputprocessor.PostProcessor;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 *
 * @author dosdiaopfhj
 */
public class ProgrammeDataProcessor
        implements PostProcessor<ProgrammeChannelNo_ReadOnly> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final Useablechannels_ReadOnly ch;
    private final ProgrammeGetter p;

    /**
     *
     * @param ch 放送局の情報
     * @param p 指定されたチャンネルの、指定時刻に始まる番組情報を取得するクラス。
     */
    public ProgrammeDataProcessor(Useablechannels_ReadOnly ch, ProgrammeGetter p) {
        this.ch = ch;
        this.p = p;
    }

    @Override
    public ProgrammeChannelNo_ReadOnly process(String s) throws IllegalArgumentException, GenerationInabilityException {
        if (this.ch == null || this.p == null) {
            throw new GenerationInabilityException("オブジェクトの生成手段がありません。");
        } else {
            try {
                Timestamp ts = Timestamp.valueOf(s);
                ProgrammeChannelNo_ReadOnly pcn = this.p.getProgramme(ch.getChannel_id(), ch.getChannel_no(), ts);
                if (pcn == null) {
                    throw new IllegalArgumentException("番組が見つかりませんでした。");
                } else {
                    return pcn;
                }
            } catch (SQLException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
    }

}
