/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.loggerconfigurator;

import java.io.File;
import java.util.logging.Logger;
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
public class LoggerConfiguratorTest {
    
    public LoggerConfiguratorTest() {
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
     * Test of getCallerLogger method, of class LoggerConfigurator.
     */
    @Test
    public void testGetCallerLogger() {
        System.out.println("getCallerLogger");
        Logger result = LoggerConfigurator.getCallerLogger();
        assertTrue(result!=null);
    }

    /**
     * Test of setlogproperties method, of class LoggerConfigurator.
     */
    @Test
    public void testSetlogproperties() throws Exception {
        System.out.println("setlogproperties");
        File f = new File("./properties/logger.properties");
        LoggerConfigurator.setlogproperties(f);
    }
    
}
