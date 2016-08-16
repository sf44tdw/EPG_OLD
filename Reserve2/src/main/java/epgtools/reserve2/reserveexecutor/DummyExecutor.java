/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve2.reserveexecutor;

import epgtools.loggerconfigurator.LoggerConfigurator;
import epgtools.reserve2.programme.Programme;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dosdiaopfhj
 */
public class DummyExecutor implements ReserveExecutorInterface {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    @Override
    public boolean executeReserveCommand(Programme p, String recordCommand) {
        log.log(Level.INFO, "番組情報={0}", p.toString());
        log.log(Level.INFO, "録画コマンド={0}", recordCommand);
        log.log(Level.INFO, "この処理はダミーです。何もせず終了します。");
        return true;
    }

}
