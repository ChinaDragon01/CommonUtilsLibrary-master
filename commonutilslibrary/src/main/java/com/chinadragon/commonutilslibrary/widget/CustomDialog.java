package com.chinadragon.commonutilslibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.R;
import com.chinadragon.commonutilslibrary.ScreenUtil;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/10 22:32
 * Name: 自定义对话框
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class CustomDialog extends Dialog {

    private TextView mTvDialogLoadingTip;
    private View mView;
    private ImageView mIvDialogLoading;

    public CustomDialog(final Context context, final int layout, final int theme, final int gravity, final int width, final int height) {
        super(context, theme);
        this.setContentView(layout);
        final Window dialogWindow = this.getWindow();
        dialogWindow.setLayout(width, height);
        dialogWindow.setGravity(gravity);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    public CustomDialog(final Context context, final int layout) {
        super(context, R.style.DialogCustom);
        this.setContentView(layout);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    public CustomDialog(final Context context, final int layout, final int gravity, final int width, final int height) {
        this(context, layout, R.style.DialogCustom, gravity, width, height);
    }

    public CustomDialog(Context context, String text) {
        super(context, R.style.dialog_loading);
        commonInit(context);
        viewSetPadding(text);
        mTvDialogLoadingTip.setText(text);
        commonSetting();
    }

    public CustomDialog(Context context) {
        super(context, R.style.dialog_loading);
        commonInit(context);
        mView.setPadding(40, 24, 40, 24);
        mTvDialogLoadingTip.setText(R.string.please_wait);
        commonSetting();
    }

    /**
     * 设置提示内容
     *
     * @param text
     */
    public void setMessage(String text) {
            if (!TextUtils.isEmpty(text)) {
                viewSetPadding(text);
                mTvDialogLoadingTip.setText(text);
            }
    }

    private void viewSetPadding(String text) {
        int length = text.length();
        if (length <= 6)
            mView.setPadding(40, 24, 40, 24);
    }

    private void commonInit(Context context) {
        mView = View.inflate(context, R.layout.dialog_loading, null);
        Animation ivDialogLoadingAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_loading_animation);
        mIvDialogLoading = (ImageView) mView.findViewById(R.id.iv_dialog_loading);
        mTvDialogLoadingTip = (TextView) mView.findViewById(R.id.tv_dialog_loading_tip);
        mIvDialogLoading.startAnimation(ivDialogLoadingAnimation);
    }

    private void commonSetting() {
        this.setContentView(mView, ScreenUtil.getLinearLayoutParamsWrapContent());
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    /**
     * 对话框消失
     */
    public void dialogDismiss(CustomDialog customDialog) {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
            customDialog = null;
        }
    }
}

