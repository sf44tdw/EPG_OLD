/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.Programme;

import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo;
import Common.EqualsChecker;
import java.sql.Timestamp;
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
public class ProgrammeChannelNoTest {

    public ProgrammeChannelNoTest() {
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

    private ProgrammeChannelNo getInstance() {
        String channel_id = "sample";
        Integer channel_no = 555;
        Integer event_id = 666;
        String title = "sample_title";
        Timestamp start_datetime = new Timestamp(1564583513151341352L);
        Timestamp stop_datetime = new Timestamp(1564583513151349999L);

        ProgrammeChannelNo ret = new ProgrammeChannelNo();
        ret.setChannel_id(channel_id);
        ret.setChannel_no(channel_no);
        ret.setEvent_id(event_id);
        ret.setTitle(title);
        ret.setStart_datetime(start_datetime);
        ret.setStop_datetime(stop_datetime);
        return ret;
    }

    private final EqualsChecker<ProgrammeChannelNo> ec = new EqualsChecker<>();

    /**
     * Test of getChannel_id method, of class ProgrammeChannelNo.
     */
    @Test
    public void testChannel_id() {
        System.out.println("Channel_id");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        String expResult = "test";
        instance.setChannel_id("test");
        String result = instance.getChannel_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChannel_id method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChannel_id_Null() {
        System.out.println("setChannel_id_Null");
        String channel_id = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setChannel_id(channel_id);
    }

    /**
     * Test of getChannel_no method, of class ProgrammeChannelNo.
     */
    @Test
    public void testChannel_no() {
        System.out.println("Channel_no");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        Integer expResult = 100;
        instance.setChannel_no(100);
        Integer result = instance.getChannel_no();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChannel_no method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChannel_no_Null() {
        System.out.println("setChannel_no_null");
        Integer channel_no = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setChannel_no(channel_no);
    }

    /**
     * Test of getEvent_id method, of class ProgrammeChannelNo.
     */
    @Test
    public void testEvent_id() {
        System.out.println("Event_id");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        Integer expResult = 258;
        instance.setEvent_id(258);
        Integer result = instance.getEvent_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEvent_id method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetEvent_id_Null() {
        System.out.println("setEvent_id_Null");
        Integer event_id = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setEvent_id(event_id);
    }

    /**
     * Test of getTitle method, of class ProgrammeChannelNo.
     */
    @Test
    public void testTitle() {
        System.out.println("Title");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        String expResult = "ssss";
        instance.setTitle("ssss");
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetTitle_Null() {
        System.out.println("setTitle_Null");
        String title = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setTitle(title);
    }

    /**
     * Test of getStart_datetime method, of class ProgrammeChannelNo.
     */
    @Test
    public void testStart_datetime() {
        System.out.println("Start_datetime");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        Timestamp expResult = new Timestamp(1867453463556489341L);
        instance.setStart_datetime(new Timestamp(1867453463556489341L));
        Timestamp result = instance.getStart_datetime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStart_datetime method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetStart_datetime_Null() {
        System.out.println("setStart_datetime_Null");
        Timestamp start_datetime = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setStart_datetime(start_datetime);
    }

    /**
     * Test of getStop_datetime method, of class ProgrammeChannelNo.
     */
    @Test
    public void testStop_datetime() {
        System.out.println("Stop_datetime");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        Timestamp expResult = new Timestamp(1867453463556489341L);
        instance.setStop_datetime(new Timestamp(1867453463556489341L));
        Timestamp result = instance.getStop_datetime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStop_datetime method, of class ProgrammeChannelNo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetStop_datetime_Null() {
        System.out.println("setStop_datetime_Null");
        Timestamp stop_datetime = null;
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        instance.setStop_datetime(stop_datetime);
    }

    @Test
    public void testvalidDateTime() {
        System.out.println("ValidDateTime");
        Timestamp past = new Timestamp(18679341L);
        Timestamp future = new Timestamp(999999999999888888L);

        ProgrammeChannelNo instance1 = new ProgrammeChannelNo();
        System.out.println("開始=終了");
        instance1.setStart_datetime(past);
        instance1.setStop_datetime(past);

        System.out.println("開始<終了");
        instance1.setStop_datetime(future);

        System.out.println("開始=終了");
        instance1.setStart_datetime(future);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateTime() {
        System.out.println("InvalidDateTime");
        Timestamp past = new Timestamp(18679341L);
        Timestamp future = new Timestamp(999999999999888888L);

        ProgrammeChannelNo instance1 = new ProgrammeChannelNo();
        System.out.println("開始>終了");
        instance1.setStart_datetime(future);
        instance1.setStop_datetime(past);
    }

    /**
     * Test of equals method, of class ProgrammeChannelNo.
     */
    @Test
    public void testEquals_same() {
        System.out.println("equals_same");
        assertTrue(this.ec.check(this.getInstance(), this.getInstance(), this.getInstance()));
    }

    /**
     * Test of equals method, of class ProgrammeChannelNo.
     */
    @Test
    public void testEquals_Notsame() {
        System.out.println("equals_Notsame");
        ProgrammeChannelNo target1 = this.getInstance();
        ProgrammeChannelNo target2 = this.getInstance();
        target2.setChannel_id("sdaigfliwgglbdgajhlfgegdvilgvklsdguioew;jsk.vlsidvipg");
        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));
    }

    /**
     * Test of hashCode method, of class ProgrammeChannelNo.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ProgrammeChannelNo instance = new ProgrammeChannelNo();
        int expResult = 0;
        int result = instance.hashCode();
        assertFalse(expResult == result);
    }

}
