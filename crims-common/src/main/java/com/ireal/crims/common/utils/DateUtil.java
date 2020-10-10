package com.ireal.crims.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String mmFormat = "mm";     // 秒
    public static final String hhFormat = "HH";     // 分钟
    public static final String hhmmFormat = "HH:mm";
    public static final String MMddFormat = "MM-dd";
    public static final String yyyyFormat = "yyyy";
    public static final String yyyyChineseFormat = "yyyy年";
    public static final String yyyyMMddFormat = "yyyy-MM-dd";
    public static final String fullFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String MMddChineseFormat = "MM月dd日";
    public static final String yyyyMMddChineseFormat = "yyyy年MM月dd日";
    public static final String fullChineseFormat = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    public static final String SYS_Default_Time = "2000-01-01 01:01:01";

    public static Date getDefaultTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 1);
        cal.set(Calendar.SECOND, 1);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public  static boolean isDefaultTime(Date dt)
    {
        SimpleDateFormat dtFormat = new SimpleDateFormat(fullFormat);
        try {
            Date defDt = dtFormat.parse(SYS_Default_Time);
            return 0 == defDt.compareTo(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * @Description: 任意时间字符串转换成时间，无需指定解析模板
     * @param:  日期
     * @return:
     * @Date: 2020/09/01 12:00
     * @Version: 1.0
     */
    public static Date parseStringToDate(String date) throws ParseException {
        Date result;
        String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format = new SimpleDateFormat(parse);
        result = format.parse(date);
        return result;
    }
}
