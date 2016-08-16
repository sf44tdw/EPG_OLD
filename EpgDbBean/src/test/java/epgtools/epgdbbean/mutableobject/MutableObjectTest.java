/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MutableObject;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dosdiaopfhj
 */
public class MutableObjectTest {

    private final String STRINGPROP1 = "STR1";
    private final String STRINGPROP2 = "STR2";

    private final Integer INGEGERPROP1 = 1;
    private final Integer INGEGERPROP2 = 2;

    public MutableObjectTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEquals_EqualsThis() {
        MutableObjectImpl target1 = new MutableObjectImpl();
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");

        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        assertTrue(target1.equals(target1));
    }

    @Test
    public void testEquals_NotEqualsNull() {
        MutableObjectImpl target1 = new MutableObjectImpl();
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");

        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        assertFalse(target1.equals(null));
    }

    @Test
    public void testEquals_NotEqualsOtherType() {
        MutableObjectImpl target1 = new MutableObjectImpl();
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");

        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        assertFalse(target1.equals("DUMMY"));
    }

    /**
     * Test of equals method, of class MutableObject.
     */
    @Test
    public void testEquals_intValue() {
        System.out.println("***************************************testEquals_intValue_Start**************************************");

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        MutableObjectImpl target1 = new MutableObjectImpl();
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        System.out.println(target1.toString());

        MutableObjectImpl target2 = new MutableObjectImpl();
        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection1);
        target2.setMapProp(map1);
        System.out.println(target2.toString());
        assertEquals(target1.hashCode(), target2.hashCode());

        assertTrue(target1.equals(target2));
        assertTrue(target2.equals(target1));

