/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.consoleinput;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dosdiaopfhj
 */
public class WatitngTimeTest {

    public WatitngTimeTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTimeout method, of class WatitngTime.
     */
    @Test
    public void testGetTimeout() {
        long tt = 100L;
        WatitngTime target1 = new WatitngTime(100L, TimeUnit.SECONDS);
        assertEquals(tt, target1.getTimeout());
    }

    /**
     * Test of getUnit method, of class WatitngTime.
     */
    @Test
    public void testGetUnit() {
        TimeUnit t = TimeUnit.SECONDS;
        WatitngTime target1 = new WatitngTime(100L, TimeUnit.SECONDS);
        assertEquals(t, target1.getUnit());
        WatitngTime target2 = new WatitngTime(100L, t);
        assertEquals(t, target2.getUnit());
    }

    /**
     * Test of toString method, of class WatitngTime.
     */
    @Test
    public void testToString() {
        WatitngTime target1 = new WatitngTime(100L, TimeUnit.SECONDS);
        System.out.println(target1.toString());
    }

    @Test
    public void testEquals() {
        WatitngTime target1 = new WatitngTime(100L, TimeUnit.SECONDS);
        WatitngTime target2 = new WatitngTime(100L, TimeUnit.SECONDS);
        assertTrue(target1.equals(target1));
        assertTrue(target1.equals(target2));
        assertTrue(!(target1.equals(null)));
        WatitngTime target3 = new WatitngTime(1000L, TimeUnit.SECONDS);
        assertTrue(!(target1.equals(target3)));
        WatitngTime target4 = new WatitngTime(100L, TimeUnit.MICROSECONDS);
        assertTrue(!(target1.equals(target4)));
    }

    @Test
    public void testHashCode() {
        WatitngTime target1 = new WatitngTime(100L, TimeUnit.SECONDS);
        System.out.println(target1.hashCode());
        WatitngTime target2 = new WatitngTime(1000L, TimeUnit.SECONDS);
        System.out.println(target2.hashCode());
        WatitngTime target3 = new WatitngTime(100L, TimeUnit.HOURS);
        System.out.println(target3.hashCode());
        WatitngTime target4 = new WatitngTime(100L, TimeUnit.SECONDS);
        System.out.println(target4.hashCode());
        assertEquals(target1.hashCode(),target4.hashCode());
    }

}
