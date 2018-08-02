package com.chinadragon.commonutilslibrary.simple.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.FileUtils;
import com.chinadragon.commonutilslibrary.ToastUtil;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.time.onNoMultiClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/29 9:26
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class FileUtislAapter extends RecyclerView.Adapter<FileUtislAapter.FileUtilsViewHolder> {
    private Context mContext;
    private List<File> mFileList;

    public FileUtislAapter(Context context) {
        mContext = context;
        mFileList = new ArrayList<>();
    }

    @Override
    public FileUtilsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_file_manage, parent, false);
        return new FileUtilsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileUtilsViewHolder holder, int position) {
        File file = mFileList.get(position);
        if (!file.isDirectory() || null == file) {
            return;
        }

        String name = file.getName();
        File[] files = file.listFiles();
        String folderdetail = files == null ? "0项" : files.length + "项";
        holder.tvItemFolderdetail.setText(folderdetail);
        switch (name) {
            case "imges":
                holder.tvItemFolderename.setText(FileUtils.FOLDER_ZH_NAME[0]);
                break;
            case "videos":
                holder.tvItemFolderename.setText(FileUtils.FOLDER_ZH_NAME[1]);
                break;
            case "pdf":
                holder.tvItemFolderename.setText(FileUtils.FOLDER_ZH_NAME[2]);
                break;
        }

        holder.rlItemFileManageRoot.setOnClickListener(new onNoMultiClickListener() {
            @Override
            public void onNoMultiClick(View view) {
                ToastUtil.showShort("点击了 " + holder.tvItemFolderename.getText() + "文件夹");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    class FileUtilsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_folderename)
        TextView tvItemFolderename;
        @BindView(R.id.tv_item_folderdetail)
        TextView tvItemFolderdetail;
        @BindView(R.id.item_file_manage_root)
        RelativeLayout rlItemFileManageRoot;

        public FileUtilsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addAll(List<File> fileList) {
        mFileList.addAll(fileList);
        notifyDataSetChanged();
    }

    public void remote(int positon) {
        mFileList.remove(positon);
        notifyDataSetChanged();
    }
}
