package com.chinadragon.commonutilslibrary.time;

import android.view.View;

import com.chinadragon.commonutilslibrary.ToastUtil;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/9/27 10:09
 * Name: 自定义按钮监听事件
 * Overview: 防止按钮重复点击
 * Usage:
 * **********************************************************************
 */

public abstract class onNoMultiClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime = 0;
    public abstract void onNoMultiClick(View view);
    @Override
    public void onClick(View view) {
        if (System.currentTimeMillis() - lastClickTime >= MIN_CLICK_DELAY_TIME){
            lastClickTime = System.currentTimeMillis();
            onNoMultiClick(view);
        }else {
            ToastUtil.showShort("两秒内不能重复点击");
        }
    }
}
