package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.chinadragon.commonutilslibrary.encrypt.Base64Encrypt;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/9 17:14
 * Name:SharedPreferences存储
 * Overview:
 * 注意：存储和获取String类型的数据，使用了Base64Encrypt加密
 * Usage:
 * **********************************************************************
 */
public class SharedPreferencesUtil {
    private static String mName;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * @param name
     */
    public static SharedPreferences getSharedPreferences(String name) {
        try {
            mName = name;
            if (sp == null) {
                sp = CommonUtils.sContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
                editor = sp.edit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public static void saveFloat(String key, float i) {
        editor.putFloat(key, i).commit();
    }

    public static float getFloat(String key) {
        return sp.getFloat(key, 0.0f);
    }

    public static void saveLong(String key, long i) {
        editor.putLong(key, i).commit();
    }

    public static long getLong(String key, long i) {
        return sp.getLong(key,i);
    }

    public static void saveInt(String key, int i) {
        editor.putInt(key, i).commit();
    }

       public static int getInt(String key) {
        return sp.getInt(key, -1);
    }
    public static int getInt(String key,int i) {
        return sp.getInt(key, i);
    }

    /**
     * 加密保存
     * @param key
     * @param str
     */
    public static void saveString(String key, String str) {
//        editor.putString(key, str).commit();
        editor.putString(key, Base64Encrypt.base64Encrypt(str)).commit();
    }

    /**
     * 解密
     * @param key
     * @return
     */
    public static String getString(String key) {
//        return sp.getString(key, "");
        return Base64Encrypt.base64Decode(sp.getString(key, ""));
    }

    public static void saveBoolean(String key, boolean bool) {
        editor.putBoolean(key, bool).commit();
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static void clear() {
        editor.clear().commit();
    }

    public static void romove(String key) {
        editor.remove(key).commit();
    }


    /**
     * 缓存是否被清除了
     *
     * @return
     */
    public static boolean isCacheClear(String iscacheclear) {
        return sp.getBoolean(iscacheclear, true);
    }

    /**
     * 设置有缓存数据了
     */
    public static void setCacheClear(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }
}
