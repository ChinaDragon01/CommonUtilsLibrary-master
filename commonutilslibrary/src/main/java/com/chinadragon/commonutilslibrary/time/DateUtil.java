package com.chinadragon.commonutilslibrary.time;

import com.chinadragon.commonutilslibrary.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/4 8:57
 * Name: 时间工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class DateUtil {
    public static String week(int year, int month, int day) {
        LogUtil.i(year + "：" + month + "：" + day);
        Calendar calendar = Calendar.getInstance();// 获得一个日历
        calendar.set(year, month - 1, day);// 设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
        String[] strWeek = {"周  日", "周  一", "周  二", "周  三", "周  四", "周  五", "周  六"};
        return strWeek[number - 1];
    }

    /**
     * 获取时间差
     * 注意 pattern 设定什么样的日期格式，date参数的日期格式要保持一致
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String getTimesToNow(String pattern, String date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String now = format.format(new Date());
        String returnText = null;
        try {
            long from = format.parse(date).getTime();
            long to = format.parse(now).getTime();
            int days = (int) ((to - from) / (1000 * 60 * 60 * 24));
            if (days == 0) {//一天以内，以分钟或者小时显示
                int hours = (int) ((to - from) / (1000 * 60 * 60));
                if (hours == 0) {
                    int minutes = (int) ((to - from) / (1000 * 60));
                    if (minutes == 0) {
                        returnText = "刚刚";
                    } else {
                        returnText = minutes + "分钟前";
                    }
                } else {
                    returnText = hours + "小时前";
                }
            } else if (days == 1) {
                returnText = "昨天";
            } else {
                returnText = days + "天前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnText;
    }
}
