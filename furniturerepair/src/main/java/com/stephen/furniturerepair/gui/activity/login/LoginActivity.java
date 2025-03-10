package com.stephen.furniturerepair.gui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.bean.User;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.DataUtils;
import com.stephen.furniturerepair.common.utils.GsonUtils;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.utils.Validator;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;
import com.stephen.furniturerepair.gui.beans.UserInfoBean;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TitleBarListener.ListenerTitleBarLeft {
    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.register_next)
    LinearLayout regiterNext;
    @Bind(R.id.textView_login_goRegist)
    TextView textViewLoginGoRegist;
    @Bind(R.id.textView_login_forget)
    TextView textViewLoginForget;

    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setTitle("登录");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);
        regiterNext.setOnClickListener(this);
        textViewLoginGoRegist.setOnClickListener(this);
        textViewLoginForget.setOnClickListener(this);
    }

    /**
     * 登录
     *
     * @param phoneNumber 手机号
     * @param password    密码
     */
    private void sendRequest(final String phoneNumber, final String password) {
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
                            String message = jsonObject.getString("message");
                            switch (code) {
//                                登陆成功
                                case 100:
                                    if (!GsonUtils.isEmpty(jsonObject.getString("return"))) {
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                        SPUtils.getInstance(LoginActivity.this).setLoginState(true);
                                        LogUtils.E(jsonObject.getString("return"));

                                        User aReturn = GsonUtils.fromJson(jsonObject.getString("return"), User.class);
                                        if (aReturn != null) {
                                            SPUtils.getInstance(LoginActivity.this).setUserId(aReturn.getAid());
                                            SPUtils.getInstance(LoginActivity.this).setUserName(aReturn.getName());
                                            SPUtils.getInstance(LoginActivity.this).setUserInfo(GsonUtils.toJson(aReturn));
                                        }
                                    }

                                    SPUtils.getInstance(LoginActivity.this).setUserName(phoneNumber);
                                    SPUtils.getInstance(LoginActivity.this).setPasswod(password);
//                                startActivity(new Intent(LoginActivity.this, EntranceActivity.class));
                                    setResult(Activity.RESULT_OK, new Intent());
                                    finish();
                                    break;
//                                用户已登录
                                case 101:
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    break;
//                                账号不存在
                                 case 103:
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    break;

//                                登陆失败
                                case 302:
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    break;

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

    /**
     * 登录成功后将返回下来的数据存储到sp中
     *
     * @param paramObject 数据
     */
    private void dealResult(String paramObject) {
        String s = DataUtils.dealResult(paramObject);
        if (!TextUtils.isEmpty(s)) {
            UserInfoBean userInfo = GsonUtils.fromJson(s, UserInfoBean.class);
            if (userInfo != null) {
                SPUtils.getInstance(LoginActivity.this).setUserId(userInfo.getUserId());
                SPUtils.getInstance(LoginActivity.this).setUserPhone(userInfo.getPhoneNum());
                SPUtils.getInstance(LoginActivity.this).setUserName(userInfo.getName());
                SPUtils.getInstance(LoginActivity.this).setShopName(userInfo.getShopName());
                SPUtils.getInstance(LoginActivity.this).setCity(userInfo.getCity());
                SPUtils.getInstance(LoginActivity.this).setStreet(userInfo.getStreet());
                SPUtils.getInstance(LoginActivity.this).setAddress(userInfo.getAddress());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            登录
            case R.id.register_next:
                String phoneNumber = editText.getText().toString();
                String password = editText3.getText().toString();
                if (/*!Validator.isMobile(phoneNumber)*/ TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(LoginActivity.this, "请输入有效的手机号码", Toast.LENGTH_SHORT).show();
                } else if (!Validator.isPassword(password)) {
                    Toast.makeText(LoginActivity.this, "请输入6-16位有效的密码", Toast.LENGTH_SHORT).show();
                } else {
                    sendRequest(phoneNumber, password);
                }
                break;
//            注册
            case R.id.textView_login_goRegist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
//            忘记密码
            case R.id.textView_login_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
        }
    }

    @Override
    public void clickTitleBarLeft() {
        finish();
    }
}
