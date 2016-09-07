package com.grace.zhihunews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.grace.zhihunews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity {

    public static final String KEY_IMG_URL = "image_url";

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private String mImageUrl;
    private boolean mIsHidden = false;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(PhotoActivity.KEY_IMG_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mImageUrl = getIntent().getStringExtra(KEY_IMG_URL);
        Picasso.with(this).load(mImageUrl).into(ivPhoto);
        ivPhoto.setOnClickListener(v -> hideOrShowToolbar());
        ivPhoto.setOnLongClickListener(v -> {
            new AlertDialog.Builder(PhotoActivity.this)
                    .setMessage(getString(R.string.PhotoActivity_dialog_msg))
                    .setNegativeButton(android.R.string.cancel,
                            (dialog, which) -> dialog.dismiss())
                    .setPositiveButton(android.R.string.ok,
                            (dialog, which) -> {
                                saveImageToGallery();
                                dialog.dismiss();
                            })
                    .show();
            return true;
        });
        hideOrShowToolbar();

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBarBackgroundColor(R.color.md_black_1000);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_wallpaper, "设为壁纸").setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(-1)
                .initialise();


        Toast toast = Toast.makeText(this, "设置壁纸成功", Toast.LENGTH_SHORT);

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        try {
                            setWallpaper(((BitmapDrawable) ivPhoto.getDrawable()).getBitmap());
                            toast.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

    }

    private void saveImageToGallery() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                return true;
            case R.id.action_save:
                saveImageToGallery();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mBottomNavigationBar.animate()
                .translationY(mIsHidden ? 0 : mBottomNavigationBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
