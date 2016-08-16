/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.commandexecutor;

import org.junit.Test;

/**
 *
 * @author normal
 */
public class CommandExecutorTest {
    


    /**
     * Test of execCommand method, of class CommandExecutor.
     */
    @Test
    public void testExecCommand_String() throws Exception {
        System.out.println("execCommand");
        String cmd = "java";
        CommandExecutor instance = new CommandExecutor();
        CommandResult result = instance.execCommand(cmd);
        System.out.println(result.toString());
    }

    /**
     * Test of execCommand method, of class CommandExecutor.
     */
    @Test
    public void testExecCommand_StringArr() {
        System.out.println("execCommand_arr");
        String[] cmds = new String[]{"at","-t","211201010000","-f","/usr/local/bin/ptrec.sh"};
        CommandExecutor instance = new CommandExecutor();
        CommandResult result = instance.execCommand(cmds);
        System.out.println(result.toString());
    }
    
        /**
     * Test of execCommand method, of class CommandExecutor.
     */
    @Test
    public void testExecCommand_StringArr02() {
        System.out.println("execCommand_arr02");
        String[] cmds = new String[]{"C:/lame3.99.5-64/lame.exe","--help"};
        CommandExecutor instance = new CommandExecutor();
        CommandResult result = instance.execCommand(cmds);
        System.out.println(result.toString());
    }
}
