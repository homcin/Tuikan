package com.grace.zhihunews.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grace.zhihunews.PresenterCompl.GirlsPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.adapter.GirlsAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.listener.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/3.
 */
public class AboutFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }
}
