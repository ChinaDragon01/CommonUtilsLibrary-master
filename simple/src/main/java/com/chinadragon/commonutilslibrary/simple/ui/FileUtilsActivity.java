package com.chinadragon.commonutilslibrary.simple.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chinadragon.commonutilslibrary.FileUtils;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.adpter.FileUtislAapter;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.utils.CustomDividerItemDecoration;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/29 8:10
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class FileUtilsActivity extends BaseAppCompatActivity {
    @BindView(R.id.rcv_fileutils)
    RecyclerView rcvFileutils;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        FileUtils.createFile(0);
        FileUtils.createFile(1);
        FileUtils.createFile(2);
        // 下面是对 文件重复存在演示，具体的需要有真实的文件存在
       /* File file1 = FileUtils.createFile("测试文件是否重复", 0, true);
        File file2 =  FileUtils.createFile("测试文件是否重复",0,true);
        File file3 =  FileUtils.createFile("测试文件是否重复",0,true);
        logInfo("file1.getName()："+file1.getName());
        logInfo("file2.getName()"+file2.getName());
        logInfo("file3.getName()"+file3.getName());*/
    }

    @Override
    public void initData() {
        super.initData();
        logInfo(" FileUtils.DIRECTORY_NAME = " + FileUtils.DIRECTORY_NAME);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + FileUtils.DIRECTORY_NAME;
        String[] fileTypes = new String[1];
        List<File> fileList = FileUtils.getFileListByDirPath(path, fileTypes);
        FileUtislAapter fileUtislAapter = new FileUtislAapter(FileUtilsActivity.this);
        fileUtislAapter.addAll(fileList);
        rcvFileutils.setHasFixedSize(true);
        rcvFileutils.addItemDecoration(new CustomDividerItemDecoration(10));
        rcvFileutils.setLayoutManager(new LinearLayoutManager(this));
        rcvFileutils.setAdapter(fileUtislAapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fileutils;
    }

}
