package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/14 16:37
 * Name: 字符串工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class StringUtil {
    /**
     * 将R.string.T 转string
     *
     * @param id
     * @return
     */
    public static String getResStr(int id) {
        return CommonUtils.sContext.getResources().getString(id);
    }

    /**
     * 保存到SD卡
     *
     * @return
     */
    public static String getSDCradPath() {
        return Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";
    }

    /**
     * 将图片转换成字符串
     */
    public static final String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteImag = baos.toByteArray();
        return Base64.encodeToString(byteImag, Base64.DEFAULT);
    }

    /**
     * 截取字符串
     *
     * @param string
     * @param length
     * @param startPosition
     * @param endPosition
     * @param resultType
     * @return
     */
    public static String subStr(String string, int length, int startPosition, int endPosition, int resultType) {
        if (string != null) {
            if (string.length() > length) {
                return resultType == 3 ? string.substring(startPosition, endPosition) + "..." : string.substring(startPosition, endPosition);
            } else {
                return string;
            }
        } else {
            return "";
        }
    }

    /**
     * @return
     * @Description:获取当前应用的名称
     */
    public static String getApplicationName(Context context, String appPackageNmae) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(appPackageNmae, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取系统的版本号
     *
     * @return
     */
    public static String getAppVersionName() {
        try {
            PackageManager manager = CommonUtils.sContext.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(CommonUtils.sContext.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            LogUtil.i("获取版本号失败");
            return "";
        }
    }

    /**
     * 方法名称:transMapToString
     * 传入参数:map
     * 返回值:String 形如 username'chenziwen^password'1234
     */
    public static String transMapToString(Map map){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");
        }
        return sb.toString();
    }

    /**
     * 方法名称:transStringToMap
     * 传入参数:mapString 形如 username'chenziwen^password'1234
     * 返回值:Map
     */
    public static Map transStringToMap(String mapString){
        Map map = new HashMap();
        StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens();
            map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
            items = new StringTokenizer(entrys.nextToken(), "'");
        return map;
    }
}
