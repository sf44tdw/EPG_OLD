/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker.fileseeker;

import epgtools.libepgupdate.common.Const;
import epgtools.libepgupdate.common.util.Util;
import epgtools.libepgupdate.listmaker.XmlSuffix;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class FileSeekerTest {

    public FileSeekerTest() {
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
     * Test of isRecursive method, of class FileSeeker.
     */
    @Test
    public void testIsRecursive() {
        System.out.println("isRecursive");
        FileSeeker instance = new FileSeeker(Const.getXMLTESTDATADIR(), XmlSuffix.getFilter());
        assertEquals(true, instance.isRecursive());
    }

    /**
     * Test of setRecursive method, of class FileSeeker.
     */
    @Test
    public void testSetRecursive() {
        System.out.println("setRecursive");
        FileSeeker instance = new FileSeeker(Const.getXMLTESTDATADIR(), XmlSuffix.getFilter());
        assertEquals(true, instance.isRecursive());
        instance.setRecursive(false);
        assertEquals(false, instance.isRecursive());
    }



    /**
     * Test of seek method, of class FileSeeker.
     */
    @Test
    public void testSeek_rec() {
        System.out.println("seek_rec");

        File testDataDir = Const.getTESTDATADIR();
        File recursiveDirConst = Const.getXMLTESTDATADIR_RECURSIVE();

        FileSeeker instance = new FileSeeker(Const.getXMLTESTDATADIR(), XmlSuffix.getFilter());
        instance.setRecursive(true);
        assertEquals(true, instance.isRecursive());
        List<File> expResult = new ArrayList<>();
        expResult.add(Const.getTESTDATA_XML_1());
        expResult.add(Const.getTESTDATA_XML_2());
        expResult.add(Const.getTESTDATA_XML_3());

        List<File> result = instance.seek();

        assertTrue(Util.isSameFileList(expResult, result));
    }

    /**
     * Test of seek method, of class FileSeeker.
     */
    @Test
    public void testSeek_notrec() {
        System.out.println("seek_notrec");

        File testDataDir = Const.getTESTDATADIR();
        File recursiveDirConst = Const.getXMLTESTDATADIR_RECURSIVE();

        FileSeeker instance = new FileSeeker(Const.getXMLTESTDATADIR(), XmlSuffix.getFilter());
        instance.setRecursive(false);
        assertEquals(false, instance.isRecursive());
        List<File> expResult = new ArrayList<>();
        expResult.add(Const.getTESTDATA_XML_1());
        expResult.add(Const.getTESTDATA_XML_2());

        List<File> result = instance.seek();

        assertTrue(Util.isSameFileList(expResult, result));
    }
}
