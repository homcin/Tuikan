package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/17.
 */
public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mCategoryNameList;
    private List<List<Item>> mItemsList;

    public PreviewAdapter(Context mContext, List<String> mCategoryName, List<List<Item>> mItemsList) {
        this.mContext = mContext;
        this.mCategoryNameList = mCategoryName;
        this.mItemsList = mItemsList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_category_name)
        TextView tvCategoryName;
        @BindView(R.id.gv_preview)
        GridView gvPreview;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View preview = LayoutInflater.from(mContext).inflate(R.layout.item_preview, parent, false);
        final ViewHolder viewHolder = new ViewHolder(preview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PreviewAdapter.ViewHolder holder, int position) {
        String categoryName  = mCategoryNameList.get(position);
        List<Item> itemList = mItemsList.get(position);
        holder.tvCategoryName.setText(categoryName);
        PreviewGridAdapter adapter = new PreviewGridAdapter(mContext, itemList);
        holder.gvPreview.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mCategoryNameList.size();
    }

}
