package com.chinadragon.commonutilslibrary.simple.ui;

import android.widget.TextView;

import com.chinadragon.commonutilslibrary.DialogUtils;
import com.chinadragon.commonutilslibrary.interfaces.DialogCallBack;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/27 17:48
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class DialogUtilsActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_singleChoiceDialog)
    TextView tvSingleChoiceDialog;
    @BindView(R.id.tv_multiChoiceDialog)
    TextView tvMultiChoiceDialog;

    @Override
    public void initEvent() {
        super.initEvent();
        tvSingleChoiceDialog.setOnClickListener(v -> {
            showSingleChoiceDialog();
        });
        tvMultiChoiceDialog.setOnClickListener(v -> {
            showMultiChoiceDialog();
        });
    }

    private void showMultiChoiceDialog() {
        String[] multiItems = {"选择一", "选择二", "选择三"};
        DialogUtils.showMultiChoiceDialog(this, "多选对话框", multiItems, false, new DialogCallBack() {
            @Override
            public void certain(String itemName) {
                showToastShort(itemName);
            }

            @Override
            public void cancel() {

            }
        });
    }

    private void showSingleChoiceDialog() {
        String[] singleItems = {"单选一", "单选二", "单选三"};
        DialogUtils.showSingleChoiceDialog(this, "单选对话框", singleItems, false, new DialogCallBack() {
            @Override
            public void certain(String itemName) {
                switch (itemName) {
                    case "单选一":
                        showToastShort("选择单选一");
                        break;
                    case "单选二":
                        showToastShort("选择单选二");
                        break;
                    case "单选三":
                        showToastShort("选择单选三");
                        break;
                }
            }

            @Override
            public void cancel() {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialogutils;
    }

}
