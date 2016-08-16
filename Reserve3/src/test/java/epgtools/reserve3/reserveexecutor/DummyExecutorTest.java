/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.reserveexecutor;

import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author normal
 */
public class DummyExecutorTest {
    
    public DummyExecutorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of executeReserveCommand method, of class DummyExecutor.
     */
    @Test
    public void testExecuteReserveCommand() {
        System.out.println("executeReserveCommand");
        ProgrammeChannelNo_ReadOnly p = null;
        String recordCommand = "";
        DummyExecutor instance = new DummyExecutor();
        boolean expResult = false;
        boolean result = instance.executeReserveCommand(p, recordCommand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
