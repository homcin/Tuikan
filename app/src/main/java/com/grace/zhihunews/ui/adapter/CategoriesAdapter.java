package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.video.Category;
import com.squareup.picasso.Picasso;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/10.
 */
public class CategoriesAdapter extends InfinitePagerAdapter {


    private Context mContext;
    private List<Category> mCategories;
    private int pageNum;
    private int categoriesSize;

    public CategoriesAdapter(Context context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
        if (mCategories.size() % 8 == 0) {
            pageNum =  mCategories.size() / 8;
        } else {
            pageNum =  mCategories.size() / 8 + 1;
        }
        categoriesSize = categories.size();

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup containner) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_category, containner, false);
            viewHolder = new ViewHolder(view);
            Category category0 = mCategories.get(position * 8);
            viewHolder.tvCategory0.setText(category0.getName());
            Picasso.with(mContext).load(category0.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture0);
            if (position * 8 + 1 < categoriesSize) {
                Category category1 = mCategories.get(position * 8 + 1);
                viewHolder.tvCategory1.setText(category1.getName());
                Picasso.with(mContext).load(category1.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture1);
            } else {
                viewHolder.ivPicture1.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory1.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 2 < categoriesSize) {
                Category category2 = mCategories.get(position * 8 + 2);
                viewHolder.tvCategory2.setText(category2.getName());
                Picasso.with(mContext).load(category2.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture2);
            } else {
                viewHolder.ivPicture2.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory2.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 3 < categoriesSize) {
                Category category3 = mCategories.get(position * 8 + 3);
                viewHolder.tvCategory3.setText(category3.getName());
                Picasso.with(mContext).load(category3.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture3);
            } else {
                viewHolder.ivPicture3.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory3.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 4 < categoriesSize) {
                Category category4 = mCategories.get(position * 8 + 4);
                viewHolder.tvCategory4.setText(category4.getName());
                Picasso.with(mContext).load(category4.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture4);
            } else {
                viewHolder.ivPicture4.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory4.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 5 < categoriesSize) {
                Category category5 = mCategories.get(position * 8 + 5);
                viewHolder.tvCategory5.setText(category5.getName());
                Picasso.with(mContext).load(category5.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture5);
            } else {
                viewHolder.ivPicture5.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory5.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 6 < categoriesSize) {
                Category category6 = mCategories.get(position * 8 + 6);
                viewHolder.tvCategory6.setText(category6.getName());
                Picasso.with(mContext).load(category6.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture6);
            } else {
                viewHolder.ivPicture6.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory6.setVisibility(View.INVISIBLE);
            }
            if (position * 8 + 7 < categoriesSize) {
                Category category7 = mCategories.get(position * 8 + 7);
                viewHolder.tvCategory7.setText(category7.getName());
                Picasso.with(mContext).load(category7.getBgPicture()).placeholder(mContext.getDrawable(R.drawable.bg_holder_brown)).into(viewHolder.ivPicture7);
            } else {
                viewHolder.ivPicture7.setVisibility(View.INVISIBLE);
                viewHolder.tvCategory7.setVisibility(View.INVISIBLE);
            }
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventBus.getDefault().post(new GotoNewsDetailEvent(topStory.getId()));
            }
        });
        return view;
    }

    class ViewHolder {

        @BindView(R.id.iv_picture0)
        ImageView ivPicture0;
        @BindView(R.id.iv_picture1)
        ImageView ivPicture1;
        @BindView(R.id.iv_picture2)
        ImageView ivPicture2;
        @BindView(R.id.iv_picture3)
        ImageView ivPicture3;
        @BindView(R.id.iv_picture4)
        ImageView ivPicture4;
        @BindView(R.id.iv_picture5)
        ImageView ivPicture5;
        @BindView(R.id.iv_picture6)
        ImageView ivPicture6;
        @BindView(R.id.iv_picture7)
        ImageView ivPicture7;

        @BindView(R.id.iv_category0)
        TextView tvCategory0;
        @BindView(R.id.iv_category1)
        TextView tvCategory1;
        @BindView(R.id.iv_category2)
        TextView tvCategory2;
        @BindView(R.id.iv_category3)
        TextView tvCategory3;
        @BindView(R.id.iv_category4)
        TextView tvCategory4;
        @BindView(R.id.iv_category5)
        TextView tvCategory5;
        @BindView(R.id.iv_category6)
        TextView tvCategory6;
        @BindView(R.id.iv_category7)
        TextView tvCategory7;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
