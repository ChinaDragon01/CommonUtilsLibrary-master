package com.chinadragon.commonutilslibrary;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/9/7 13:26
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class SDCardUtil {
    /**
     * 检查SD卡是否存在
     */
    public static boolean checkSdCard() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机SD卡总空间
     */
    public static String getSDcardTotalSize() {

        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            return totalSize(path.getPath());
        } else {
            return null;
        }
    }


    /**
     * 获取SDCard可用空间
     */
    public static String getSDcardAvailableSize() {

        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            return availableSize(path.getPath());
        } else {
            return null;
        }
    }


    /**
     * 获取手机内部存储总空间
     */
    @SuppressLint("NewApi")
    public static String getPhoneTotalSize() {

        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            return totalSize(path.getPath());
        } else {
            return getSDcardTotalSize();
        }
    }

    /**
     * 获取手机内存存储可用空间
     */
    @SuppressLint("NewApi")
    public static String getPhoneAvailableSize() {

        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            return availableSize(path.getPath());
        } else
            return getSDcardAvailableSize();
    }

    /**
     * 获取内部存储 总 空间
     * @param
     * @return
     */
    private static String totalSize(String filePath){
        StatFs mStatFs = new StatFs(filePath);
        long blockSizeLong = 0;
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = mStatFs.getBlockSizeLong();
            blockCountLong = mStatFs.getBlockCountLong();
        }else {
            blockSizeLong = mStatFs.getBlockSize();
            blockCountLong = mStatFs.getBlockCount();
        }
        return Formatter.formatFileSize(CommonUtils.sContext,blockSizeLong * blockCountLong);
    }


    /**
     * 获取内部存储 可用 空间
     * @param
     * @return
     */
    private static String availableSize(String filePath){
        StatFs mStatFs = new StatFs(filePath);
        long blockSizeLong = 0;
        long availableBlocksLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = mStatFs.getBlockSizeLong();
            availableBlocksLong = mStatFs.getAvailableBlocksLong();
        }else {
            blockSizeLong = mStatFs.getBlockSize();
            availableBlocksLong = mStatFs.getAvailableBlocks();
        }
        return Formatter.formatFileSize(CommonUtils.sContext,blockSizeLong * availableBlocksLong);
    }
}
