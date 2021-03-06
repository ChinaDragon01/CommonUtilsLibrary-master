package com.chinadragon.commonutilslibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

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
     * 得到设备屏幕的密度
     */
    public static final float getScreenScaledDensity() {
        return CommonUtils.sContext.getResources().getDisplayMetrics().scaledDensity;
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
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getScreenScaledDensity();
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getScreenScaledDensity();
        return (int) (spValue * fontScale + 0.5f);
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

    /**
     * 得到控件测量之后的高度
     *
     * @param view
     * @return
     */
    public static int getMeasuredHeight(View view) {

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    /**
     * 得到控件测量之后的宽度
     *
     * @param view
     * @return
     */
    public static int getMeasuredWidth(View view) {

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    /**
     * 获取顶部statusbar的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * (底部导航栏)
     * 获取底部navigationbar的高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    /**
     * 检查是否有navigationbar显示
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            LogUtil.e("get navigation bar height failed! >> 获取 navigationbar  高度出现异常： "+e.toString());
            e.printStackTrace();
        }

        return hasNavigationBar;
    }


}
