/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.channel;

import DataExtractor.Channel.Channel;
import epgtools.libepgupdate.common.EqualsChecker;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author normal
 */
public class ChannelTest {

    public ChannelTest() {
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
    private static final String DUMMY_ID = "DUMMY_ID";
    private static final int DUMMY_CH = 100;
    private static final String DUMMY_NAME = "DUMMY_NAME";

    private Channel getInstance() {
        Channel temp = new Channel(DUMMY_ID, DUMMY_CH, DUMMY_NAME);
        return temp;
    }

    private static final String DUMMY_ID2 = "DUMMY_ID2";
    private static final int DUMMY_CH2 = 200;
    private static final String DUMMY_NAME2 = "DUMMY_NAME2";

    private Channel getInstance2() {
        Channel temp = new Channel(DUMMY_ID2, DUMMY_CH2, DUMMY_NAME2);
        return temp;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testId_Null() {
        System.out.println("testId_Null");
        Channel temp = new Channel(null, DUMMY_CH, DUMMY_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testId_blank() {
        System.out.println("testId_blank");
        Channel temp = new Channel("", DUMMY_CH, DUMMY_NAME);
    }

    @Test
    public void testch_Zero() {
        System.out.println("testch_Zero");
        Channel temp = new Channel(DUMMY_ID, 0, DUMMY_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testch_Minus() {
        System.out.println("testch_Minus");
        Channel temp2 = new Channel(DUMMY_ID, -1, DUMMY_NAME);
    }

    /**
     * Test of getBroadcastingStationName method, of class Channel.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBroadcastingStationName_Null() {
        System.out.println("testBroadcastingStationName_Null");
        Channel temp = new Channel(DUMMY_ID, DUMMY_CH, null);
    }

    /**
     * Test of getBroadcastingStationName method, of class Channel.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBroadcastingStationName_Blank() {
        System.out.println("testBroadcastingStationName_Blank");
        Channel temp = new Channel(DUMMY_ID, DUMMY_CH, "");
    }

    /**
     * Test of getId method, of class Channel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Channel instance = this.getInstance();
        String expResult = ChannelTest.DUMMY_ID;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhysicalChannelNumber method, of class Channel.
     */
    @Test
    public void testGetPhysicalChannelNumber() {
        System.out.println("getPhysicalChannelNumber");
        Channel instance = this.getInstance();
        int expResult = ChannelTest.DUMMY_CH;
        int result = instance.getPhysicalChannelNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBroadcastingStationName method, of class Channel.
     */
    @Test
    public void testGetBroadcastingStationName() {
        System.out.println("getBroadcastingStationName");
        Channel instance = this.getInstance();
        String expResult = ChannelTest.DUMMY_NAME;
        String result = instance.getBroadcastingStationName();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Channel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Channel instance = this.getInstance();
        String expResult = "";
        String result = instance.toString();
        assertFalse((expResult == null ? result == null : expResult.equals(result)));
    }

    /**
     * Test of hashCode method, of class Channel.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Channel instance = this.getInstance();
        int expResult = 0;
        int result = instance.hashCode();
        assertFalse(expResult == result);
    }

    /**
     * Test of equals method, of class Channel.
     */
    @Test
    public void testEquals_Same() {
        System.out.println("equals_Same");
        EqualsChecker<Channel> ec = new EqualsChecker<>();
        Channel t1 = this.getInstance();
        Channel t2 = this.getInstance();
        Channel t3 = this.getInstance();
        assertTrue(ec.check(t1, t2, t3));
    }

    @Test
    public void testEquals_NotSame() {
        System.out.println("equals_NotSame");
        Channel t1 = this.getInstance();
        Channel t2 = this.getInstance2();
        assertFalse(t1.equals(t2) && t2.equals(t1));

    }

}