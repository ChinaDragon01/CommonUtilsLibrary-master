package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.text.TextUtils;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/3 17:03
 * Name: 公共工具类
 * Overview: 公共工具初始化
 * Usage: 使用步骤
 * 在主工程的Application类里通过CommonUtils调用其中 一个 init方法进行初始化
 * 注意事项：
 * 如果是调用重载的多个参数init方法，需要到 LogUtil 类里 对 customLogTag 和 flag变量进行赋值
 * **********************************************************************
 */

public class CommonUtils {
    public static Context sContext;
    public static String sCustomLogTag;
    public static boolean sLogModel;
    public static String sDirectoryName;

    /**
     * @param context
     * @param logModel true 开发环境，打印日志；false 正式环境，不打印日志
     */
    public static void init(Context context, boolean logModel, String directoryName) {
        init(context, null, logModel, null, directoryName);
    }

    /**
     * @param context
     * @param customLogTag 自定义日志Tag
     * @param logModel     true 开发环境，打印日志；false 正式环境，不打印日志
     * @param directoryName
     */
    public static void init(Context context, String customLogTag, boolean logModel, String directoryName) {
        init(context, customLogTag, logModel, null, directoryName);
    }

    /**
     * @param context
     * @param customLogTag          自定义日志Tag
     * @param logModel              true 开发环境，打印日志；false 正式环境，不打印日志
     * @param sharedPreferencesName
     * @param directoryName
     */
    public static void init(Context context, String customLogTag, boolean logModel, String sharedPreferencesName, String directoryName) {
        sContext = context;
        sCustomLogTag = customLogTag;
        sLogModel = logModel;
        sDirectoryName = directoryName;
        if (TextUtils.isEmpty(sharedPreferencesName)) {
            SharedPreferencesUtil.getSharedPreferences("sharedPreferencesFile");
        } else {
            SharedPreferencesUtil.getSharedPreferences(sharedPreferencesName);
        }
    }
}
