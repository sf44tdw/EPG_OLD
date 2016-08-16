/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve2.reserveexecutor;

import epgtools.reserve2.programme.Programme;

/**
 *
 * @author normal
 */
public interface ReserveExecutorInterface {

    public boolean executeReserveCommand(Programme p, String recordCommand);
}
