package com.chinadragon.commonutilslibrary.simple.ui;

import android.graphics.Color;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.SpannableStringBuilderUtil;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/29 18:05
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class SpannableStringBuilderUtilActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_textForegroundColor)
    TextView tvTextForegroundColor;
    @BindView(R.id.tv_textBackgroundColor)
    TextView tvTextBackgroundColor;
    @BindView(R.id.tv_textForegroundColorAndSize)
    TextView tvTextBackgroundColorAndSize;

    @Override
    public void initData() {
        super.initData();
        String text = tvTextForegroundColor.getText().toString().trim();
        String text2 = tvTextBackgroundColor.getText().toString().trim();
        String text3 = tvTextBackgroundColorAndSize.getText().toString().trim();
        SpannableStringBuilderUtil.setSectionTextForegroundColor(tvTextForegroundColor, text, 2, 4, Color.parseColor("#ff4040"));
        SpannableStringBuilderUtil.setSectionTextBackgroundColor(tvTextBackgroundColor, text2, 2, 4, Color.parseColor("#ff4040"));
        SpannableStringBuilderUtil.setSectionTextForegroundColor(tvTextBackgroundColorAndSize, 20, text3, 2, 4, Color.parseColor("#ff4040"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spannablestringbuilderutil;
    }

}
