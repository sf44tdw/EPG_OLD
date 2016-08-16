/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.loggerconfigurator.LoggerConfigurator;
import org.w3c.dom.Document;
import DataExtractor.EpgData;

/**
 * チャンネル。番組の情報を全てのXMLファイルから抽出し、リストにまとめる。
 *
 * @author dosdiaopfhj
 * @param <T> EPGDataインタフェースを実装したクラス。
 * @param <U> 情報取り出し用クラス
 */
public abstract class AbstractAllEpgFileExtractor<T extends EpgData,U extends AbstractEpgFileExtractor<T>> {
private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final List<Document> EPGXMLs;

    /**
     * @param EPGXMLs ロード済みのEPG XMLファイルのリスト
     */
    public AbstractAllEpgFileExtractor(List<Document> EPGXMLs) {
        this.EPGXMLs = EPGXMLs;
    }

    /**
     * XMLから情報を取り出すクラスを取得する処理を実装する。getAllEPGRecords()が使用する。
     *
     * @param doc ロードされたXMLファイル
     * @return 情報取り出し用クラス
     */
    protected abstract U getExtractor(Document doc);

    /**
     * 渡された全てのXMLから、 getExtractor()によって取得された抽出用オブジェクトによって抽出された情報を取得する。
     *
     * @return 取得された情報。
     */
    public final synchronized List<T> getAllEPGRecords() {
        List<T> temp_List1 = Collections.synchronizedList(new ArrayList<T>());
        log.log(Level.FINE, "ファイル数={0}", this.EPGXMLs.size());
        Iterator<Document> it_EPG = this.EPGXMLs.iterator();
        while (it_EPG.hasNext()) {
            Document D = it_EPG.next();
            List<T> temp_List2;
            temp_List2 = this.getExtractor(D).makeList();
            temp_List1.addAll(temp_List2);
        }
        return temp_List1;
    }
}
