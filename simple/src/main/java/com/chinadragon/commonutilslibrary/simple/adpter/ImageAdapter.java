package com.chinadragon.commonutilslibrary.simple.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chinadragon.commonutilslibrary.simple.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/23 21:44
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Bitmap> mBitmapList;
    private Context mContext;

    public ImageAdapter(Context context) {
        mBitmapList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.itemImg.setImageBitmap(mBitmapList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBitmapList.size();
    }

    public void addAll(List<Bitmap> bitmaps){
        mBitmapList.addAll(bitmaps);
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_img)
        ImageView itemImg;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
