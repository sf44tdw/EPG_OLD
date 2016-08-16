/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import epgtools.libepgupdate.common.Const;
import java.io.File;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author dosdiaopfhj
 */
public class XMLLoaderTest {

    public XMLLoaderTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCharset method, of class XmlLoader.
     */
    @Test
    public void testGetCharset() {
        System.out.println("getCharset");
        XmlLoader instance = new XmlLoader(Const.CHARCODE);
        String expResult = "UTF-8";
        String result = instance.getCharset().name();
        assertEquals(expResult, result);
    }

    /**
     * Test of Load method, of class XmlLoader.
     */
    @Test
    public void testLoad() {
        System.out.println("Load");
        File F = Const.getTESTDATA_XML_1();
        XmlLoader instance = new XmlLoader(Const.CHARCODE);
        Document result = instance.Load(F);
        System.out.println(result.hashCode());
    }

}
