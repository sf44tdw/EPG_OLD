/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.updator.config;

import java.nio.charset.Charset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author normal
 */
public class ConfigFactoryTest {

    public ConfigFactoryTest() {
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

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_Directory_Null() {
        System.out.println("getInstance_Directory_Null");
        ConfigFactory temp = new ConfigFactory(null, Charset.forName("UTF-8"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_Directory_NotFound() {
        System.out.println("getInstance_Directory_NotFound");
        ConfigFactory temp = new ConfigFactory("Z:/", Charset.forName("UTF-8"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_CharSet_Null() {
        System.out.println("getInstance_CharSet_Null");
        ConfigFactory temp = new ConfigFactory("./example_properties/EpgUpdater.properties", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance_CharSet_NotFound() {
        System.out.println("getInstance_CharSet_NotFound");
        ConfigFactory temp = new ConfigFactory("./example_properties/EpgUpdater.properties", Charset.forName("rkuhuuires"));
    }

//    /**
//     * Test of getConfig method, of class ConfigFactory.
//     */
//    @Test
//    public void testGetConfig() {
//        System.out.println("getConfig");
//        ConfigFactory instance = new ConfigFactory("testproperties/Success_Exist_Paid", Charset.forName("UTF-8"));
//        Config result = instance.getConfig();
//        System.out.println(result.toString());
//        assertFalse(null == result);
//    }
//
//    /**
//     * 実際にDBに接続しない限りテスト不可能。
//     */
//    @Test
//    public void testGetConfig_2() {
//        System.out.println("getConfig_2");
//        ConfigFactory instance = new ConfigFactory("testproperties/Success_No_Paid", Charset.forName("UTF-8"));
//        Config result = instance.getConfig();
//        System.out.println(result.toString());
//        assertFalse(null == result);
//    }
//
//    /**
//     * 実際にDBに接続しない限りテスト不可能。
//     */
//    @Test
//    public void testGetConfig_() {
//        System.out.println("getConfig_2");
//        ConfigFactory instance = new ConfigFactory("testproperties/Success_No_Paid", Charset.forName("UTF-8"));
//        Config result = instance.getConfig();
//        System.out.println(result.toString());
//        assertFalse(null == result);
//    }
//    
    
}
