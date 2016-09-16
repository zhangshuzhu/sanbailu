package com.stephen.furniturerepair.gui.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.gui.activity.EntranceActivity;
import com.stephen.furniturerepair.gui.widget.MyTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {
    @Bind(R.id.viewPager_guide)
    ViewPager viewPagerGuide;
    @Bind(R.id.textView_guide_join)
    MyTextView textViewGuideJoin;

    private MyViewPagerAdapter myViewPagerAdapter;
    private List<ImageView> list_Views;
    private int[] imgIds = {R.mipmap.guide01, R.mipmap.guide02, R.mipmap.guide03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isFirst = prefs.getBoolean("isFirst", true);
        if (!isFirst) {
//			startActivity(new Intent(GuideActivity.this, SplashActivity.class));
            Intent intent = new Intent(GuideActivity.this, EntranceActivity.class);
            intent.putExtras(new Bundle(2));
            startActivity(intent);
            GuideActivity.this.finish();
        }

        viewPagerGuide = (ViewPager) this.findViewById(R.id.viewPager_guide);
        textViewGuideJoin = (MyTextView) findViewById(R.id.textView_guide_join);
        initViewPager();
    }

    @Override
    protected int setView() {
        return R.layout.activity_guide;
    }

    /**
     * 初始化ViewPager
     */
    public void initViewPager() {
        list_Views = new ArrayList<ImageView>();
        for (int imgId : imgIds) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgId);
            imageView.setScaleType(ScaleType.FIT_XY);
            list_Views.add(imageView);
        }
        myViewPagerAdapter = new MyViewPagerAdapter(list_Views);
        viewPagerGuide.setAdapter(myViewPagerAdapter);
        viewPagerGuide.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int positon) {

                textViewGuideJoin.setVisibility(View.GONE);
                if (positon == 2) {
                    textViewGuideJoin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @OnClick(R.id.textView_guide_join)
    public void onClick() {
        SharedPreferences prefs = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", false);
        editor.apply();
        Intent intent = new Intent();
//		intent.setClass(this, SplashActivity.class);
        intent.setClass(this, EntranceActivity.class);
        startActivity(intent);
        GuideActivity.this.finish();
    }
}
