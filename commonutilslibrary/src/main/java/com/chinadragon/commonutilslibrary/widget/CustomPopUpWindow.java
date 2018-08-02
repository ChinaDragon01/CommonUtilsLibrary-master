package com.chinadragon.commonutilslibrary.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.chinadragon.commonutilslibrary.ScreenUtil;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/11/20 22:48
 * Name: 自定义PopUpWindow
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class CustomPopUpWindow extends PopupWindow implements PopupWindow.OnDismissListener {
    private Activity mActivity;

    public CustomPopUpWindow(Activity context, View view, int width, int height) {
        super(context);
        this.mActivity = context;
        initView(view, width, height);
    }

    public CustomPopUpWindow(Activity context, View view, int width, int height, View anchor, int xoff, int yoff) {
        super(context);
        this.mActivity = context;
        initView(view, width, height, anchor, xoff, yoff);
    }

    public CustomPopUpWindow(Activity context, View view, int width, int height, View anchor, int xoff, int yoff, int animationStyle) {
        super(context);
        this.mActivity = context;
        initView(view, width, height, anchor, xoff, yoff, animationStyle);
    }

    public CustomPopUpWindow(Activity context, View view, int width, int height, View parent, int gravity, int x, int y, int animationStyle) {
        super(context);
        this.mActivity = context;
        initView(view, width, height, parent, gravity, x, y, animationStyle);
    }

    private void initCommonSetting(View view, int width, int height) {
//        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
//        LayoutInflater layoutInflater2 = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mView = layoutInflater.inflate(R.layout.custom_popupwindow, null);
        this.setOnDismissListener(this);
        this.setContentView(view);
        this.setWidth(width);
        this.setHeight(height);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
//        ColorDrawable colorDrawable = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7b7b7c")));
        ScreenUtil.setWindowBackgroundAlpha(mActivity, 0.5f);
    }

    private void initView(View view, int width, int height) {
        initCommonSetting(view, width, height);
    }

    private void initView(View view, int width, int height, View anchor, int xoff, int yoff) {
        initCommonSetting(view, width, height);
        this.showAsDropDown(anchor, xoff, yoff);
    }

    private void initView(View view, int width, int height, View anchor, int xoff, int yoff, int animationStyle) {
        initCommonSetting(view, width, height);
        this.showAsDropDown(anchor, xoff, yoff);
        this.setAnimationStyle(animationStyle);
    }

    /**
     *
     * @param view
     * @param width
     * @param height
     * @param parent
     * @param gravity
     * @param x
     * @param y
     * @param animationStyle 如果想让CustomPopUpWindow以showAtLocation显示出来没有动画效果，该值必须设为0
     */
    private void initView(View view, int width, int height, View parent, int gravity, int x, int y, int animationStyle) {
        initCommonSetting(view, width, height);
        this.showAtLocation(parent, gravity, x, y);
        if (animationStyle != 0)
            this.setAnimationStyle(animationStyle);
    }

    @Override
    public void onDismiss() {
        dismiss();
        ScreenUtil.setWindowBackgroundAlpha(mActivity, 1f);
    }
}
