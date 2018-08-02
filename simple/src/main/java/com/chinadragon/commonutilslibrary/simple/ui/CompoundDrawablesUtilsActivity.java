package com.chinadragon.commonutilslibrary.simple.ui;

import android.widget.TextView;

import com.chinadragon.commonutilslibrary.CompoundDrawablesUtils;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/27 17:38
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class CompoundDrawablesUtilsActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_compoundDrawables_top)
    TextView tvCompoundDrawablesTop;
    @BindView(R.id.tv_compoundDrawables_bottom)
    TextView tvCompoundDrawablesBottom;
    @BindView(R.id.tv_compoundDrawables_left)
    TextView tvCompoundDrawablesLeft;
    @BindView(R.id.tv_compoundDrawables_right)
    TextView tvCompoundDrawablesRight;

    @Override
    public void initData() {
        super.initData();
        CompoundDrawablesUtils.setCompoundDrawables(tvCompoundDrawablesTop, R.mipmap.ic_launcher_round, 2);
        CompoundDrawablesUtils.setCompoundDrawables(tvCompoundDrawablesBottom, R.mipmap.ic_launcher_round, 4);
        CompoundDrawablesUtils.setCompoundDrawables(tvCompoundDrawablesLeft, R.mipmap.ic_launcher_round, 1);
        CompoundDrawablesUtils.setCompoundDrawables(tvCompoundDrawablesRight, R.mipmap.ic_launcher_round, 3);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_compounddrawablesutils;
    }

}
