package com.stephen.furniturerepair.gui.activity.four;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;
import com.stephen.furniturerepair.gui.activity.login.LoginActivity;

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
 */
public class ChangePasswordActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.editText1)
    EditText editText1;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.linearLayout_loginOut)
    LinearLayout linearLayoutModify;

    @Override
    protected int setView() {
        return R.layout.activity_mine_changepassword;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBar.setTitlBartitle("修改密码");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);
    }

    @Override
    public void clickTitleBarLeft() {
        finish();
    }

    @OnClick({R.id.editText1, R.id.editText2, R.id.editText3, R.id.linearLayout_loginOut})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editText1:
                break;
            case R.id.editText2:
                break;
            case R.id.editText3:
                break;
            case R.id.linearLayout_loginOut:

                sendRequest(editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
                break;
        }
    }

    /**
     * 修改密码
     *
     */
    private void sendRequest(final String account, final String old_password, final String new_password) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("old_password", old_password));
        list.add(new BasicNameValuePair("new_password", new_password));
        try {
            getDataFromServer(list, URL.URL_UPDATE_PASSWORD, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (!TextUtils.isEmpty(paramObject)) {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            if (code == 100) {
//                                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                                SPUtils.getInstance(LoginActivity.this).setLoginState(true);
//                                SPUtils.getInstance(LoginActivity.this).setUserName(phoneNumber);
//                                SPUtils.getInstance(LoginActivity.this).setPasswod(password);
//                                startActivity(new Intent(LoginActivity.this, EntranceActivity.class));
                                setResult(Activity.RESULT_OK, new Intent());
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
}
