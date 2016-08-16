/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.jdbcaccessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author dosdiaopfhj
 */
public class JDBCAccesserTest {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    public JDBCAccesserTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCon method, of class JDBCAccessor.
     */
    @Test
    public void testGetCon() {
        try {
            System.out.println("getCon");
            //実行前に、適当なDBへのアドレス、ユーザー名、パスワードを記述した設定ファイルを用意し、そのパスを設定すること。
            File PF=new File ("h:/EpgUpdater.properties");
            Reader R = new BufferedReader(new InputStreamReader(new FileInputStream(PF), "UTF8"));
            Properties conf = new Properties();
            conf.load(R);
            String url = conf.getProperty("url");
            String user = conf.getProperty("user");
            String password = conf.getProperty("password");
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            JDBCAccessor instance = JDBCAccessor.getInstance();
            instance.connect(url, user, password);
            java.sql.Connection result = instance.getCon();
            assertNotNull(result);
            JDBCAccessor.getInstance().close();
        } catch (IOException ex) {
            Logger.getLogger(JDBCAccesserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of getInstance method, of class JDBCAccessor.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        JDBCAccessor result = JDBCAccessor.getInstance();
        assertNotNull(result);
        JDBCAccessor.getInstance().close();
    }
    

}
