package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.network.entity.Story;
import com.grace.zhihunews.util.DateUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private Context mContext;
    private List<Story> stories;

    public StoriesAdapter(Context context, List<Story> stories) {
        mContext = context;
        this.stories = stories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.story_img)
        ImageView ivStoryImage;
        @BindView(R.id.story_title)
        TextView tvStoryTitle;
        @BindView(R.id.date)
        TextView tvDate;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        final ViewHolder viewHolder = new ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GotoNewsDetailEvent(stories.get(viewHolder.getLayoutPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoriesAdapter.ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.tvStoryTitle.setText(story.getTitle());
        holder.tvDate.setText(DateUtil.getDateDescription(story.getDate()));
        Picasso.with(mContext).load(story.getImages().get(0)).into(holder.ivStoryImage);
    }

    @Override
    public int getItemCount() {
            return stories.size();
    }

}
