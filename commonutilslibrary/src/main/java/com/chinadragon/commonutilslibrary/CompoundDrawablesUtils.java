package com.chinadragon.commonutilslibrary;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/4 9:10
 * Name: 设置文本与图片相对位置
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class CompoundDrawablesUtils {
    private static final int ZERO = 0, ONE = 1, TWO = 2, THREE = 3, FOUR = 4;
    /**
     *
     * @param textView
     * @param drawableRes
     * @param direction 1：左边；2：顶部；3：右边；4：底部
     * @param text
     */
    public static void setCompoundDrawables(TextView textView, int drawableRes, int direction, String text) {
        TextView tv = textView;
        tv.setText(text);
        Drawable drawable =CommonUtils.sContext.getResources().getDrawable(drawableRes);
        drawable.setBounds(ZERO,ZERO, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (direction) {
            case ONE:
                tv.setCompoundDrawables(drawable, null, null, null);
                break;
            case TWO:
                tv.setCompoundDrawables(null, drawable, null, null);
                break;
            case THREE:
                tv.setCompoundDrawables(null, null, drawable, null);
                break;
            case FOUR:
                tv.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

    /**
     *
     * @param textView
     * @param drawableRes
     * @param direction 1：左边；2：顶部；3：右边；4：底部
     */
    public static void setCompoundDrawables(TextView textView, int drawableRes, int direction) {
        TextView tv = textView;
        Drawable drawable =CommonUtils.sContext.getResources().getDrawable(drawableRes);
        drawable.setBounds(ZERO, ZERO, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (direction) {
            case ONE:
                tv.setCompoundDrawables(drawable, null, null, null);
                break;
            case TWO:
                tv.setCompoundDrawables(null, drawable, null, null);
                break;
            case THREE:
                tv.setCompoundDrawables(null, null, drawable, null);
                break;
            case FOUR:
                tv.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

}
