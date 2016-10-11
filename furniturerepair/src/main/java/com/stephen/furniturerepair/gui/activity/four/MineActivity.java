package com.stephen.furniturerepair.gui.activity.four;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Stephen on 09/16/2016.
 * Emial: 895745843@qq.com
 * <p/>
 * 我的
 */
public class MineActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.linearLayout_mine_header)
    LinearLayout linearLayoutMineHeader;
    @Bind(R.id.linearLayout_loginOut)
    LinearLayout linearLayoutLoginOut;
    @Bind(R.id.iv_mine_avatar)
    ImageView ivMineAvatar;
    @Bind(R.id.tv_mine_name)
    TextView tvMineName;

    @Override
    protected int setView() {
        return R.layout.activity_mine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBar.setTitlBartitle("我的");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);
        init();
    }

    private void init() {
        String userName = SPUtils.getInstance(this).getUserName();
        if (!TextUtils.isEmpty(userName))
        tvMineName.setText(userName);
    }


    /**
     * 退出登录
     *
     * @param id id
     */
    private void sendRequest(String id) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("id", id));
        try {
            getDataFromServer(list, URL.URL_LOGINOUT, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (!TextUtils.isEmpty(paramObject)) {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(MineActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code == 100) {
                                SPUtils.getInstance(MineActivity.this).setLoginState(false);
                                startActivity(new Intent(MineActivity.this, MainActivity.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        dealResult(paramObject);
                    }
                }

                @Override
                public void responseError(String string) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void clickTitleBarLeft() {
        closeActivity();
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }

    private void closeActivity() {
//        setResult(Activity.RESULT_OK);
        finish();
    }

    @OnClick({R.id.linearLayout_mine_header, R.id.linearLayout_loginOut})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_mine_header:
                startActivity(new Intent(MineActivity.this, MineInfoActivity.class));
                break;
//            退出登录
            case R.id.linearLayout_loginOut:
                sendRequest(SPUtils.getInstance(MineActivity.this).getUserId());
                break;
        }
    }
}
