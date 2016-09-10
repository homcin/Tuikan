package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.network.entity.TopStory;
import com.squareup.picasso.Picasso;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class TopStoriesAdapter extends InfinitePagerAdapter {

    private Context mContext;
    private List<TopStory> mTopStories;

    public TopStoriesAdapter(Context context, List<TopStory> topStories) {
        mContext = context;
        mTopStories = topStories;
    }

    @Override
    public int getItemCount() {
        return mTopStories == null ? 0 : mTopStories.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup containner) {
        //Log.e("TopStoryPosition", String.valueOf(position));
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_top_story, containner, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        TopStory topStory = mTopStories.get(position);
        Picasso.with(mContext).load(topStory.getImage()).into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(topStory.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GotoNewsDetailEvent(topStory.getId()));
            }
        });
        return view;
    }

    class ViewHolder {

        @BindView(R.id.iv_top_story)
        ImageView ivImage;
        @BindView(R.id.tv_top_story_title)
        TextView tvTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
