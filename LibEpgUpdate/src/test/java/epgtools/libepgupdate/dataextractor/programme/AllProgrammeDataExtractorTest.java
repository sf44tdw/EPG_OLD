/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.programme;

import DataExtractor.Programme.Programme;
import epgtools.libepgupdate.common.Const;
import epgtools.libepgupdate.listmaker.XmlLoader;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;



/**
 *
 * @author dosdiaopfhj
 */
public class AllProgrammeDataExtractorTest {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    private final Charset CHARCODE = Const.CHARCODE;
    private final File F1 = Const.getTESTDATA_XML_1();
    private final File F2 = Const.getTESTDATA_XML_2();
    private final XmlLoader loader = new XmlLoader(CHARCODE);
    private final Document doc1 = loader.Load(F1);
    private final Document doc2 = loader.Load(F2);

    public AllProgrammeDataExtractorTest() {
        log.setLevel(Level.ALL);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        List<Document> docs = Collections.synchronizedList(new ArrayList<Document>());
        docs.add(doc1);
        docs.add(doc2);

        AllProgrammeDataExtractor target = new AllProgrammeDataExtractor(docs);
        List<Programme> prs = target.getAllEPGRecords();

        for (Programme o : prs) {
            System.out.println(o.toString());
        }
    }

}