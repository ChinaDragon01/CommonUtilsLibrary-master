package com.chinadragon.commonutilslibrary.time;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.R;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/18 16:08
 * Name: 倒计时工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class TimeCountDown extends CountDownTimer {
    private TextView mTextView;
    private String mText;

    public TimeCountDown(long millisInFuture, long countDownInterval, TextView textView, String text) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.mText = text;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false);
        if (mText == null) {
            mTextView.setText(millisUntilFinished / 1000 + "秒后重新获取");
        } else {
            mTextView.setText(millisUntilFinished / 1000 + mText);
        }

    }

    @Override
    public void onFinish() {
        mTextView.setText(R.string.re_obtain);
        mTextView.setEnabled(true);
    }
}