        target2.setIntegerProp(INGEGERPROP2);
        System.out.println(target2.toString());

        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));

        target2.setIntegerProp(null);
        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));

        System.out.println("***************************************testEquals_intValue_End***************************************");
    }

    @Test
    public void testEquals_StringValue() {
        System.out.println("***************************************testEquals_StringValue_Start**************************************");

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        MutableObjectImpl target1 = new MutableObjectImpl();
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        System.out.println(target1.toString());

        MutableObjectImpl target2 = new MutableObjectImpl();
        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection1);
        target2.setMapProp(map1);
        System.out.println(target2.toString());
        assertEquals(target1.hashCode(), target2.hashCode());

        assertTrue(target1.equals(target2));
        assertTrue(target2.equals(target1));

        target2.setStringProp(STRINGPROP2);
        System.out.println(target2.toString());

        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));

        target2.setStringProp(null);
        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));
        System.out.println("***************************************testEquals_StringValue_End****************************************");
    }

    @Test
    public void testEquals_CollectionValue() {
        System.out.println("***************************************testEquals_CollectionValue_Start**************************************");

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        MutableObjectImpl target1 = new MutableObjectImpl();
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        System.out.println(target1.toString());

        MutableObjectImpl target2 = new MutableObjectImpl();

        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection1);
        target2.setMapProp(map1);
        System.out.println(target2.toString());
        assertEquals(target1.hashCode(), target2.hashCode());

        assertTrue(target1.equals(target2));
        assertTrue(target2.equals(target1));

        ArrayList<String> collection2 = new ArrayList<>();
        collection2.add("A");
        collection2.add("B");
        collection2.add("C");
        target2.setCollectionProp(collection2);
        System.out.println(target2.toString());

        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));

        target2.setCollectionProp(null);
        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));
        System.out.println("***************************************testEquals_CollectionValue_End****************************************");
    }

    @Test
    public void testEquals_MapValue() {
        System.out.println("***************************************testEquals_MapValue_Start**************************************");

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        MutableObjectImpl target1 = new MutableObjectImpl();
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        System.out.println(target1.toString());

        MutableObjectImpl target2 = new MutableObjectImpl();

        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection1);
        target2.setMapProp(map1);
        System.out.println(target2.toString());
        assertEquals(target1.hashCode(), target2.hashCode());

        assertTrue(target1.equals(target2));
        assertTrue(target2.equals(target1));

        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "X");
        map2.put(2, "Y");
        map2.put(3, "Z");
        target2.setMapProp(map2);
        System.out.println(target2.toString());

        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));

        target2.setMapProp(null);
        assertFalse(target1.equals(target2));
        assertFalse(target2.equals(target1));
        System.out.println("***************************************testEquals_MapValue_End****************************************");
    }

    @Test
    public void testEquals_3Objects() {
        System.out.println("***************************************testEquals_3Objects_Start**************************************");

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");

        MutableObjectImpl target1 = new MutableObjectImpl();
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        System.out.println(target1.toString());

        MutableObjectImpl target2 = new MutableObjectImpl();
        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection1);
        target2.setMapProp(map1);
        System.out.println(target2.toString());

        MutableObjectImpl target3 = new MutableObjectImpl();
        target3.setIntegerProp(INGEGERPROP1);
        target3.setStringProp(STRINGPROP1);
        target3.setCollectionProp(collection1);
        target3.setMapProp(map1);
        System.out.println(target3.toString());

        assertEquals(target1.hashCode(), target2.hashCode());
        assertEquals(target2.hashCode(), target3.hashCode());
        assertEquals(target3.hashCode(), target1.hashCode());

        assertTrue(target1.equals(target2));
        assertTrue(target2.equals(target3));
        assertTrue(target3.equals(target1));

        System.out.println("***************************************testEquals_3Objects_End**************************************");
    }

    /**
     * Test of hashCode method, of class MutableObject.
     */
    @Test
    public void testHashCode() {
        System.out.println("***************************************testHashCode_Start**************************************");
        MutableObjectImpl target1 = new MutableObjectImpl();
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        System.out.println(target1.hashCode());

        MutableObjectImpl target2 = new MutableObjectImpl();
        ArrayList<String> collection2 = new ArrayList<>();
        collection2.add("A");
        collection2.add("B");
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "X");
        map2.put(2, "Y");
        target2.setIntegerProp(INGEGERPROP1);
        target2.setStringProp(STRINGPROP1);
        target2.setCollectionProp(collection2);
        target2.setMapProp(map2);
        System.out.println(target2.hashCode());

        assertEquals(target1.hashCode(), target2.hashCode());

        target2.setIntegerProp(INGEGERPROP2);
        System.out.println(target2.hashCode());

        assertFalse(target1.hashCode() == target2.hashCode());
        System.out.println("***************************************testHashCode_End**************************************");
    }

    /**
     * Test of toString method, of class MutableObject.
     */
    @Test
    public void testToString() {
        System.out.println("***************************************testToString_Start**************************************");
        MutableObjectImpl target1 = new MutableObjectImpl();
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");

        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        String s1 = target1.toString();
        System.out.println(s1);
        assertNotNull(s1);

        target1.setCollectionProp(null);
        target1.setMapProp(map1);
        String s2 = target1.toString();
        System.out.println(s2);
        assertNotNull(s2);

        target1.setCollectionProp(collection1);
        target1.setMapProp(null);
        String s3 = target1.toString();
        System.out.println(s3);
        assertNotNull(s3);

        target1.setIntegerProp(null);
        target1.setStringProp(STRINGPROP1);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        String s4 = target1.toString();
        System.out.println(s4);
        assertNotNull(s4);

        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(null);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        String s5 = target1.toString();
        System.out.println(s5);
        assertNotNull(s5);
        System.out.println("***************************************testToString_End**************************************");
    }

    /**
     */
    @Test
    public void testPropertyChange() {
        System.out.println("***************************************testPropertyChange_Start**************************************");
        MutableObjectImpl target1 = new MutableObjectImpl();

        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "X");
        map1.put(2, "Y");
        TestListener listener = new TestListener();
        target1.addPropertyChangeListener(listener);
        target1.setIntegerProp(INGEGERPROP1);
        target1.setStringProp(STRINGPROP1);
        target1.setIntegerProp(null);
        target1.setStringProp(null);
        target1.setIntegerProp(INGEGERPROP2);
        target1.setStringProp(STRINGPROP2);
        target1.setCollectionProp(collection1);
        target1.setMapProp(map1);
        String s = target1.toString();
        System.out.println(s);
        assertNotNull(s);
        System.out.println("***************************************testPropertyChange_End**************************************");
    }

}
