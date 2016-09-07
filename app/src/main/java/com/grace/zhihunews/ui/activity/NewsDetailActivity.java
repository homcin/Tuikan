package com.grace.zhihunews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.NewsDetailPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.NewsDetailContract;
import com.grace.zhihunews.network.entity.NewsDetail;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity implements NewsDetailContract.INewsDetailView {

    public static final String KEY_STORY_ID = "story_id";

    @BindView(R.id.iv_header_img)
    ImageView ivHeaderImg;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_img_source)
    TextView tvImgSource;
    @BindView(R.id.wb_story_content)
    WebView wbNewsContent;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private NewsDetailContract.INewsDetailPresenter mNewsDetailPresenter;
    private NewsDetail mNewsDetail;
    private int id;

    @Override
    protected void initVariables() {
        mNewsDetailPresenter = new NewsDetailPresenterCompl((App)getApplication(), this);
        id = getIntent().getIntExtra(KEY_STORY_ID, 8740001);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        fab.setOnClickListener(v -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    protected void loadData() {
        mNewsDetailPresenter.loadNewsDetail(id);
    }

    //NewsDetailContract.INewsDetailView接口方法
    @Override
    public void showNewsDetail(NewsDetail newsDetail) {
        mNewsDetail = newsDetail;
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" + mNewsDetail.getBody();
        wbNewsContent.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);

        tvHeaderTitle.setText(mNewsDetail.getTitle());
        tvImgSource.setText(mNewsDetail.getImage_source());
        Picasso.with(this).load(mNewsDetail.getImage()).into(ivHeaderImg);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                if (mNewsDetail != null) {
                    shareStory();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareStory() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mNewsDetail.getShare_url());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
