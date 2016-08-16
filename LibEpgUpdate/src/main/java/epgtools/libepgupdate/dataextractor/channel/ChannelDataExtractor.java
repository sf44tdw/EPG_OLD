/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dataextractor.channel;

import DataExtractor.Channel.Channel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import epgtools.libepgupdate.dataextractor.AbstractEpgFileExtractor;
import epgtools.loggerconfigurator.LoggerConfigurator;

/**
 * チャンネル関係の情報だけDBへ格納する
 *
 * 地上波の場合 
 * EPGのチャンネルid(GR_*)? :id
 * EPG取得時の物理チャンネル番号 :tp
 * テレビ局名 :display-name
 *
 * BSの場合 
 * EPGのチャンネルid?(BS_物理チャンネル番号) :id
 * EPG取得時の物理チャンネル番号(BS_*_*) :tp
 * テレビ局名:display-name
 *
 * を取得し、リストに格納する。
 *
 * @author dosdiaopfhj
 */
public class ChannelDataExtractor extends AbstractEpgFileExtractor<Channel> {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * EPG関連 チャンネル要素の要素名
     */
    private static final String EPG_CHANNEL = "channel";
    /**
     * EPG関連 チャンネル要素のチャンネルIDの要素名
     */
    private static final String EPG_CHANNEL_ID = "id";

    /**
     * EPG関連 チャンネル要素の物理チャンネル番号の要素名
     */
    private static final String EPG_CHANNEL_TP = "tp";

    /**
     * EPG関連 チャンネルIDが地上波の場合の接頭辞
     */
    private static final String PREFIX_GR = "GR";

    /**
     * EPG関連 チャンネルIDがBSの場合の接頭辞
     */
    private static final String PREFIX_BS = "BS";

//    /**
//     * EPG関連 チャンネルIDがCSの場合の接頭辞
//     */
//    private static final String PREFIX_CS = "CS";
    
    /**
     * EPG関連 チャンネル要素の局名の要素名
     */
    private static final String EPG_CHANNEL_DISPLAY_NAME = "display-name";

    public ChannelDataExtractor(Document doc) {
        super(doc, ChannelDataExtractor.EPG_CHANNEL);
    }

    private synchronized int getChannelNumber(String ch_S, String tp_S) {
//チャンネルidの頭2文字を取り出す。
        int r;
        String pref = ch_S.substring(0, 2);
        log.log(Level.FINE, "チャンネルid:{0}_頭2文字:{1}", new Object[]{ch_S, pref});
        switch (pref) {
            case ChannelDataExtractor.PREFIX_GR:
                //地上波の場合
                r = Integer.parseInt(tp_S);
                break;
            default:
                //BSかCSの場合(CSについては憶測)
                //チャンネルidの頭3文字以外を数字に変換して物理チャンネル番号とする。
                r = Integer.parseInt(ch_S.substring(3));
                break;
        }
        return r;
    }

    @Override
    protected final synchronized Channel dump(Node N)  throws IllegalArgumentException{
        NamedNodeMap attrs_channel = N.getAttributes();  // NamedNodeMapの取得

        Node ch = attrs_channel.getNamedItem(ChannelDataExtractor.EPG_CHANNEL_ID);
        Node tp = attrs_channel.getNamedItem(ChannelDataExtractor.EPG_CHANNEL_TP);
        String ch_S = ch.getNodeValue();
        log.log(Level.FINE, "ch:{0}", new Object[]{ch_S});
        String tp_S = tp.getNodeValue();
        log.log(Level.FINE, "TP:{0}", new Object[]{tp_S});
        String display_name_S = "";
        NodeList channelChildren = N.getChildNodes();
        int Nodes = channelChildren.getLength();
        for (int i = 0; i < Nodes; i++) {
            Node gchild = channelChildren.item(i);

            //たまに放送局名が空欄になっていることがあるので、その場合は仮の名前を記入する。
            try {
                if (gchild.getNodeName().equals(ChannelDataExtractor.EPG_CHANNEL_DISPLAY_NAME)) {
                    display_name_S = gchild.getFirstChild().getNodeValue();
                }
                log.log(Level.FINE, "放送局名有り。");
            } catch (NullPointerException e) {
                log.log(Level.FINE, "放送局名無し。");
                display_name_S = "Unknown display-name";
            } finally {
                log.log(Level.INFO, "display-name:{0}", new Object[]{display_name_S});
            }
        }
        log.log(Level.INFO, "ch:{0}_TP:{1}_display-name:{2}", new Object[]{ch_S, tp_S, display_name_S});
        return new Channel(ch_S, getChannelNumber(ch_S, tp_S), display_name_S);
    }
}
