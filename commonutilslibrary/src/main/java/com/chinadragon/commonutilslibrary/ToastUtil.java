package com.chinadragon.commonutilslibrary;

import android.view.Gravity;
import android.widget.Toast;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/14 10:21
 * Name: Toast工具类
 * Overview:
 * 1、快速弹出下一个Toast
 * 2、页面退出时，Toast快速消失
 * Usage:
 * 1、在页面里通过ToastUtil直接调用对应的方法，实例如下：
 * ToastUtil.showShort("演示");
 * 2、在页面的生命周期onPause通过ToastUtil直接调用cancelToast()
 * **********************************************************************
 */
public class ToastUtil {
    public static ToastUtil sToastUtilInstance;
    public static Toast sToast;

    public ToastUtil() {
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (null == sToast) {
            if (null == CommonUtils.sContext) {
                return;
            }
            sToast = Toast.makeText(CommonUtils.sContext, message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
        }
        sToast.show();

    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (null == sToast) {
            if (null == CommonUtils.sContext) {
                return;
            }
            sToast = Toast.makeText(CommonUtils.sContext, message, Toast.LENGTH_LONG);
        } else {
            sToast.setText(message);
        }
        sToast.show();

    }

    /**
     * 在屏幕中间展示toast提示
     *
     * @param message
     * @return Toast
     */
    public static void showGravityCenter(String message) {

        if (null == sToast) {
            if (null == CommonUtils.sContext) {
                return;
            }
            sToast = Toast.makeText(CommonUtils.sContext, message, Toast.LENGTH_LONG);
        } else {
            sToast.setText(message);
        }
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }

    public static void cancelToast() {
        if (null != sToast) {
            sToast.cancel();
        }
    }

    private static synchronized void syncInit() {
        if (sToastUtilInstance == null) {
            sToastUtilInstance = new ToastUtil();
        }
    }

    public static ToastUtil getToastUtilInstance() {
        if (null == sToastUtilInstance) {
            syncInit();
        }
        return sToastUtilInstance;
    }
}
