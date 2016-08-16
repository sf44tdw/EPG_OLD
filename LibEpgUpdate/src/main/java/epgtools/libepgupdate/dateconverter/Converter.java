/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.dateconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import epgtools.loggerconfigurator.LoggerConfigurator;

/**
 *
 * @author dosdiaopfhj
 */
public class Converter {

    private static final Logger log = LoggerConfigurator.getCallerLogger();

    /**
     * 日時をjava.sql.Timestamp型に変換
     *
     * @param source 日時文字列
     * @param datePattern SimpleDateFormatで使用する日時のパターン
     * @return 日時の入ったTimestampオブジェクト。
     */
    public final synchronized java.sql.Timestamp stringTOSqlDate(String source, String datePattern) {
       try {
            return new java.sql.Timestamp ((new SimpleDateFormat(datePattern)).parse(source).getTime());
        } catch (ParseException ex) {
            log.log(Level.SEVERE, "日時のTimestamp型への変換に失敗しました。", ex);
            return null;
        }
    }

//    /**
//     * java.util.Dateをjava.sql.Dateに変換する
//     *
//     * @param utilDate java.util.Date形式の日時
//     * @return java.sql.Date形式の日時
//     */
//    public final synchronized java.sql.Date utilDateTOSqlDate(java.util.Date utilDate) {
//       
//                java.util.Calendar cal = Calendar.getInstance();
//        cal.setTime(utilDate);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
//        log.log(Level.INFO, "LoggerConfigurator.Date={0}_Sql.Date={1}", new Object[]{utilDate.toString(), sqlDate.toString()});
//        return sqlDate;
//    }


    }

