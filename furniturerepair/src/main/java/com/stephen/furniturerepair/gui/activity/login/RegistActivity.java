package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.DataUtils;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.Validator;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class RegistActivity extends BaseActivity implements TextWatcher, View.OnClickListener, TitleBarListener.ListenerTitleBarLeft {
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.register_next)
    LinearLayout regiterNext;
    @Bind(R.id.textView_sendVerify_Request)
    TextView textViewSendVerifyRequest;
    @Bind(R.id.titleBar)
    TitleBar titleBar;

    private Timer timer;
    private int second = 60;

    private String registPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("注册");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back,this);
        editText.addTextChangedListener(this);
        textViewSendVerifyRequest.setOnClickListener(this);
        regiterNext.setOnClickListener(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_regiter;
    }

    private void sendRequest(String phoneNumber) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("mobile", phoneNumber));
        try {
            getDataFromServer(list, URL.URL_SEND_MOBILE_SMS, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    registPhoneNumber = editText.getText().toString();
                    startToTiming();
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String phoneNumber = editText.getText().toString();
        boolean b = phoneNumber.startsWith("1");
        int length = phoneNumber.length();
        if (b && length == 11) {
            // TODO: 2016/4/30 重置发送验证码按钮 倒计时
            resetVerificationStatus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_sendVerify_Request:
                String s = editText.getText().toString();
                if (TextUtils.isEmpty(s) || !Validator.isMobile(s)) {
                    Toast.makeText(RegistActivity.this, "请输入有效的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                LogUtils.E("---------------请求发送验证码");
                sendRequest(editText.getText().toString());
                break;
//            下一步填写注册信息
            case R.id.register_next:
                String ss = editText.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                if (TextUtils.isEmpty(ss)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else if (!Validator.isMobile(ss)) {
                    Toast.makeText(RegistActivity.this, "请输入有效的手机号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(s2)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else if (!Validator.isPassword(s3)) {
                    Toast.makeText(RegistActivity.this, "请输入6-16位密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(registPhoneNumber)) {
                    Toast.makeText(this, "注册前，请获取验证码", Toast.LENGTH_SHORT).show();
                } else if(!ss.equals(registPhoneNumber)){
                    Toast.makeText(this, "当前手机号和获取验证码手机号不一致", Toast.LENGTH_SHORT).show();
                } else {
                    sendReques2t(ss,s2,s3);
                }
                break;
        }
    }

    private void openStartRegisterInfoActivity(String ss, String s2, String s3) {
        Intent intent = new Intent(RegistActivity.this, RegistInfoActivity.class);
        intent.putExtra("phoneNumber", ss);
        intent.putExtra("verification", s2);
        intent.putExtra("password", s3);
        startActivity(intent);
        finish();
    }

    /**
     * 开始倒计时
     */
    private void startToTiming() {
        textViewSendVerifyRequest.setEnabled(false);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        second--;
                        if (second == 0) {
                            resetVerificationStatus();
                            return;
                        }
                        textViewSendVerifyRequest.setText(second + "秒");
                    }
                });

            }
        }, 0, 1000);
    }

    /**
     * 重置验证码状态
     */
    private void resetVerificationStatus() {
        textViewSendVerifyRequest.setText("获取验证码");
        textViewSendVerifyRequest.setEnabled(true);
        second = 60;
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void clickTitleBarLeft() {
        finish();
    }


    private void sendReques2t(final String phoneNumber, final String verification, final String s3) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("mobile", phoneNumber));
        list.add(new BasicNameValuePair("captcha", verification));
        try {
            getDataFromServer(list, URL.URL_CHECK_SMS, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (!DataUtils.dealResultFail(paramObject, 100)) {//正确
                        openStartRegisterInfoActivity(phoneNumber, verification, s3);
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
