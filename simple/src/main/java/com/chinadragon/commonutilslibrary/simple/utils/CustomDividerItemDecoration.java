package com.chinadragon.commonutilslibrary.simple.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/25 15:45
 * Name:
 * Overview: 设置item的padding
 * Usage:
 * **********************************************************************
 */

public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int divider;

    public CustomDividerItemDecoration(int divider) {
        this.divider = divider;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divider;
    }
}
