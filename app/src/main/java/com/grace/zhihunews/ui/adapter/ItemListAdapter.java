package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Item;
import com.grace.zhihunews.ui.activity.ShowVideoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_TEXT = 1;

    private Context mContext;
    private List<Item> mItemList;

    public ItemListAdapter(Context context, List<Item> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    public static class VideoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.iv_cover)
        ImageView ivCover;

        public VideoHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TextHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_date)
        TextView tvDate;

        public TextHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



    @Override
    public int getItemViewType(int position) {
        Item item = mItemList.get(position);
        if ("video".equals(item.getType())) {
            return TYPE_VIDEO;
        } else {
            return TYPE_TEXT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VIDEO :
                View videoView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
                final VideoHolder videoHolder = new VideoHolder(videoView);
                videoView.setOnClickListener(v -> {
                    //Intent intent = PhotoActivity.newIntent(mContext, girls.get(viewHolder.getAdapterPosition()).getUrl());
                    //mContext.startActivity(intent);
                    Intent intent=new Intent(mContext, ShowVideoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("video", mItemList.get(videoHolder.getAdapterPosition()).getData().getPlayUrl());
                    bundle.putString("title", mItemList.get(videoHolder.getAdapterPosition()).getData().getTitle());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                });
                return videoHolder;
            case TYPE_TEXT :
                View textView = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
                final TextHolder textHolder = new TextHolder(textView);
                textView.setOnClickListener(v -> {
                    //Intent intent = PhotoActivity.newIntent(mContext, girls.get(viewHolder.getAdapterPosition()).getUrl());
                    //mContext.startActivity(intent);
                });
                return textHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_VIDEO :
                String feed = item.getData().getCover().getFeed();
                Uri coverUrl = Uri.parse(feed);
                String title = item.getData().getTitle();
                String category = item.getData().getCategory();
                category = "#" + category + "  /  ";
                int duration = item.getData().getDuration();
                int secondCount = duration % 60;
                String second;
                if (secondCount <= 9) {
                    second = "0" + secondCount;
                } else {
                    second = secondCount + "";
                }
                int minuteCount = duration / 60;
                String minute;
                if (minuteCount < 10) {
                    minute = "0" + minuteCount;
                } else {
                    minute = minuteCount + "";
                }
                String durationTime = minute + "' " + second + '"';
                Picasso.with(mContext).load(coverUrl).resize(355, 200).centerCrop().into(((VideoHolder)holder).ivCover);
                ((VideoHolder)holder).tvTitle.setText(title);
                ((VideoHolder)holder).tvDuration.setText(category + durationTime);
                break;
            case TYPE_TEXT :
                String text = item.getData().getText();
                if (TextUtils.isEmpty(text)) {
                    ((TextHolder)holder).tvDate.setTextSize(20);
                    ((TextHolder)holder).tvDate.setText("-Weekend  special-");
                } else {
                    ((TextHolder)holder).tvDate.setText(text);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
