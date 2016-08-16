/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.programme;

import DataExtractor.Programme.Programme;

import epgtools.libepgupdate.dataextractor.AbstractAllEpgFileExtractor;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author dosdiaopfhj
 */
public class AllProgrammeDataExtractor extends AbstractAllEpgFileExtractor<Programme, ProgrammeDataExtractor> {

    public AllProgrammeDataExtractor(List<Document> EPGXMLs) {
        super(EPGXMLs);
    }

    @Override
    protected synchronized ProgrammeDataExtractor getExtractor(Document doc) {
        return new ProgrammeDataExtractor(doc);
    }

}
