package com.grace.zhihunews.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.GirlsPresenterCompl;
import com.grace.zhihunews.PresenterCompl.NewsDetailPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.GirlsContact;
import com.grace.zhihunews.network.entity.Girl;
import com.grace.zhihunews.ui.adapter.GirlsAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.listener.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsFragment extends BaseFragment implements GirlsContact.IGirlsView {

    @BindView(R.id.srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_girls)
    RecyclerView rvGirls;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private GirlsContact.IGirlsPresenter mGirlsPresenter;
    private Unbinder unbinder;
    private List<Girl> girls;
    private GirlsAdapter girlsAdapter;
    private int page;
    private List<Integer> pages = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_girls;
    }

    @Override
    protected void initVariables() {
        girls = new ArrayList<>();
        girlsAdapter = new GirlsAdapter(getActivity(), girls);
        mGirlsPresenter = new GirlsPresenterCompl(this);
        page = 1;
        pages.add(page);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        rvGirls.setLayoutManager(layoutManager);
        rvGirls.setAdapter(girlsAdapter);
        rvGirls.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                Integer pageCount = pages.get(pages.size() - 1);
                pageCount += 1;
                pages.add(pageCount);
                mGirlsPresenter.loadBenefit(pageCount, true, false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            girls.clear();
            girlsAdapter.notifyDataSetChanged();
            pages.clear();
            page = 1;
            pages.add(page);
            mGirlsPresenter.loadBenefit(page, true, true);

            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 1200);
        });
        fab.setOnClickListener(v -> rvGirls.smoothScrollToPosition(0));
    }

    @Override
    protected void loadData() {
        mGirlsPresenter.loadBenefit(page, false, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //GirlsContact.IGirlsView接口方法
    @Override
    public void showGirls(List<Girl> girls) {
        this.girls.addAll(girls);
        int curSize = girlsAdapter.getItemCount();
        girlsAdapter.notifyItemRangeChanged(curSize, girls.size() - 1);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
