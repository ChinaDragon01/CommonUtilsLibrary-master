package com.chinadragon.commonutilslibrary.time;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/9/25 15:14
 * Name: 计时器
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class TimeCount {
    private static TextView mTimerTv;
    private static Handler customHandler = new Handler();
    private static long startTime = 0L;

    public static void updateRecordingTime(TextView textView) {
        mTimerTv = textView;
        startTime = SystemClock.uptimeMillis();
        updateRecordingTime(0, 0, 0);
        customHandler.postDelayed(updateTimerThread, 1000);
    }

    /**
     * 将updateTimerThread从线程队列移除
     * <p>
     * 删除指定的Runnable对象，使线程对象停止运行。
     */
    public static void removeCallbacks() {
        customHandler.removeCallbacks(updateTimerThread);
    }

    private static Runnable updateTimerThread = new Runnable() {
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int seconds = (int) (timeInMilliseconds / 1000);
            int hour = seconds / 1000 / 60;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            updateRecordingTime(hour, minutes, seconds);
            customHandler.postDelayed(this, 1000);
        }
    };


    /**
     * 分秒
     *
     * @param minutes
     * @param seconds
     */
    private static void updateRecordingTime(int minutes, int seconds) {
        mTimerTv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }

    /**
     * 时分秒
     *
     * @param hour
     * @param seconds
     * @param minutes
     */
    private static void updateRecordingTime(int hour, int minutes, int seconds) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", hour)).append(":").append(String.format("%02d", minutes)).append(":").append(String.format("%02d", seconds));
        mTimerTv.setText(sb.toString());
    }

    public static String timeData(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm:ss");
        long tempTime = Long.valueOf(time) - 8 * 60 * 60 * 1000;// 减8个小时的毫秒值是为了解决时区的问题。
        return sdr.format(new Date(tempTime));// 00:00:00

    }

    public static String timeData(long time, String pattern) {
        if (null == pattern) {
            return timeData(time);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(new Date(time));
        }
    }

    /**
     * 设置日期格式
     *
     * @param pattern
     * @return
     */
    public static String currentTime(String pattern) {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = null;
        if (pattern != null) {
            format = new SimpleDateFormat(pattern);
        } else {
            format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        }
        String tempTime = format.format(new Date(time));
        return tempTime;
    }

    // 参考资料 http://www.cnblogs.com/liushilin/p/5802954.html
    public static void startChronometer(Chronometer chronometer) {
        chronometer.setBase(SystemClock.elapsedRealtime()); // 计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
        chronometer.setFormat("0" + String.valueOf(hour) + ":%s");
        chronometer.start();
    }

    public static void stopChronometer(Chronometer chronometer) {
        if (null != chronometer) {
            chronometer.stop();
        }
    }
}
