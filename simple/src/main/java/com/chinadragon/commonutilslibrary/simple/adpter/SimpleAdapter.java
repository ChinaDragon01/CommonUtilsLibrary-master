package com.chinadragon.commonutilslibrary.simple.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.simple.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/2/22 16:05
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private List<String> mStringList;
    private Context mContext;
    private ItemOnClick mItemOnClick;

    public SimpleAdapter(List<String> stringList, Context context, ItemOnClick itemOnClick) {
        mStringList = stringList;
        mContext = context;
        mItemOnClick = itemOnClick;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.item_tv.setText(mStringList.get(position));
        holder.item_tv.setOnClickListener(v -> {
            mItemOnClick.itemOnClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        TextView item_tv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemOnClick{
        void itemOnClick(int position);
    }
}
