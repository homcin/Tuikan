package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.Girl;
import com.grace.zhihunews.ui.activity.PhotoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.ViewHolder> {

    private Context mContext;
    private List<Girl> girls;
    private BitmapTrans bitmapTrans;

    public GirlsAdapter(Context context, List<Girl> stories) {
        mContext = context;
        this.girls = stories;
        bitmapTrans = new BitmapTrans();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_girl)
        ImageView ivGirl;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View girlView = LayoutInflater.from(context).inflate(R.layout.item_girl, parent, false);
        final ViewHolder viewHolder = new ViewHolder(girlView);
        girlView.setOnClickListener(v -> {
            PhotoActivity.actionStart(mContext, girls.get(viewHolder.getAdapterPosition()).getUrl());
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GirlsAdapter.ViewHolder holder, int position) {
        Girl girl = girls.get(position);
        Picasso.with(mContext).load(girl.getUrl()).transform(bitmapTrans).config(Bitmap.Config.RGB_565).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(holder.ivGirl);
    }

    @Override
    public int getItemCount() {
        return girls.size();
    }

}
