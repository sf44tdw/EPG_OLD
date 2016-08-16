/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor;

import DataExtractor.Channel.Channel;
import epgtools.libepgupdate.common.Const;
import epgtools.libepgupdate.dataextractor.channel.ChannelDataExtractor;
import epgtools.libepgupdate.listmaker.XmlLoader;
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
public class ChannelDataExtractorTest {

    public ChannelDataExtractorTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        XmlLoader loader = new XmlLoader(Const.CHARCODE);
        Document doc = loader.Load(Const.getTESTDATA_XML_1());
        ChannelDataExtractor target = new ChannelDataExtractor(doc);
        List<Channel> chs = target.makeList();
        Iterator<Channel> it = chs.iterator();
        while (it.hasNext()) {
            Channel o = it.next();
            System.out.println(o.toString());
        }
    }

}
