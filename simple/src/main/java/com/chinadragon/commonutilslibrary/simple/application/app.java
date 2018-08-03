package com.chinadragon.commonutilslibrary.simple.application;

import android.app.Application;

import com.chinadragon.commonutilslibrary.CommonUtils;
import com.chinadragon.commonutilslibrary.simple.constants.AppConstants;


/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/2/22 17:08
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonUtils.init(this, AppConstants.LOG_TAG, AppConstants.LOG_MODEL,null,"FileUtils_DirectoryName");
//        CommonUtils.init(this, AppConstants.LOG_TAG, AppConstants.LOG_MODEL,null,null);
//        CommonUtils.init(this, AppConstants.LOG_TAG, AppConstants.LOG_MODEL,null);
    }
}
