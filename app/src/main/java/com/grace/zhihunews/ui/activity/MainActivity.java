package com.grace.zhihunews.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.grace.zhihunews.ui.fragment.AboutFragment;
import com.grace.zhihunews.ui.fragment.FeatureListFragment;
import com.grace.zhihunews.ui.fragment.GirlsFragment;
import com.grace.zhihunews.ui.fragment.NewsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private NewsListFragment mNewsListFragment;
    private GirlsFragment mGirlsFragment;
    private AboutFragment mAboutFragment;
    private FeatureListFragment mFeatureListFragment;
    private FragmentManager fm;

    @Override
    protected void initVariables() {
        fm = getFragmentManager();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.ic_news_24dp, "新闻").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_photo_24dp, "美图").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_video_24dp, "视频").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_about_me, "关于").setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        toolbarTitle.setText(R.string.MainActivity_title_news);
                        showFragment(0);
                        break;
                    case 1:
                        toolbarTitle.setText(R.string.MainActivity_title_photo);
                        showFragment(1);
                        break;
                    case 2:
                        toolbarTitle.setText(R.string.MainActivity_title_video);
                        showFragment(2);
                        break;
                    case 3:
                        toolbarTitle.setText(R.string.MainActivity_title_about);
                        showFragment(3);
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        toolbarTitle.setText(R.string.MainActivity_title_news);
        showFragment(0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (position) {
            case 0 : if (mNewsListFragment != null) {
                        ft.show(mNewsListFragment);
                    } else {
                        mNewsListFragment = new NewsListFragment();
                        ft.add(R.id.frame_layout, mNewsListFragment);
                    }
                break;
            case 1 : if (mGirlsFragment != null) {
                        ft.show(mGirlsFragment);
                    } else {
                        mGirlsFragment = new GirlsFragment();
                        ft.add(R.id.frame_layout, mGirlsFragment);
                    }
                break;
            case 2 : if (mFeatureListFragment != null) {
                        ft.show(mFeatureListFragment);
                    } else {
                        mFeatureListFragment = new FeatureListFragment();
                        ft.add(R.id.frame_layout, mFeatureListFragment);
                    }
                break;
            case 3 : if (mAboutFragment != null) {
                        ft.show(mAboutFragment);
                    } else {
                        mAboutFragment = new AboutFragment();
                        ft.add(R.id.frame_layout, mAboutFragment);
                    }
                break;
        }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (mNewsListFragment != null) {
            ft.hide(mNewsListFragment);
        }
        if (mGirlsFragment != null) {
            ft.hide(mGirlsFragment);
        }
        if (mFeatureListFragment != null) {
            ft.hide(mFeatureListFragment);
        }
        if (mAboutFragment != null) {
            ft.hide(mAboutFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
    }
}
