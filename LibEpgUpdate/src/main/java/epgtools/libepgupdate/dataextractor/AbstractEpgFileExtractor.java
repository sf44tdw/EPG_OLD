/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.util.logging.Level;
import DataExtractor.EpgData;

/**
 * 読み込んだEPG XMLから必要な情報を取り出す。
 *
 * @author dosdiaopfhj
 * @param <T> EPGDataインタフェースを実装したクラス。
 */
public abstract class AbstractEpgFileExtractor<T extends EpgData> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final Document doc;
    private final String nodeName;

    /**
     *
     * @param doc パースされたEPGのXMLデータ
     * @param nodeName 情報を取り出したい要素のノード名
     */
    public AbstractEpgFileExtractor(Document doc, String nodeName) {
        this.doc = doc;
        this.nodeName = nodeName;

    }

    /**
     * 特定の要素名のノードを全て確保し、1件ごとに実装されたdump()を呼び出してデータの抽出処理を行う。
     *
     * @author dosdiaopfhj
     * @return 抽出されたデータのリスト
     *
     */
    public final synchronized List<T> makeList() {
        List<T> records = Collections.synchronizedList(new ArrayList<T>());
        Element root;
        root = this.doc.getDocumentElement();
        NodeList nl = doc.getElementsByTagName(this.nodeName);
        int Nodes = nl.getLength();
        for (int i = 0; i < Nodes; i++) {
            Node N = nl.item(i);
            try {
                T recoed_val = dump(N);
                records.add(recoed_val);
            } catch (IllegalArgumentException ex) {
                log.log(Level.WARNING, "データに問題があるため、無視します。");
            }
        }
        return records;
    }

    /**
     * 取得した要素の内容から必要なデータを1件抽出する。makeList()が使用することを前提にしているので、外部から直接使わないこと。
     *
     * @author dosdiaopfhj
     * @param N 取得した要素
     * @return 1件分のデータ。
     */
    protected abstract T dump(Node N) throws IllegalArgumentException;

//    protected final synchronized void printNode(Node node) {
//
//        System.out.print("ノード名 = " + node.getNodeName() + " "); // ノード名
//        System.out.print("ノードタイプ = " + node.getNodeType() + " "); // ノードタイプ
//        System.out.println("ノード値 = " + node.getNodeValue()); // ノード値
//
//    }
}
