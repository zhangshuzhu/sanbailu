package com.stephen.furniturerepair.gui.activity.four;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.SApplication;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.bean.User;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.GsonUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Stephen on 09/16/2016.
 * Emial: 895745843@qq.com
 */
public class MineInfoActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.linearLayout_loginOut)
    LinearLayout linearLayoutLoginOut;
    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected int setView() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBar.setTitlBartitle("个人信息");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);

        ((TextView) linearLayoutLoginOut.getChildAt(0)).setText("修改密码");


    }


    @Override
    protected void onResume() {
        super.onResume();
        String userInfo = SPUtils.getInstance(MineInfoActivity.this).getUserInfo();
        textView.setText("Json: " + userInfo + "\n\n");

        try {

            User user = GsonUtils.fromJson(userInfo, User.class);

            textView.setText("Json: " + userInfo + "\n\n JsonObject" + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出登录
     *
     * @param phoneNumber 手机号
     * @param password    密码
     */
    private void sendRequest(String phoneNumber, String password) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("username", phoneNumber));
        list.add(new BasicNameValuePair("password", password));
        try {
            getDataFromServer(list, URL.URL_LOGIN, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (!TextUtils.isEmpty(paramObject)) {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            if (code == 100) {
                                Toast.makeText(MineInfoActivity.this, "退出成功！", Toast.LENGTH_SHORT).show();
                                SPUtils.getInstance(MineInfoActivity.this).setLoginState(false);
                                startActivity(new Intent(MineInfoActivity.this, MainActivity.class));
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

    @OnClick(R.id.linearLayout_loginOut)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_loginOut:
                startActivity(new Intent(MineInfoActivity.this, ChangePasswordActivity.class));
                break;
        }
    }
}
