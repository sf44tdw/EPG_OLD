/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * xmltv.dtdをファイルから読み込ませる。
 *
 * @author normal
 */
public class XmlTvDtdResolver implements EntityResolver {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    public static final String DTD_NAME = "xmltv.dtd";

    private static final String MSG_SUCCESS = "{0}は実在するファイルです。";

    private final File XmlTVDTD;

    /**
     * @author normal
     */
    public XmlTvDtdResolver() {
        this.XmlTVDTD = new File(new File("./dtd"), this.DTD_NAME);
        if ((XmlTVDTD == null)) {
            throw new IllegalStateException("DTDファイルがありません。");
        } else if (!(XmlTVDTD.isFile())) {
            throw new IllegalStateException(MessageFormat.format("DTDファイルとして指定された {0} は実在しないか、ファイルではありません。", XmlTVDTD.getAbsolutePath()));
        }
    }

    /**
     * 公開識別子もしくはシステム識別子にxmltv.dtdを含んだ文字列があった場合、DocumentBuilderに対し、このクラスで指定されたファイルからxmltv.dtdを読み込ませる。
     * ファイルが指定されなかった場合はnullを返す。
     */
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

        if ((publicId != null && publicId.contains(DTD_NAME)) || (systemId != null && systemId.contains(DTD_NAME))) {
            log.log(Level.FINE, "識別子を確認しました。");
            InputSource source = new InputSource(new FileInputStream(this.XmlTVDTD));
            source.setPublicId(publicId);
            source.setSystemId(systemId);
            return source;
        } else {
            log.log(Level.FINE, "公開識別子、システム識別子とも、{0}を含む文字列ではありませんでした。", this.XmlTVDTD);
            return null;
        }
    }
}
