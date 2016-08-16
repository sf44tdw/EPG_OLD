/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.Channel;

import Common.EqualsChecker;
import epgtools.epgdbbean.bean.channel.Useablechannels;
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
public class UseablechannelsTest {

    public UseablechannelsTest() {
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
     * Test of getChannel_id method, of class Useablechannels.
     */
    @Test
    public void testChannel_id() {
        System.out.println("Channel_id");
        Useablechannels instance = new Useablechannels();
        instance.setChannel_id("TEST");
        String expResult = "TEST";
        String result = instance.getChannel_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChannel_id method, of class Useablechannels.
     */
    @Test(expected = NullPointerException.class)
    public void setChannel_id_setNull() {
        System.out.println("setChannel_id_setNull");
        String channel_id = null;
        Useablechannels instance = new Useablechannels();
        instance.setChannel_id(channel_id);
    }

    /**
     * Test of getChannel_no method, of class Useablechannels.
     */
    @Test
    public void testChannel_no() {
        System.out.println("Channel_no");
        Useablechannels instance = new Useablechannels();
        instance.setChannel_no(10);
        Integer expResult = 10;
        Integer result = instance.getChannel_no();
        assertEquals(expResult, result);
    }


    /**
     * Test of setChannel_no method, of class Useablechannels.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChannel_no_setNull() {
        System.out.println("setChannel_no_setNull");
        Integer channel_no = null;
        Useablechannels instance = new Useablechannels();
        instance.setChannel_no(channel_no);
    }

    /**
     * Test of getDisplay_name method, of class Useablechannels.
     */
    @Test
    public void testDisplay_name() {
        System.out.println("Display_name");
        Useablechannels instance = new Useablechannels();
        instance.setDisplay_name("SAMPLE");
        String expResult = "SAMPLE";
        String result = instance.getDisplay_name();
        assertEquals(expResult, result);
    }


    /**
     * Test of getInsert_datetime method, of class Useablechannels.
     */
    @Test
    public void testInsert_datetime() {
        System.out.println("Insert_datetime");
        Useablechannels instance = new Useablechannels();
        instance.setInsert_datetime(new Timestamp(353443566454165413L));
        Timestamp expResult = new Timestamp(353443566454165413L);
        Timestamp result = instance.getInsert_datetime();
        assertEquals(expResult, result);
    }

     @Test(expected = NullPointerException.class)
    public void testSetInsert_datetime_setNull() {
        System.out.println("setInsert_datetime_setNull");
        Useablechannels instance = new Useablechannels();
        instance.setInsert_datetime(null);
    }

    private final EqualsChecker<Useablechannels> ec = new EqualsChecker<>();

    private Useablechannels getInstance() {
        String channel_id = "AAA";
        Integer channel_no = 100;
        String display_name = "BBB";
        Timestamp insert_datetime = new Timestamp(353443566454165413L);

        Useablechannels instance = new Useablechannels();

        instance.setChannel_id(channel_id);
        instance.setChannel_no(channel_no);
        instance.setDisplay_name(display_name);
        instance.setInsert_datetime(insert_datetime);

        return instance;
    }

    /**
     * Test of equals method, of class Useablechannels.
     */
    @Test
    public void testEquals_same() {
        System.out.println("equals_same");
        boolean r = ec.check(this.getInstance(), this.getInstance(), this.getInstance());
        assertTrue(r);
    }

    /**
     * Test of equals method, of class Useablechannels.
     */
    @Test
    public void testEquals_Notsame() {
        System.out.println("equals2_Notsame");
        Useablechannels obj = this.getInstance();
        Useablechannels instance = this.getInstance();
        obj.setChannel_no(181986654);
        boolean expResult = false;
        assertEquals(expResult, instance.equals(obj));
        assertEquals(expResult, obj.equals(instance));
        assertEquals(expResult, instance.equals("STRING"));
    }

    /**
     * Test of hashCode method, of class Useablechannels.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Useablechannels instance = new Useablechannels();
        int expResult = 0;
        int result = instance.hashCode();
        assertFalse(expResult==result);
    }

}
