package com.chinadragon.commonutilslibrary.simple.base;

import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.chinadragon.commonutilslibrary.AppActivityManager;
import com.chinadragon.commonutilslibrary.LogUtil;
import com.chinadragon.commonutilslibrary.NetWorkState;
import com.chinadragon.commonutilslibrary.ToastUtil;
import com.chinadragon.commonutilslibrary.broadcastreceiver.BaseBroadCastReceiver;

import butterknife.ButterKnife;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/2/22 15:12
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseInterface, BaseBroadCastReceiver.BaseBroadCastReceiverInterface {
    private BaseBroadCastReceiver mBaseBroadCastReceiver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        logInfo(getClass().getName() + "：***** < *---* " + getClass().getSimpleName() + " *---* > *****");
        setTitle(getClass().getSimpleName());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppActivityManager.getInstance().addActivity(this);
        if (mBaseBroadCastReceiver == null) {
            mBaseBroadCastReceiver = new BaseBroadCastReceiver(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mBaseBroadCastReceiver, intentFilter);
        }
        initView(savedInstanceState);
        initData();
        initEvent();
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    protected abstract int getLayoutId();

    @Override
    protected void onPause() {
        super.onPause();
        ToastUtil.cancelToast();
    }

    protected void logInfo(String logText) {
        if (TextUtils.isEmpty(logText)) {
            return;
        }
        LogUtil.i(logText);
    }

    protected void showToastShort(String toastText) {
        if (TextUtils.isEmpty(toastText)) {
            return;
        }
        ToastUtil.showShort(toastText);
    }

    protected void showToastLong(String toastText) {
        if (TextUtils.isEmpty(toastText)) {
            return;
        }
        ToastUtil.showLong(toastText);
    }

    @Override
    public void isNetworkAvailable(int type) {
        switch (type) {
            case NetWorkState.NETWORK_NONE:
                logInfo("===== NETWORK_NONE");
                showToastShort("app 当前没有网络可用");
                break;
            case NetWorkState.NETWORK_MOBILE:
                logInfo("===== NETWORK_MOBILE");
                showToastShort("app 切换到移动网络/流量");
                break;
            case NetWorkState.NETWORK_WIFI:
                logInfo("===== NETWORK_WIFI");
                showToastShort("app 已连接" + NetWorkState.mNetWorkName);
                break;
        }
    }
}
