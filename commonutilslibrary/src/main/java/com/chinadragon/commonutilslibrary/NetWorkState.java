package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/4 9:03
 * Name: 检测网络工具类
 * Overview: 检查当前网络是否可用 以及 网络类型
 * Usage:
 * 参考博客：http://blog.csdn.net/mxiaoyem/article/details/50708052
 * **********************************************************************
 */

public class NetWorkState {

    public static final int NETWORK_NONE = -1;// 没有连接网络
    public static final int NETWORK_MOBILE = 0;// 移动网络
    public static final int NETWORK_WIFI = 1;// 无线网络
    public static String mNetWorkName;

    /**
     * 检查当前网络是否可用 以及 网络类型
     *
     * @return
     */
    public static int isNetworkAvailable() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) CommonUtils.sContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return NETWORK_NONE;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != activeNetworkInfo && activeNetworkInfo.isConnectedOrConnecting()){
                if (ConnectivityManager.TYPE_WIFI == activeNetworkInfo.getType()){
                    LogUtil.i("当前链接的 wifi 网络 networkInfo getType: " +activeNetworkInfo.getType()+"，getTypeName："+activeNetworkInfo.getTypeName()+"，getExtraInfo："+activeNetworkInfo.getExtraInfo());
                    mNetWorkName = activeNetworkInfo.getExtraInfo();
                    return NETWORK_WIFI;
                }else if (ConnectivityManager.TYPE_MOBILE == activeNetworkInfo.getType()){
                    LogUtil.i("当前链接的 mobilbe 网络 networkInfo getType: " + activeNetworkInfo.getType()+"，getTypeName："+activeNetworkInfo.getTypeName()+"，getExtraInfo："+activeNetworkInfo.getExtraInfo());
                    mNetWorkName = activeNetworkInfo.getExtraInfo();
                    return NETWORK_MOBILE;
                }
            }else {
                    return NETWORK_NONE;
            }
        }
        return NETWORK_NONE;
    }
}
