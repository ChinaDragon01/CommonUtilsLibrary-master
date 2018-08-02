package com.chinadragon.commonutilslibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/9/26 10:07
 * Name: 屏幕工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class ScreenUtil {

    /**
     * 设置屏幕全屏
     *
     * @param activity
     */
    public static final void setScreenFull(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 退出全屏
     *
     * @param activity
     */
    public static final void quitScreenFull(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * 得到屏幕的宽度(像素)
     */
    public static final int getScreenWidth() {
        return CommonUtils.sContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到屏幕的高度(像素)
     */
    public static final int getScreenHeight() {
        return CommonUtils.sContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备屏幕的密度
     */
    public static final float getScreenDensity() {
        return CommonUtils.sContext.getResources().getDisplayMetrics().density;
    }

    /**
     * 得到设备屏幕的密度Dpi
     */
    public static final int getScreenDensityDpi() {
        return CommonUtils.sContext.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 获得设备屏幕正确的尺寸
     * <p>
     * 主要使用getRealSize()
     */
    @SuppressLint("NewApi")
    public static final double getScreenSizeOfDevice(Activity activity) {
        DisplayMetrics dm = CommonUtils.sContext.getResources().getDisplayMetrics();
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        double x = Math.pow((point.x / dm.xdpi), 2);
        double y = Math.pow((point.y / dm.ydpi), 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }

    /**
     * 将px值转换为dip或dp值
     *
     * @param pxValue
     * @return
     */
    public static int pxToDip(float pxValue) {
        float scale = getScreenDensity();//scale是DisplayMetrics类中属性density
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 把密度转换为像素
     */
    public static final int dipToPx(float dpValue) {
        float scale = getScreenDensity();//scale是DisplayMetrics类中属性density
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * Dialog弹出之后屏幕暗度调整
     */
    public static void setDimAmount(Dialog dialog, float dimAmount) {
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = dimAmount;
            window.setAttributes(params);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void setWindowBackgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高
     *
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParamsWrapContent() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高
     *
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParamsMatchParentWrapContent() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高
     *
     * @param width
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParamsWidth(float width) {
        return new LinearLayout.LayoutParams((int) ((getScreenWidth() * width) + 0.5),LinearLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高
     *
     * @param height
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParamsHeight(float height) {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) ((getScreenHeight() * height) + 0.5));
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高
     *
     * @param width
     * @param height
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParams(float width, float height) {
        return new LinearLayout.LayoutParams((int) ((getScreenWidth() * width) + 0.5), (int) ((getScreenHeight() * height) + 0.5));
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高和Margins
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParams(int left, int top, int right, int bottom) {
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) ((getScreenWidth() * width) + 0.5), (int) ((getScreenHeight() * height) + 0.5));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left,top,right,bottom);
        return lp;
    }

    /**
     * 用于在LinearLayout布局里动态设置布局和控件的宽高和Margins
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return LinearLayout.LayoutParams
     */
    public static final LinearLayout.LayoutParams getLinearLayoutParamsWrapContent(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = getLinearLayoutParamsMatchParentWrapContent();
        lp.setMargins(left,top,right,bottom);
        return lp;
    }

    /**
     * 用于在RelativeLayout布局里动态设置布局和控件的宽高
     *
     * @param width
     * @param height
     * @return RelativeLayout.LayoutParams
     */
    public static final RelativeLayout.LayoutParams getRelativeLayoutParams(float width, float height) {
        return new RelativeLayout.LayoutParams((int) ((getScreenWidth() * width) + 0.5), (int) ((getScreenHeight() * height) + 0.5));
    }

    /**
     * 用于在Relative布局里动态设置布局和控件的宽高
     *
     * @return Relative.LayoutParams
     */
    public static final RelativeLayout.LayoutParams getRelativeLayoutParamsMatchParent() {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 用于在ViewGroup布局里动态设置布局和控件的宽高
     *
     * @param width
     * @param height
     * @return ViewGroup.LayoutParams
     */
    public static final ViewGroup.LayoutParams getViewGroupParams(float width, float height) {
        return new ViewGroup.LayoutParams((int) ((getScreenWidth() * width) + 0.5), (int) ((getScreenHeight() * height) + 0.5));
    }

}
