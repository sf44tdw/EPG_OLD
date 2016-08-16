/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.epgdbbean.bean.Programme;

import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo;
import epgtools.epgdbbean.bean.programme.ProgrammeChannelNo_ReadOnly;
import epgtools.epgdbbean.common.Initializer;
import epgtools.epgdbbean.bean.programme.ProgrammeGetter;
import epgtools.jdbcaccessor.jdbcaccessor.JDBCAccessor;
import epgtools.loggerconfigurator.LoggerConfigurator;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dosdiaopfhj
 */
public class ProgrammeGetterTest {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    private final Initializer init = new Initializer();
    private final JDBCAccessor Accesser;
    /**
     * チャンネルビューの内容のうち、放送開始が未来の番組を全て選択するSQL
     */
    public static final String SELECT_ALL_CHANNEL_VIEW = "SELECT * FROM programme_channel_no WHERE start_datetime >= NOW()";

    public ProgrammeGetterTest() {
        this.init.loadConfig();
        this.Accesser = this.init.getAccessor();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        this.Accesser.close();
    }

    /**
     * Test of getProgramme method, of class ProgrammeGetter.
     */
    @Test
    public void testGetProgramme() {
        try {
            ProgrammeGetter gt = new ProgrammeGetter(this.Accesser.getCon());
            //番組テーブルから適当に1個の番組を選ぶ。
            ProgrammeChannelNo_ReadOnly pr = this.chooseAppropriately(this.Accesser.getCon());
            log.log(Level.INFO, pr.toString());
            //ProgrammeGetterを使って選んだ番組を取得する。
            ProgrammeChannelNo_ReadOnly pc = gt.getProgramme(pr.getChannel_id(), pr.getChannel_no(), pr.getStart_datetime());
            log.log(Level.INFO, pc.toString());
            //同じ番組ならば成功扱いにする。
            assertEquals(pr, pc);
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeGetterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ProgrammeChannelNo_ReadOnly chooseAppropriately(Connection con) throws SQLException {
        //番組テーブル内の現在以降に放送開始の番組から適当に1個選ぶ。
        QueryRunner runner = new QueryRunner();
        List<ProgrammeChannelNo> Programmes;
        Programmes = runner.query(con, SELECT_ALL_CHANNEL_VIEW, new BeanListHandler<ProgrammeChannelNo>(ProgrammeChannelNo.class));
        Iterator<ProgrammeChannelNo> j = Programmes.iterator();

        Random rnd = new Random();
        int ran = rnd.nextInt(Programmes.size()) + 1;
        ProgrammeChannelNo p = null;

        int i = 1;
        while (j.hasNext()) { //Iteratorを取得し、ループで回す 
            p = j.next();
            if (ran == i) {
                break;
            }
            i++;
        }
        return p;
    }

}
