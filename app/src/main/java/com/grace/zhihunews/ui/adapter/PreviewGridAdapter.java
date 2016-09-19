package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Category;
import com.grace.zhihunews.network.entity.video.Feature;
import com.grace.zhihunews.network.entity.video.Item;
import com.grace.zhihunews.ui.activity.ShowVideoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/17.
 */
public class PreviewGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> mItemList;

    public PreviewGridAdapter(Context mContext, List<Item> mItemList) {
        this.mItemList = mItemList;
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_preview_grid, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (mItemList.get(position).getType().equals("banner1")) {
            viewHolder.tvTitle.setText("广告");
            String image = mItemList.get(position).getData().getImage();
            Uri coverUrl = Uri.parse(image);
            Picasso.with(mContext).load(coverUrl).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivCover);

        } else {
            String title = mItemList.get(position).getData().getTitle();
            viewHolder.tvTitle.setText(title);
            String feed = mItemList.get(position).getData().getCover().getFeed();
            Uri coverUrl = Uri.parse(feed);
            Picasso.with(mContext).load(coverUrl).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivCover);
            String playUrl = mItemList.get(position).getData().getPlayUrl();
            view.setOnClickListener(v -> ShowVideoActivity.actionStart(mContext, playUrl, title));
        }
        return view;
    }

    class ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView){
            ButterKnife.bind(this, itemView);
        }
    }
}
