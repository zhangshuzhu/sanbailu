package com.stephen.furniturerepair.gui.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.gui.activity.EntranceActivity;

import butterknife.Bind;

/**
 * Created by Stephen on 09/16/2016.
 * Emial: 895745843@qq.com
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView image;

    @Override
    protected int setView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlphaAnimation alpha = new AlphaAnimation(0.5f, 1);
        alpha.setDuration(2000);
        image.startAnimation(alpha);
        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, EntranceActivity.class));
                finish();
            }
        });
    }
}
