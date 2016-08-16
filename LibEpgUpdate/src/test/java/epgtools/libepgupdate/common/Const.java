/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.common;

import epgtools.loggerconfigurator.LoggerConfigurator;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author normal
 */
public final class Const {

    private Const() {
    }

    private static final Logger logger = LoggerConfigurator.getCallerLogger();

    public static void setLevelALL() {

        // Logger の Level 設定
        System.out.print("Logger's LogLevel is changed from " + logger.getLevel());
        logger.setLevel(Level.ALL);
        System.out.println(" to " + logger.getLevel());

//        logger.addHandler(new ConsoleHandler());
        // Handler の Level 設定
        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            System.out.println(handler + "'s Level: " + handler.getLevel());
            System.out.print("Handler's LogLevel is changed from " + handler.getLevel());
            handler.setLevel(Level.ALL);
            System.out.println(" to " + handler.getLevel());

        }
    }
    public static final Charset CHARCODE = StandardCharsets.UTF_8;

    /**
     * テストデータ置き場
     */
    private static final File TESTDATADIR = new File("./test");

    /**
     * テスト用EPGのxml置き場
     */
    private static final File XMLTESTDATADIR = new File(TESTDATADIR, "xml");

    /**
     * テスト用EPGのxml置き場
     */
    private static final File XMLTESTDATADIR_RECURSIVE = new File(XMLTESTDATADIR, "recursive");

    public static final String TESTDATADIR_S = TESTDATADIR.getAbsolutePath();

    /**
     * XMLファイル1
     */
    private static final File TESTDATA_XML_1 = new File(XMLTESTDATADIR, "24.xml");

    /**
     * XMLファイル2
     */
    private static final File TESTDATA_XML_2 = new File(XMLTESTDATADIR, "22.xml");

    /**
     * XMLファイル3
     */
    private static final File TESTDATA_XML_3 = new File(XMLTESTDATADIR_RECURSIVE, "23.xml");

    private static File copy(File f) {
        return new File(f.getAbsolutePath());
    }

    public static File getTESTDATADIR() {
        return copy(TESTDATADIR);
    }

    public static File getXMLTESTDATADIR() {
        return copy(XMLTESTDATADIR);
    }

    public static File getXMLTESTDATADIR_RECURSIVE() {
        return copy(XMLTESTDATADIR_RECURSIVE);
    }

    public static File getTESTDATA_XML_1() {
        return copy(TESTDATA_XML_1);
    }

    public static File getTESTDATA_XML_2() {
        return copy(TESTDATA_XML_2);
    }

    public static File getTESTDATA_XML_3() {
        return copy(TESTDATA_XML_3);
    }

    /**
     * 見られないチャンネルのリスト。
     */
    public static final Set<Integer> getDummyPaidBroadcastings() {
        Set<Integer> tempPaidBroadcastings = new HashSet<>();
        tempPaidBroadcastings.add(10);
        tempPaidBroadcastings.add(20);
        tempPaidBroadcastings.add(30);
        return Collections.unmodifiableSet(tempPaidBroadcastings);
    }

}
