package com.chinadragon.commonutilslibrary.simple.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.adpter.SimpleAdapter;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.ui.Image.ImageUtilsActivity;
import com.chinadragon.commonutilslibrary.simple.ui.animation.AnimationUtilsActivity;
import com.chinadragon.commonutilslibrary.simple.ui.encrypt.EncryptActivity;
import com.chinadragon.commonutilslibrary.simple.ui.time.TimeCountActivity;
import com.chinadragon.commonutilslibrary.simple.ui.widget.WidgetActivity;
import com.chinadragon.commonutilslibrary.simple.utils.CustomDividerItemDecoration;
import com.chinadragon.commonutilslibrary.time.WeakHandlerUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements SimpleAdapter.ItemOnClick {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindArray(R.array.activity_name)
    String[] mActivityName;


    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {

                    } else {
                        showToastLong("由于权限被拒绝,两秒钟过后将退出程序");
                        exit();
                    }
                });


    }

    private void exit() {
        new WeakHandlerUtil().postDelayed(() -> {
            this.finish();
        }, 2000);
    }

    @Override
    public void initData() {
        super.initData();
        List<String> stringList = new ArrayList<>();
        int len = mActivityName.length;
        for (int i = 0; i < len; i++) {
            stringList.add(mActivityName[i]);
        }
        rvMain.setItemAnimator(new DefaultItemAnimator());
//        rvMain.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));// 默认的
        rvMain.addItemDecoration(new CustomDividerItemDecoration(20));
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new SimpleAdapter(stringList, this, this));
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void itemOnClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, AnimationUtilsActivity.class);
                break;
            case 1:
                intent = new Intent(this, EncryptActivity.class);
                break;
            case 2:
                intent = new Intent(this, ImageUtilsActivity.class);
                break;
            case 3:
                intent = new Intent(this, TimeCountActivity.class);
                break;
            case 4:
                intent = new Intent(this, WidgetActivity.class);
                break;
            case 5:
                intent = new Intent(this, CompoundDrawablesUtilsActivity.class);
                break;
            case 6:
                intent = new Intent(this, DialogUtilsActivity.class);
                break;
            case 7:
                intent = new Intent(this, FileUtilsActivity.class);
                break;
            case 8:
                intent = new Intent(this, RegexUtilActivity.class);
                break;
            case 9:
                intent = new Intent(this, SharedPreferencesUtilActivity.class);
                break;
            case 10:
                intent = new Intent(this, SpannableStringBuilderUtilActivity.class);
                break;
        }
        if (intent == null) {
            return;
        }
        startActivity(intent);
    }
}
