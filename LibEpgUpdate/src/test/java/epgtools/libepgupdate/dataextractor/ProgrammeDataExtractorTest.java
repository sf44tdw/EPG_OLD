/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor;

import DataExtractor.Programme.Programme;
import epgtools.libepgupdate.common.Const;
import epgtools.libepgupdate.dataextractor.programme.ProgrammeDataExtractor;
import epgtools.libepgupdate.listmaker.XmlLoader;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;


/**
 *
 * @author dosdiaopfhj
 */
public class ProgrammeDataExtractorTest {

    private final Charset CHARCODE = Const.CHARCODE;
    private final File F =Const.getTESTDATA_XML_1();

    public ProgrammeDataExtractorTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        XmlLoader loader = new XmlLoader(CHARCODE);
        Document doc = loader.Load(F);
        ProgrammeDataExtractor target = new ProgrammeDataExtractor(doc);
        List<Programme> prs = target.makeList();
        Iterator<Programme> it = prs.iterator();
        while (it.hasNext()) {
            Programme o = it.next();
            System.out.println(o.toString());
        }
    }

}
