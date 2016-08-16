/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.channel;


import epgtools.epgdbbean.common.Initializer;
import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dosdiaopfhj
 */
public class ChannelGetterTest {

    private static final Logger log = LoggerConfigurator.getCallerLogger();
    private final Initializer init = new Initializer();
    private final JDBCAccessor Accessor;

    public ChannelGetterTest() {
        this.init.loadConfig();
        this.Accessor = this.init.getAccessor();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        this.Accessor.close();
    }

    /**
     * Test of getChannels method, of class ChannelGetter.
     */
    @Test
    public void testGetChannels() {
        try {
            log.log(Level.INFO, "正常系1");
            Map<Integer, Useablechannels_ReadOnly> Channels_Ro = new ChannelGetter(this.Accessor.getCon()).getChannels();
            if (Channels_Ro != null) {
                Iterator<Integer> it = Channels_Ro.keySet().iterator();
                //チャンネルの一覧を表示
                while (it.hasNext()) {
                    int o = it.next();
                    log.log(Level.INFO, Channels_Ro.get(o).toString());
                }
            }
            log.log(Level.INFO, "正常系1正常終了");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "正常系1異常終了", ex);
        }
    }

}
