/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.channel;

import DataExtractor.Channel.Channel;
import epgtools.libepgupdate.dataextractor.AbstractAllEpgFileExtractor;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author dosdiaopfhj
 */
public class AllChannelDataExtractor extends AbstractAllEpgFileExtractor<Channel,ChannelDataExtractor>{

    public AllChannelDataExtractor(List<Document> EPGXMLs) {
        super(EPGXMLs);
    }

    @Override
    protected synchronized ChannelDataExtractor getExtractor(Document doc) {
        return new ChannelDataExtractor(doc);
    }
    
}
