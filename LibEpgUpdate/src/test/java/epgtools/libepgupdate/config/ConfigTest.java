/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.config;

import epgtools.libepgupdate.common.Const;
import epgtools.libepgupdate.common.EqualsChecker;
import epgtools.libepgupdate.updator.config.Config;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author normal
 */
public class ConfigTest {

    public ConfigTest() {
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

    private Config getInstance() {
        Config temp = new Config(new DummyConnection("snfupwqhfu"), Const.getTESTDATADIR(), Const.CHARCODE, Const.getDummyPaidBroadcastings());
        return temp;
    }

    private Config getInstance2() {
        Config temp = new Config(new DummyConnection("snfupwewffu"), new File("C:/"), Const.CHARCODE, Const.getDummyPaidBroadcastings());
        return temp;
    }

    /**
     * Test of getCon method, of class Config.
     */
    @Test(expected = NullPointerException.class)
    public void getInstance_NoCon() {
        System.out.println("getInstance_NoCon");
        Config instance = new Config(null, Const.getTESTDATADIR(), Const.CHARCODE, Const.getDummyPaidBroadcastings());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_NoXMLDIR() {
        System.out.println("getInstance_NoXMLDIR");
        Config temp = new Config(new DummyConnection("snfupwqhfu"), new File("safblisg"), Const.CHARCODE, Const.getDummyPaidBroadcastings());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_NoXMLDIR_Null() {
        System.out.println("getInstance_NoXMLDIR_Null");
        Config temp = new Config(new DummyConnection("snfupwqhfu"), null, Const.CHARCODE, Const.getDummyPaidBroadcastings());
    }

    @Test(expected = NullPointerException.class)
    public void getInstance_NullCharCode() {
        System.out.println("getInstance_NullCharCode");
        Config temp = new Config(new DummyConnection("snfupwqhfu"), Const.getTESTDATADIR(), null, Const.getDummyPaidBroadcastings());

    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_NullSet() {
        System.out.println("getInstance_NullSet");
        Config temp = new Config(new DummyConnection("snfupwqhfu"), Const.getTESTDATADIR(), Const.CHARCODE, null);

    }

    /**
     * Test of getCon method, of class Config.
     */
    @Test
    public void getConnection() {
        System.out.println("getConnection");
        Config instance = getInstance();
        java.sql.Connection expResult = new DummyConnection("snfupwqhfu");
        java.sql.Connection result = instance.getConention();
        assertEquals(expResult, result);
    }

    /**
     * Test of getXMLDirectory method, of class Config.
     */
    @Test
    public void testGetxMLDirectory() {
        System.out.println("getxMLDirectory");
        Config instance = this.getInstance();
        File expResult = new File(Const.getTESTDATADIR().getAbsolutePath());
        File result = instance.getXMLDirectory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getXMLFileCharCode method, of class Config.
     */
    @Test
    public void testGetxMLFileCharCode() {
        System.out.println("getxMLFileCharCode");
        Config instance = this.getInstance();
        Charset expResult = Const.CHARCODE;
        Charset result = instance.getXMLFileCharCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPaidBroadcasting method, of class Config.
     */
    @Test
    public void testGetPaidBroadcasting() {
        System.out.println("getPaidBroadcasting");
        Config instance = this.getInstance();
        Set<Integer> expResult = Const.getDummyPaidBroadcastings();
        Set<Integer> result = instance.getPaidBroadcastings();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Config.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Config instance = this.getInstance();
        int expResult = 0;
        int result = instance.hashCode();
        assertFalse(expResult == result);
    }

    /**
     * Test of equals method, of class Config.
     */
    @Test
    public void testEquals_Same() {
        System.out.println("equals_Same");
        EqualsChecker<Config> ec = new EqualsChecker<>();
        Config instance1 = this.getInstance();
        Config instance2 = this.getInstance();
        Config instance3 = this.getInstance();
        assertTrue(ec.check(instance1, instance2, instance3));
    }

    /**
     * Test of equals method, of class Config.
     */
    @Test
    public void testEquals_NotSame() {
        System.out.println("equals_same");
        Config instance1 = this.getInstance();
        Config instance2 = this.getInstance2();
        assertFalse(instance1.equals(instance2));
        assertFalse(instance2.equals(instance1));
    }

    /**
     * Test of equals method, of class Config.
     */
    @Test
    public void testtoString() {
        Config instance1 = this.getInstance();
        String s = instance1.toString();
        System.out.println(s);
        assertNotNull(s);
    }

}
