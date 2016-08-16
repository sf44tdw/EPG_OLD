/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import epgtools.libepgupdate.listmaker.fileseeker.FileSeeker;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.nio.charset.Charset;

/**
 * 指定されたディレクトリかたxmlファイルを捜索する。サブディレクトリは無視する。
 * @author dosdiaopfhj
 */
public class EpgListMaker {

    private final FileSeeker seeker;



    private final Charset charset;

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * EPG XMLファイルを読み込む際、DTDファイルの場所を指定する。
     *
     * @param sourceDir ファイルを検索するディレクトリ
     * @param charset 読み込むファイルの文字コード。
     */
    public EpgListMaker(File sourceDir, Charset charset) {
        this.charset = charset;
        seeker = new FileSeeker(sourceDir, XmlSuffix.getFilter());
        seeker.setRecursive(false);
    }

    /**
     * EPG XMLファイルの検索を行い、見つかったファイルを読み込む。
     *
     * @return 見つかったファイルを変換したDocumentオブジェクトのリスト。読み込みに失敗したファイルは無視する。
     */
    public synchronized List<Document> seek() {
        List<Document> EPGs = Collections.synchronizedList(new ArrayList<Document>());
        List<File> FL = this.seeker.seek();
        Iterator<File> it_F = FL.iterator();
        while (it_F.hasNext()) {
            File F = it_F.next();
            Document d = new XmlLoader(charset).Load(F);
            if (d != null) {
                log.log(Level.INFO, "EPGファイルが読み込まれました。 EPG FILE={0}", F.toString());
                EPGs.add(d);
            } else {
                log.log(Level.WARNING, "EPGファイルが読み込まれませんでした。このファイルは無視されます。 EPG FILE={0}", F.toString());
            }
        }
        return EPGs;
    }
}
