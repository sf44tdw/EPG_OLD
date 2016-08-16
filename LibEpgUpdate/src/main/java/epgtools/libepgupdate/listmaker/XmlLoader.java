/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.nio.charset.Charset;

/**
 * 指定された文字コードでEPG XMLファイルを読み込む。
 *
 * @author dosdiaopfhj
 */
public class XmlLoader {

    private final Charset charset;

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * @param charset XMLファイルの文字コード
     */
    public XmlLoader(Charset charset) {
        this.charset = charset;
    }

    public final synchronized Charset getCharset() {
        return charset;
    }

    /**
     * XMLを読み込む
     *
     * @param F XMLファイル
     * @return XMLファイルから作ったDocumentオブジェクト。
     * @author dosdiaopfhj
     */
    public synchronized Document Load(File F) {
        try {
            log.log(Level.FINE, "ファイル = {0} 文字コード={1} 読み込み開始。", new Object[]{F, getCharset()});
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            documentBuilder.setEntityResolver(new XmlTvDtdResolver());
            Document document = documentBuilder.parse(new InputSource(new InputStreamReader(new FileInputStream(F), getCharset())));
            Element root = document.getDocumentElement();
            log.log(Level.FINE, "ファイル = {0} 文字コード={1} 読み込み完了。", new Object[]{F, getCharset()});
            return document;
        } catch (ParserConfigurationException | UnsupportedEncodingException | FileNotFoundException ex) {
            log.log(Level.SEVERE, "例外発生。", ex);
            return null;
        } catch (SAXException | IOException ex) {
            log.log(Level.SEVERE, "例外発生。", ex);
            return null;
        }

    }

}
