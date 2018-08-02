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
    }

    @Override
    public void initData() {
        super.initData();
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
