package com.chinadragon.commonutilslibrary.simple.ui.time;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.constants.AppConstants;
import com.chinadragon.commonutilslibrary.time.DateUtil;
import com.chinadragon.commonutilslibrary.time.TimeCount;
import com.chinadragon.commonutilslibrary.time.TimeCountDown;
import com.chinadragon.commonutilslibrary.time.WeakHandlerUtil;
import com.chinadragon.commonutilslibrary.time.onNoMultiClickListener;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/26 22:22
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class TimeCountActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_currettime)
    TextView tvCurrettime;
    @BindView(R.id.tv_date_util)
    TextView tvDateUtil;
    @BindView(R.id.tv_no_multiclick)
    TextView tvNoMulticlick;
    @BindView(R.id.tv_totalDuration)
    TextView tvTotalDuration;
    @BindView(R.id.tv_recordtime)
    TextView tvRecordTime;
    @BindView(R.id.chronometer)
    Chronometer mChronometer;
    @BindView(R.id.tv_recordtime2)
    TextView tvRecordTime2;
    @BindView(R.id.tv_getverfiycode)
    TextView tvGetverfiycode;
    @BindView(R.id.tv_weakHandlerUtil)
    TextView tvWeakHandlerUtil;
    private long startTime;
    private boolean isRecordTime;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        startTime = SystemClock.uptimeMillis();
        logInfo("startTime = "+startTime);
    }

    @Override
    public void initData() {
        super.initData();
        String timesToNow = DateUtil.getTimesToNow("yyyy-MM-dd HH:mm:ss", "2018-07-27 09:01:12");// 两个参数日期的格式一致
        logInfo(timesToNow);
        tvDateUtil.setText(timesToNow + "发布此框架");

    }

    @Override
    public void initEvent() {
        super.initEvent();
        final int[] clickNum = {0};
        tvNoMulticlick.setOnClickListener(new onNoMultiClickListener() {
            @Override
            public void onNoMultiClick(View view) {// 设定两秒内不能重复点击
                clickNum[0]++;
                String currentTime = TimeCount.currentTime(null);
                showToastLong("第" + clickNum[0] + "点击，当前时间:" + currentTime);

            }
        });
        setOnClicklistener(tvCurrettime, AppConstants.ONE);
        setOnClicklistener(tvTotalDuration, AppConstants.TWO);
        setOnClicklistener(tvRecordTime, AppConstants.THREE);
        setOnClicklistener(tvGetverfiycode, AppConstants.FOUR);
        setOnClicklistener(tvWeakHandlerUtil, AppConstants.FIVE);
    }

    private void setOnClicklistener(View view, int type) {
        view.setOnClickListener(v -> {
            switch (type) {
                case AppConstants.ONE:
                    tvCurrettime.setText("获取当前时间为：" + TimeCount.currentTime(null));
                    logInfo("TimeCount.currentTime() = "+TimeCount.currentTime("yyyy-MM-dd HH:mm:ss"));
                    break;
                case AppConstants.TWO:
                    totalDuration();
                    break;
                case AppConstants.THREE:
                    recordtime();
                    break;
                case AppConstants.FOUR:
                    new TimeCountDown(60000, 1000, tvGetverfiycode).start();
                    break;
                case AppConstants.FIVE:
                    showToastShort("3秒钟后将有提示信息");
                    new WeakHandlerUtil().postDelayed(() -> {
                        showToastShort("3秒钟倒计时已经结束了");
                    }, 3000);
                    break;
            }
        });
    }

    private void totalDuration() {
        long stopUptimeMillis = SystemClock.uptimeMillis();
        logInfo("stopUptimeMillis = "+stopUptimeMillis);
        String totalDuration = TimeCount.timeData(stopUptimeMillis - startTime);// 记录总时间
        tvTotalDuration.setText("刷新停留的时长：" + totalDuration);
    }

    private void recordtime() {
        isRecordTime = !isRecordTime;
        if (isRecordTime) {
            tvRecordTime.setText("停止计时");
            TimeCount.startChronometer(mChronometer);
            TimeCount.updateRecordingTime(tvRecordTime2);
        } else {
            tvRecordTime.setText("开始计时");
            TimeCount.stopChronometer(mChronometer);
            TimeCount.removeCallbacks();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_time;
    }

}
