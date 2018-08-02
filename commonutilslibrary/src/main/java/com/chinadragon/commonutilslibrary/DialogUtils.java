package com.chinadragon.commonutilslibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.chinadragon.commonutilslibrary.interfaces.DialogCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/31 16:27
 * Name: 对话框工具类
 * Overview: 单选对话框、多选对话框
 * Usage:
 * **********************************************************************
 */

public class DialogUtils {

    public static void showSingleChoiceDialog(Activity activity, String title, final String[] items, boolean cancelable, final DialogCallBack dialogCallBack) {
        final int[] index = {0};
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setSingleChoiceItems(items, index[0], new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index[0] = which;
                        LogUtil.i("setSingleChoiceItems which : " + which);
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogUtil.i("setPositiveButton which：" + which);
                dialogCallBack.certain(items[index[0]]);
                ToastUtil.showShort(items[index[0]]);
                index[0] = 0;
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                index[0] = 0;
                dialog.dismiss();
            }
        }).setCancelable(cancelable).show();
    }

    public static void showMultiChoiceDialog(Activity activity, String title, final String[] items, boolean cancelable,final DialogCallBack dialogCallBack) {
        final List<String> list = new ArrayList<>();
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                if (isChecked) {
                                    list.add(items[which]);
                                } else if (list.contains(items[which])) {
                                    list.remove(items[which]);
                                } else {
                                    list.clear();
                                }
                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogCallBack.certain(list.toString());
                        list.clear();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogCallBack.cancel();
                list.clear();
                dialog.dismiss();
            }
        }).setCancelable(cancelable).show();
    }

    public static ProgressDialog showProgressDialog(Activity activity, String title,int max) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setTitle(title);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(max);
        pd.setMessage("更新进度的");
        pd.setProgress(50);
//        pd.show();
        return pd;
    }
}
