package com.chinadragon.commonutilslibrary.simple.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.SharedPreferencesUtil;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.constants.AppConstants;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/29 16:58
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class SharedPreferencesUtilActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_int_num)
    TextView tvIntNum;
    @BindView(R.id.tv_long_num)
    TextView tvLongNum;
    @BindView(R.id.tv_string_text)
    TextView tvStringText;
    @BindView(R.id.tv_show_data)
    TextView tvShowData;
    @BindView(R.id.tv_save_int_num)
    TextView tvSaveIntNum;
    @BindView(R.id.tv_get_int_num)
    TextView tvGetIntNum;
    @BindView(R.id.tv_remove_int_num)
    TextView tvRemoveIntNum;
    @BindView(R.id.tv_save_long_num)
    TextView tvSaveLongNum;
    @BindView(R.id.tv_get_long_num)
    TextView tvGetLongNum;
    @BindView(R.id.tv_remove_long_num)
    TextView tvRemoveLongNum;
    @BindView(R.id.tv_save_string)
    TextView tvSaveString;
    @BindView(R.id.tv_get_string)
    TextView tvGetString;
    @BindView(R.id.tv_remove_string)
    TextView tvRemoveString;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    private int mIntNum;
    private long mLongNum;
    private String mStringText;

    @Override
    public void initData() {
        super.initData();
        SharedPreferencesUtil.saveBoolean("isCacheClear", true);
        mIntNum = Integer.parseInt(tvIntNum.getText().toString().trim());
        mLongNum = Long.parseLong(tvLongNum.getText().toString().trim());
        mStringText = tvStringText.getText().toString().trim();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        setOnClicklistener(tvSaveIntNum, AppConstants.ONE);
        setOnClicklistener(tvGetIntNum, AppConstants.TWO);
        setOnClicklistener(tvRemoveIntNum, AppConstants.THREE);
        setOnClicklistener(tvSaveLongNum, AppConstants.FOUR);
        setOnClicklistener(tvGetLongNum, AppConstants.FIVE);
        setOnClicklistener(tvRemoveLongNum, AppConstants.SIX);
        setOnClicklistener(tvSaveString, AppConstants.SEVEN);
        setOnClicklistener(tvGetString, AppConstants.EIGHT);
        setOnClicklistener(tvRemoveString, AppConstants.NINE);
        setOnClicklistener(tvClearCache, AppConstants.TEN);
    }

    private void setOnClicklistener(View view, int type) {
        view.setOnClickListener(v -> {
            switch (type) {
                case AppConstants.ONE:
                    SharedPreferencesUtil.saveInt("mIntNum", mIntNum);
                    SharedPreferencesUtil.saveBoolean("isCacheClear", false);
                    break;
                case AppConstants.TWO:
                    int tempIntNum = SharedPreferencesUtil.getInt("mIntNum");
                    if (tempIntNum < 0) {
                        showToastShort("请先保存int数据再获取");
                    } else {
                        tvShowData.setText(String.valueOf(tempIntNum));
                    }
                    break;
                case AppConstants.THREE:
                    int tempIntNum2 = SharedPreferencesUtil.getInt("mIntNum");
                    if (tempIntNum2 < 0) {
                        showToastShort("请先保存int数据再移除");
                    } else {
                        SharedPreferencesUtil.romove("mIntNum");
                        tvShowData.setText("这里将显示获取到的数据");
                        saveCacheClearTrue();
                    }
                    break;
                case AppConstants.FOUR:
                    SharedPreferencesUtil.saveLong("mLongNum", mLongNum);
                    SharedPreferencesUtil.saveBoolean("isCacheClear", false);
                    break;
                case AppConstants.FIVE:
                    long tempLongNum = SharedPreferencesUtil.getLong("mLongNum", -9223372036854775808l);
                    if (tempLongNum < 0) {
                        showToastShort("请先保存long数据再获取");
                    } else {
                        tvShowData.setText(String.valueOf(tempLongNum));
                    }
                    break;
                case AppConstants.SIX:
                    long tempLongNum2 = SharedPreferencesUtil.getLong("mLongNum", -9223372036854775808l);
                    if (tempLongNum2 < 0) {
                        showToastShort("请先保存long数据再移除");
                    } else {
                        SharedPreferencesUtil.romove("mLongNum");
                        tvShowData.setText("这里将显示获取到的数据");
                        saveCacheClearTrue();
                    }
                    break;
                case AppConstants.SEVEN:
                    SharedPreferencesUtil.saveString("mStringText", mStringText);
                    SharedPreferencesUtil.saveBoolean("isCacheClear", false);
                    break;
                case AppConstants.EIGHT:
                    String tempStringText = SharedPreferencesUtil.getString("mStringText");
                    if (TextUtils.isEmpty(tempStringText)) {
                        showToastShort("请先保存string数据再获取");
                    } else {
                        tvShowData.setText(tempStringText);
                    }
                    break;
                case AppConstants.NINE:
                    String tempStringText2 = SharedPreferencesUtil.getString("mStringText");
                    if (TextUtils.isEmpty(tempStringText2)) {
                        showToastShort("请先保存string数据再移除");
                    } else {
                        SharedPreferencesUtil.romove("mStringText");
                        tvShowData.setText("这里将显示获取到的数据");
                        saveCacheClearTrue();
                    }
                    break;
                case AppConstants.TEN:
                    boolean isCacheClear = isCacheClear();
                    logInfo("isCacheClear = " + isCacheClear);
                    if (isCacheClear) {
                        showToastShort("没有可清除的缓存数据了");
                        return;
                    }
                    SharedPreferencesUtil.clear();
                    SharedPreferencesUtil.saveBoolean("isCacheClear", true);
                    tvShowData.setText("这里将显示获取到的数据");
                    break;
            }
        });
    }

    private void saveCacheClearTrue(){
        if (!isCacheClear()){
            SharedPreferencesUtil.saveBoolean("isCacheClear", true);
        }
    }

    private boolean isCacheClear() {
        boolean isCacheClear = SharedPreferencesUtil.getBoolean("isCacheClear");
        return isCacheClear;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sharedpreferencesutil;
    }

}
