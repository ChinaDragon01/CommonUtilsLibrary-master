package com.chinadragon.commonutilslibrary.simple.ui.widget;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.ScreenUtil;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.time.WeakHandlerUtil;
import com.chinadragon.commonutilslibrary.widget.CustomClearEditText;
import com.chinadragon.commonutilslibrary.widget.CustomDialog;
import com.chinadragon.commonutilslibrary.widget.CustomPopUpWindow;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/27 14:23
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class WidgetActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_customdialog)
    TextView tvCustomdialog;
    @BindView(R.id.tv_custompopupwindow)
    TextView tvCustompopupwindow;
    @BindView(R.id.et_customclearedittext)
    CustomClearEditText etCustomclearedittext;
    private View mParent;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        etCustomclearedittext.setShakeAnimation();// 开启晃动动画，这个可以在判断CustomClearEditText的内容为空的时候再调用
    }

    @Override
    public void initData() {
        super.initData();
        mParent = View.inflate(this, R.layout.activity_widget, null);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        tvCustompopupwindow.setOnClickListener(v -> {
            creatPopUpWindow();
        });
        tvCustomdialog.setOnClickListener(v -> {
            CustomDialog customDialog = new CustomDialog(this, "正在加载...");
            customDialog.show();
            new WeakHandlerUtil().postDelayed(() -> {
                customDialog.dialogDismiss(customDialog);
                tipDialog();
            }, 1000);

        });
    }

    private void tipDialog() {
        CustomDialog tipDialog = new CustomDialog(WidgetActivity.this, R.layout.dialog_message, Gravity.CENTER, (int) (ScreenUtil.getScreenWidth() * 0.6), LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tvMessageContent = (TextView) tipDialog.findViewById(R.id.tv_message_content);
        TextView tvCancle = (TextView) tipDialog.findViewById(R.id.tv_cancle);
        TextView tvCertain = (TextView) tipDialog.findViewById(R.id.tv_certain);
        tvMessageContent.setText("近期高温天气，请大家做好防暑准备！");
        tvCancle.setOnClickListener(cancle -> {
            tipDialog.dialogDismiss(tipDialog);
        });

        tvCertain.setOnClickListener(certain -> {
            tipDialog.dialogDismiss(tipDialog);

        });
        tipDialog.setCancelable(true);
        tipDialog.show();
        tipDialog.setOnCancelListener(dialog -> {
        });
    }

    private void creatPopUpWindow() {
        View view = View.inflate(this, R.layout.dialog_message, null);
        TextView tvMessageContent = (TextView) view.findViewById(R.id.tv_message_content);
        TextView tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
        TextView tvCertain = (TextView) view.findViewById(R.id.tv_certain);
        tvMessageContent.setText("清除缓存数据");
        tvCancle.setText(R.string.cancel);
        tvCertain.setText(R.string.certain);
        CustomPopUpWindow customPopUpWindow = new CustomPopUpWindow(this, view,
                (int) (ScreenUtil.getScreenWidth() * 0.6), LinearLayout.LayoutParams.WRAP_CONTENT, mParent, Gravity.CENTER, 0, 0, 0);
        customPopUpWindow.setFocusable(false);
        customPopUpWindow.setTouchable(false);
        customPopUpWindow.setOutsideTouchable(false);
        tvCancle.setOnClickListener(v -> {
            customPopUpWindow.dismiss();
        });
        tvCertain.setOnClickListener(v -> {
            customPopUpWindow.dismiss();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_widget;
    }

}
