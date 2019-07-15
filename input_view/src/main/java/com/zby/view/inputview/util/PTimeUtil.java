package com.zby.view.inputview.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

//时间工具类，获取当前、本周、本月、上周、上月时间
public class PTimeUtil {


    /**
     * * 获取现在时间
     * *
     * * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * long 转换为string time时间
     *
     * @param time
     * @return
     */
    public static String longToString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(time);
        return str;
    }


}
