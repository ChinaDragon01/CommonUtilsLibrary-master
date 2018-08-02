package com.chinadragon.commonutilslibrary.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.chinadragon.commonutilslibrary.LogUtil;
import com.chinadragon.commonutilslibrary.NetWorkState;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/8/21 10:18
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class BaseBroadCastReceiver extends BroadcastReceiver {
    private BaseBroadCastReceiverInterface mBaseBroadCastReceiverInterface;

    public BaseBroadCastReceiver(BaseBroadCastReceiverInterface baseBroadCastReceiverInterface) {
        this.mBaseBroadCastReceiverInterface = baseBroadCastReceiverInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.i("接收到广播 action " + action);
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            mBaseBroadCastReceiverInterface.isNetworkAvailable(NetWorkState.isNetworkAvailable());
        }
    }

    public interface BaseBroadCastReceiverInterface {
        void isNetworkAvailable(int type);
    }
}
