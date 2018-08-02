package com.chinadragon.commonutilslibrary.simple.ui.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chinadragon.commonutilslibrary.ScreenUtil;
import com.chinadragon.commonutilslibrary.ToastUtil;
import com.chinadragon.commonutilslibrary.image.ImageUtils;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.adpter.ImageAdapter;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/22 21:28
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class ImageUtilsActivity extends BaseAppCompatActivity {

    @BindView(R.id.rc_img_util)
    RecyclerView rcImgUtil;
    private int resourceId = R.mipmap.img_01;
    private int what = 1;
    private ImageAdapter mImageAdapter;
    private GridLayoutManager mGridLayoutManager;
    private int mLastVisibleItemPosition;
    private boolean loading;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mImageAdapter = new ImageAdapter(this);
        rcImgUtil.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        rcImgUtil.setLayoutManager(mGridLayoutManager);
        rcImgUtil.addItemDecoration(new GridSpacingItemDecoration(3, 10, true));
        rcImgUtil.setAdapter(mImageAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        getBitmapList();

    }

    private List<Bitmap> addBitmap(Bitmap bitmap) {
        List<Bitmap> bitmaps = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            bitmaps.add(bitmap);
        }
        return bitmaps;
    }

    private void getBitmapList() {
        new AsyncTask<Void, Void, List<Bitmap>>() {

            @Override
            protected void onPreExecute() {
                showToastLong("正在加载图片...");
                loading = true;
            }

            @Override
            protected List<Bitmap> doInBackground(Void... params) {
                Bitmap bitmap = compressImageFromBitmap(resourceId);
                List<Bitmap> bitmaps = addBitmap(bitmap);
                return bitmaps;
            }

            @Override
            protected void onPostExecute(List<Bitmap> bitmaps) {
                super.onPostExecute(bitmaps);
                mImageAdapter.addAll(bitmaps);
                loading = false;
                ToastUtil.cancelToast();
            }
        }.execute();
    }

    private Bitmap compressImageFromBitmap(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), id);
        int screenWidth = ScreenUtil.getScreenWidth() / 3;
        Bitmap newBitmp = ImageUtils.compressImageFromBitmap(bitmap, screenWidth, screenWidth);
        return newBitmp;
    }

    @Override
    public void initEvent() {
        super.initEvent();

        rcImgUtil.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                logInfo("mLastVisibleItemPosition = " + mLastVisibleItemPosition + "，getItemCount = " + mImageAdapter.getItemCount() + "，newState = " + newState + "，what = " + what);
                if (mLastVisibleItemPosition + 1 == mImageAdapter.getItemCount() && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    if (loading){
                        showToastLong("正在加载图片...");
                        return;
                    }
                    if (what <= 2 ) {
                        what++;
                        resourceId = what == 2 ? R.mipmap.img_02 : R.mipmap.img_03;
                        logInfo("onScrollStateChanged what = " + what + "，resourceId = " + resourceId);
                        getBitmapList();
                    } else {
                        showToastShort("已经是全部图片了");
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = mGridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imageutil;
    }

}
