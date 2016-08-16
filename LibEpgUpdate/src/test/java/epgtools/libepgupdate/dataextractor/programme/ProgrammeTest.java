/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.programme;

import DataExtractor.Programme.Programme;
import epgtools.libepgupdate.common.EqualsChecker;
import java.sql.Timestamp;
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
public class ProgrammeTest {

    public ProgrammeTest() {
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

    //チャンネルid
    private static final String ID = "DUMMY_ID";
    //番組id
    private static final int EVENT_ID = 33;
    //番組名
    private static final String TITLE = "DUMMY_TITLE";
    //開始時刻
    private static final java.sql.Timestamp START_DATETIME = new Timestamp(5348734846485336464L);
    //終了時刻
    private static final java.sql.Timestamp STOP_DATETIME = new Timestamp(6348734846485336464L);

    private Programme getInstance() {
        Programme temp = new Programme(ID, EVENT_ID, TITLE, START_DATETIME, STOP_DATETIME);
        return temp;
    }

    //チャンネルid
    private static final String ID2 = "DUMMY_ID";
    //番組id
    private static final int EVENT_ID2 = 33;
    //番組名
    private static final String TITLE2 = "DUMMY_TITLE";
    //開始時刻
    private static final java.sql.Timestamp START_DATETIME2 = new Timestamp(8348734846485336464L);
    //終了時刻
    private static final java.sql.Timestamp STOP_DATETIME2 = new Timestamp(8358734846485336464L);

    private Programme getInstance2() {
        Programme temp = new Programme(ID2, EVENT_ID2, TITLE2, START_DATETIME2, STOP_DATETIME2);
        return temp;
    }

    @Test(expected = IllegalArgumentException.class)
    public void id_Null() {
        System.out.println("id_Null");
        Programme temp = new Programme(null, EVENT_ID, TITLE, START_DATETIME, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void id_Blank() {
        System.out.println("id_Blank");
        Programme temp = new Programme("", EVENT_ID, TITLE, START_DATETIME, STOP_DATETIME);
    }

    @Test
    public void event_Zero() {
        System.out.println("event_Zero");
        Programme temp = new Programme(ID, 0, TITLE, START_DATETIME, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void event_Minus() {
        System.out.println("event_Minus");
        Programme temp = new Programme(ID, -1, TITLE, START_DATETIME, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void title_Null() {
        System.out.println("title_Null");
        Programme temp = new Programme(ID, EVENT_ID, null, START_DATETIME, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void title_Blank() {
        System.out.println("title_Blank");
        Programme temp = new Programme(ID, EVENT_ID, "", START_DATETIME, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startDatetime_Null() {
        System.out.println("startDatetime_Null");
        Programme temp = new Programme(ID, EVENT_ID, TITLE, null, STOP_DATETIME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void stopDatetime_Null() {
        System.out.println("stopDatetime_Null");
        Programme temp = new Programme(ID, EVENT_ID, TITLE, START_DATETIME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void allDatetime_Null() {
        System.out.println("allDatetime_Null");
        Programme temp = new Programme(ID, EVENT_ID, TITLE, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Datetime_StopBeforeStart() {
        System.out.println("Datetime_StopBeforeStart");
        Programme temp = new Programme(ID, EVENT_ID, TITLE, STOP_DATETIME, START_DATETIME);
    }

    /**
     * Test of getId method, of class Programme.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Programme instance = this.getInstance();
        String expResult = ProgrammeTest.ID;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventId method, of class Programme.
     */
    @Test
    public void testGetEventId() {
        System.out.println("getEventId");
        Programme instance = this.getInstance();
        int expResult = ProgrammeTest.EVENT_ID;
        int result = instance.getEventId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class Programme.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Programme instance = this.getInstance();
        String expResult = ProgrammeTest.TITLE;
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDatetime method, of class Programme.
     */
    @Test
    public void testGetStartDatetime() {
        System.out.println("getStartDatetime");
        Programme instance = this.getInstance();
        Timestamp expResult = ProgrammeTest.START_DATETIME;
        Timestamp result = instance.getStartDatetime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStopDatetime method, of class Programme.
     */
    @Test
    public void testGetStopDatetime() {
        System.out.println("getStopDatetime");
        Programme instance = this.getInstance();
        Timestamp expResult = ProgrammeTest.STOP_DATETIME;
        Timestamp result = instance.getStopDatetime();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Programme.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Programme instance = this.getInstance();
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
        EqualsChecker<Programme> ec = new EqualsChecker<>();
        Programme instance1 = this.getInstance();
        Programme instance2 = this.getInstance();
        Programme instance3 = this.getInstance();
        assertTrue(ec.check(instance1, instance2, instance3));
    }

    /**
     * Test of equals method, of class Config.
     */
    @Test
    public void testEquals_NotSame() {
        System.out.println("equals_Notsame");
        Programme instance1 = this.getInstance();
        Programme instance2 = this.getInstance2();
        assertFalse(instance1.equals(instance2));
        assertFalse(instance2.equals(instance1));
    }
    /**
     * Test of toString method, of class Programme.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Programme instance = this.getInstance();
        String expResult = "";
        String result = instance.toString();
        assertFalse(expResult== result);
    }

}