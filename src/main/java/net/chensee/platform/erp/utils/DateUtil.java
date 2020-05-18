package net.chensee.platform.erp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ah
 * @title: 时间格式转换
 * @date 2019/10/30 10:37
 */
public class DateUtil {

    public static Date convertStrToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static String getYear(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }

    public static String getMonth(){
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(new Date());
    }

    public static String getDay(){
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(new Date());
    }

}
