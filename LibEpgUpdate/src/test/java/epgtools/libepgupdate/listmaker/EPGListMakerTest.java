/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import epgtools.libepgupdate.common.Const;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author normal
 */
public class EPGListMakerTest {

    public EPGListMakerTest() {
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
     * Test of seek method, of class EpgListMaker.
     */
    @Test
    public void testSeek() {
        System.out.println("seek");
        EpgListMaker instance = new EpgListMaker(Const.getXMLTESTDATADIR(), Const.CHARCODE);
        List<Document> result = instance.seek();
        assertEquals(2, result.size());
    }

}
