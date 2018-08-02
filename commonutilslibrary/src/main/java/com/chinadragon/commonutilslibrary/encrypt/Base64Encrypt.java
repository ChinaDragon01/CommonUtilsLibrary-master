package com.chinadragon.commonutilslibrary.encrypt;

import android.text.TextUtils;
import android.util.Base64;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/23 11:27
 * Name: Base64工具类
 * Overview: Base64加密
 * Usage: 使用步骤
 * 1、调用base64Encrypt方法对数据进行加密
 * 2、调用base64Decode对加密过的数据进行解密
 * **********************************************************************
 */

public class Base64Encrypt {
    public static String base64Encrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        }
    }

    public static byte[] base64Encrypt(byte[] str) {
        if (str == null) {
            return null;
        } else {
            return Base64.encode(str, Base64.DEFAULT);
        }
    }

    public static String base64Decode(String str) {
        return new String(Base64.decode(str, Base64.DEFAULT));
    }

    public static byte[] base64Decode(byte[] str) {
        return Base64.decode(str, Base64.DEFAULT);
    }
}
