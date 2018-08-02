package com.chinadragon.commonutilslibrary;

import android.util.Log;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/10 11:37
 * Name: 日志工具类
 * Overview:
 * Usage:
 * 1.flag为true时，表示是开发环境，打印log日志
 * 2.flag为false时，表示是正式环境，不打印log日志
 * **********************************************************************
 */
public class LogUtil {
    private static LogUtil sLogUtilInstance = null;
    private static String customLogTag = "TAG";
    private static boolean flag = true;

    public LogUtil() {
    }

    public static void v(String content) {
        if (!getFlog()) return;
        Log.v(getCustomLogTag(), content);
    }

    public static void d(String content) {
        if (!getFlog()) return;
        Log.d(getCustomLogTag(), content);
    }

    public static void i(String content) {
        if (!getFlog()) return;
        Log.i(getCustomLogTag(), content);
    }

    public static void w(String content) {
        if (!getFlog()) return;
        Log.w(getCustomLogTag(), content);
    }

    public static void e(String content) {
        if (!flag) return;
        Log.e(getCustomLogTag(), content);
    }

    public static void wtf(String content) {
        if (!getFlog()) return;
        Log.wtf(getCustomLogTag(), content);
    }


    private static synchronized void syncInit() {
        if (sLogUtilInstance == null) {
            sLogUtilInstance = new LogUtil();
        }
    }

    public static LogUtil getInstance() {
        if (sLogUtilInstance == null) {
            syncInit();
        }
        return sLogUtilInstance;
    }

    private static String getCustomLogTag(){
        return CommonUtils.sCustomLogTag != null ? CommonUtils.sCustomLogTag : customLogTag;
    }

    private static boolean getFlog(){
        return CommonUtils.sLogModel == true ? flag : CommonUtils.sLogModel;
    }
}
