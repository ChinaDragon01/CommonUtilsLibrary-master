package com.chinadragon.commonutilslibrary;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/12/26 17:44
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class SpannableStringBuilderUtil {

    /**
     * 修改字体颜色，不带控件的方法
     *
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static final SpannableStringBuilder setSectionTextForegroundColor(String text, int start, int end, int color) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;
    }

    /**
     * 修改字体颜色，带控件的方法
     *
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static final void setSectionTextForegroundColor(TextView tv,String text, int start, int end, int color) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableStringBuilder);
    }

    /**
     * 设置字体背景色，带控件的方法
     *
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static final void setSectionTextBackgroundColor(TextView tv,String text, int start, int end, int color) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(color);
        spannableStringBuilder.setSpan(backgroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableStringBuilder);
    }

    /**
     * 同时修改字体大小和字体颜色，不带控件的方法
     *
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static final SpannableStringBuilder setSectionTextForegroundColor(int textSize, String text, int start, int end, int color) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(textSize);
        spannableStringBuilder.setSpan(absoluteSizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;
    }

    /**
     * 同时修改字体大小和字体颜色，带控件的方法
     *
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static final void setSectionTextForegroundColor(TextView tv,int textSize, String text, int start, int end, int color) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(textSize);
        spannableStringBuilder.setSpan(absoluteSizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableStringBuilder);
    }
}
