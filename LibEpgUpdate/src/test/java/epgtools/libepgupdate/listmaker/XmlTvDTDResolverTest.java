/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import epgtools.libepgupdate.common.Const;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 *
 * @author normal
 */
public class XmlTvDTDResolverTest {

   

    public XmlTvDTDResolverTest() {
        Const.setLevelALL();
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
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_1() {
        try {
            System.out.println("ResolveEntity_FileExist_1");
            String publicId =XmlTvDtdResolver.DTD_NAME;
            String systemId =XmlTvDtdResolver.DTD_NAME;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertTrue(result instanceof InputSource);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_2() {
        try {
            System.out.println("ResolveEntity_FileExist_2");
            String publicId;
            publicId = XmlTvDtdResolver.DTD_NAME;
            String systemId = null;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertTrue(result instanceof InputSource);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_2_2() {
        try {
            System.out.println("ResolveEntity_FileExist_2_2");
            String publicId =XmlTvDtdResolver.DTD_NAME;
            String systemId = "fwlfkdhohas;grhgl;rhgiaqh";
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertTrue(result instanceof InputSource);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_3() {
        try {
            System.out.println("ResolveEntity_FileExist_3");
            String publicId = null;
            String systemId =XmlTvDtdResolver.DTD_NAME;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertTrue(result instanceof InputSource);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_3_2() {
        try {
            System.out.println("ResolveEntity_FileExist_3_2");
            String publicId = "dsliadguislguvilhgdklsgaklhug;guo";
            String systemId =XmlTvDtdResolver.DTD_NAME;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertTrue(result instanceof InputSource);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_4() {
        try {
            System.out.println("ResolveEntity_FileExist_4");
            String publicId = null;
            String systemId = null;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertEquals(null, result);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_5() {
        try {
            System.out.println("ResolveEntity_FileExist_5");
            String publicId = "fowih;weohf;w";
            String systemId = "dsegli4ewqup;g";
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertEquals(null, result);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_6() {
        try {
            System.out.println("ResolveEntity_FileExist_6");
            String publicId = "fowih;weohf;w";
            String systemId = null;
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertEquals(null, result);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of resolveEntity method, of class XmlTvDtdResolver.
     */
    @Test
    public void testResolveEntity_FileExist_7() {
        try {
            System.out.println("ResolveEntity_FileExist_7");
            String publicId = "null";
            String systemId = "dsegli4ewqup;g";
            XmlTvDtdResolver instance = new XmlTvDtdResolver();
            InputSource result = instance.resolveEntity(publicId, systemId);
            assertEquals(null, result);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlTvDTDResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
