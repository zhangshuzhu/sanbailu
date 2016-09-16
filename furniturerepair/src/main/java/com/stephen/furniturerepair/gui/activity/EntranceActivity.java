package com.stephen.furniturerepair.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Stephen on 09/15/2016.
 * Emial: 895745843@qq.com
 */
public class EntranceActivity extends BaseActivity {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;

    @Override
    protected int setView() {
        return R.layout.activity_entrance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {

    }


    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                // TODO: 09/15/2016 发单界面

                break;
            case R.id.button2:
                // TODO: 09/15/2016 接单 进入主界面
                Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                intent.putExtras(new Bundle(1));
                startActivity(intent);
                break;
        }
    }
}
