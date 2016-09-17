package com.stephen.furniturerepair.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.gui.activity.login.LoginActivity;

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

    private static final int CODE_ISSUANCE = 100;
    private static final int CODE_RECEIVE = 101;

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                // TODO: 09/15/2016 发单界面
                if (SPUtils.getInstance(this).getLoginState()) {
                    startActivity(new Intent(EntranceActivity.this, IssuanceActivity.class));
                } else {
                    startActivityForResult(new Intent(EntranceActivity.this, LoginActivity.class), CODE_ISSUANCE);
                }
                break;
            case R.id.button2:
                // TODO: 09/15/2016 接单 进入主界面
                Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                intent.putExtras(new Bundle(1));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case CODE_ISSUANCE:
                    Intent intent = new Intent(EntranceActivity.this, IssuanceActivity.class);
                    intent.putExtras(new Bundle(1));
                    startActivity(intent);
                    break;
                case CODE_RECEIVE:
                    Intent intent2 = new Intent(EntranceActivity.this, MainActivity.class);
                    intent2.putExtras(new Bundle(1));
                    startActivity(intent2);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
