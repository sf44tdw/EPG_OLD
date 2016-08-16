/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.programme;

import DataExtractor.Programme.Programme;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import epgtools.libepgupdate.dataextractor.AbstractEpgFileExtractor;
import epgtools.libepgupdate.dateconverter.Converter;
import epgtools.libepgupdate.regex.stringreplacer.StringReplacer_Regex;
import epgtools.loggerconfigurator.LoggerConfigurator;

/**
 * EPGから番組関係の情報を取得する。 番組ID :event_id チャンネルID :channel タイトル :title 開始時刻 :start
 * 終了時刻 :stop
 *
 * @author dosdiaopfhj
 */
public class ProgrammeDataExtractor extends AbstractEpgFileExtractor<Programme> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final StringReplacer_Regex rep = new StringReplacer_Regex();
    private final Converter c = new Converter();

    /**
     * 日時要素の+で始まる部分にマッチする正規表現。日時要素のこの部分は何に使っているのか判らないが、DATETIME型フィールドには入れられないので除去する。
     */
    private static final String C_TIME_OF_MYSTERY = "\\s\\+[0-9]*";

    /**
     * EPGの放送日データをDate型に変換するためのフォーマット
     */
    private static final String C_DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * EPG関連 番組要素の要素名
     */
    private static final String EPG_PROGRAMME = "programme";

    /**
     * EPG関連 番組要素の番組IDの要素名
     */
    private static final String EPG_PROGRAMME_EVENT_ID = "event_id";

    /**
     * EPG関連 番組要素のチャンネルIDの要素名
     */
    private static final String EPG_PROGRAMME_CHANNEL_ID = "channel";

    /**
     * EPG関連 番組要素の番組開始時刻の要素名
     */
    private static final String EPG_PROGRAMME_START_TIME = "start";

    /**
     * EPG関連 番組要素の番組終了時刻の要素名
     */
    private static final String EPG_PROGRAMME_STOP_TIME = "stop";

    /**
     * EPG関連 番組要素の番組名の要素名
     */
    private static final String EPG_PROGRAMME_NAME = "title";

    public ProgrammeDataExtractor(Document doc) {
        super(doc, ProgrammeDataExtractor.EPG_PROGRAMME);
        //日付要素のよくわからない部分を削除するよう設定する。
        rep.setRegexPattern(ProgrammeDataExtractor.C_TIME_OF_MYSTERY);
        rep.setReplaceTo("");
    }

    @Override
    protected final synchronized Programme dump(Node N)  throws IllegalArgumentException{
        String event_id_s = "";
        String start_time_s = "";
        String stop_time_s = "";
        String channel_id_s = "";
        String title_s = "";

        NamedNodeMap programme_attrs = N.getAttributes();  // NamedNodeMapの取得
        Node event_id = programme_attrs.getNamedItem(ProgrammeDataExtractor.EPG_PROGRAMME_EVENT_ID);
        Node start_time = programme_attrs.getNamedItem(ProgrammeDataExtractor.EPG_PROGRAMME_START_TIME);
        Node stop_time = programme_attrs.getNamedItem(ProgrammeDataExtractor.EPG_PROGRAMME_STOP_TIME);
        Node channel_id = programme_attrs.getNamedItem(ProgrammeDataExtractor.EPG_PROGRAMME_CHANNEL_ID);

        event_id_s = event_id.getNodeValue();
        start_time_s = rep.replaceAll(start_time.getNodeValue());
        stop_time_s = rep.replaceAll(stop_time.getNodeValue());
        channel_id_s = channel_id.getNodeValue();

        NodeList programmeChildren = N.getChildNodes();
        int Nodes = programmeChildren.getLength();
        for (int i = 0; i < Nodes; i++) {
            Node gchild = programmeChildren.item(i);
            if (gchild.getNodeName().equals(ProgrammeDataExtractor.EPG_PROGRAMME_NAME)) {
                title_s = gchild.getFirstChild().getNodeValue();
            }
        }
        log.log(Level.INFO, "event_id:{0}_start:{1}_stop:{2}_channel_id:{3}_title:{4}", new Object[]{event_id_s, start_time_s, stop_time_s, channel_id_s, title_s});
        Programme p = new Programme(
                channel_id_s,
                Integer.parseInt(event_id_s),
                title_s,
                c.stringTOSqlDate(start_time_s, ProgrammeDataExtractor.C_DATE_PATTERN),
                c.stringTOSqlDate(stop_time_s, ProgrammeDataExtractor.C_DATE_PATTERN)
        );
        return p;
    }

}
