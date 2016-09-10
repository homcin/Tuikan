package com.grace.zhihunews.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.PresenterCompl.FeaturePresenterCompl;
import com.grace.zhihunews.PresenterCompl.GirlsPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.FeatureContact;
import com.grace.zhihunews.contract.GirlsContact;
import com.grace.zhihunews.network.entity.Girl;
import com.grace.zhihunews.network.entity.video.Category;
import com.grace.zhihunews.network.entity.video.Feature;
import com.grace.zhihunews.network.entity.video.Item;
import com.grace.zhihunews.ui.adapter.CategoriesAdapter;
import com.grace.zhihunews.ui.adapter.GirlsAdapter;
import com.grace.zhihunews.ui.adapter.ItemListAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.listener.EndlessRecyclerViewScrollListener;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/9/9.
 */
public class FeatureListFragment extends BaseFragment implements FeatureContact.IFeatureView {

    @BindView(R.id.srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_features)
    RecyclerView rvFeatures;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;
    @BindView(R.id.view_pager)
    InfiniteViewPager mViewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;

    private FeatureContact.IFeaturePresenter mFeaturePresenter;
    private Unbinder unbinder;
    private List<Item> mItemList;
    private ItemListAdapter mItemListAdapter;
    private CategoriesAdapter mCategoriesAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_feature_list;
    }

    @Override
    protected void initVariables() {
        mItemList = new ArrayList<>();
        mItemListAdapter = new ItemListAdapter(getActivity(), mItemList);
        mFeaturePresenter = new FeaturePresenterCompl(this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvFeatures.setLayoutManager(layoutManager);
        rvFeatures.setAdapter(mItemListAdapter);
        rvHeader.attachTo(rvFeatures, true);
        rvFeatures.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                /*
                Integer pageCount = pages.get(pages.size() - 1);
                pageCount += 1;
                pages.add(pageCount);
                mGirlsPresenter.loadBenefit(pageCount);
                */
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            /*
            mSwipeRefreshLayout.setRefreshing(true);

            girls.clear();
            girlsAdapter.notifyDataSetChanged();
            pages.clear();
            page = 1;
            pages.add(page);
            mGirlsPresenter.loadBenefit(page);

            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 1200);
            */
        });
        fab.setOnClickListener(v -> rvFeatures.smoothScrollToPosition(0));
    }

    @Override
    protected void loadData() {
        mFeaturePresenter.loadCategories();
        mFeaturePresenter.loadTodayFeature();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //GirlsContact.IGirlsView接口方法
    @Override
    public void showTodayFeature(Feature feature) {
        mItemList.clear();
        Log.d("data", String.valueOf(feature.getIssueList().get(0).getItemList().size()));
        mItemList.addAll(feature.getIssueList().get(0).getItemList());
        int curSize = mItemListAdapter.getItemCount();
        mItemListAdapter.notifyItemRangeChanged(curSize, mItemList.size() - 1);
    }

    @Override
    public void showCategories(List<Category> categories) {
        mCategoriesAdapter = new CategoriesAdapter(getActivity(), categories);
        mViewPager.setAdapter(mCategoriesAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

}
