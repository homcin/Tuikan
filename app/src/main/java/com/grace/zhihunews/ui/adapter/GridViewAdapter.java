package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/16.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Category> categories;

    private int mIndex;
    private int mPageSize;

    public GridViewAdapter(Context context, List<Category> categories, int index) {
        this.categories = categories;
        mContext = context.getApplicationContext();
        mIndex = index;
        mPageSize = 8;
    }

    @Override
    public int getCount() {
        return categories.size() >= (mIndex + 1) * mPageSize ? mPageSize : (categories.size() - mIndex * mPageSize);
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position + mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        int pos = position + mIndex * mPageSize;
        viewHolder.tvCategory.setText(categories.get(pos).getName());
        Picasso.with(mContext).load(categories.get(pos).getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.iv_picture)
        ImageView ivPicture;
        @BindView(R.id.tv_category)
        TextView tvCategory;

        public ViewHolder(View itemView){
            ButterKnife.bind(this, itemView);
        }
    }
}
