package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * *******************************************************
 * Author: zhangbenlong
 * Time: 2019/10/6 16:01
 * Name:动态创建View
 * Overview:
 * Usage:
 * *******************************************************
 */
public class ViewUtils {
    /**
     * 创建 LinearLayout
     *
     * @param context
     * @param width
     * @param height
     * @return LinearLayout
     */
    public static LinearLayout createLinearLayout(Context context, int width, int height) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        return linearLayout;
    }
}
