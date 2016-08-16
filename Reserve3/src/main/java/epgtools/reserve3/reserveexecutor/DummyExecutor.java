/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.reserveexecutor;

import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dosdiaopfhj
 */
public class DummyExecutor implements ReserveExecutor {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    @Override
    public boolean executeReserveCommand(ProgrammeChannelNo_ReadOnly p, String recordCommand) {
        log.log(Level.INFO, "録画対象:{0}",p.toString());
        log.log(Level.INFO, "コマンド:{0}",recordCommand);
        log.log(Level.INFO, "この処理はダミーです。何もせず終了します。");
        return true;
    }

}
