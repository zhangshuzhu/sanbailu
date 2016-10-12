package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.DataUtils;
import com.stephen.furniturerepair.common.utils.LogUtils;
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
    @Bind(R.id.rb_register_user)
    RadioButton rbRegisterUser;
    @Bind(R.id.rb_register_master)
    RadioButton rbRegisterMaster;
    @Bind(R.id.rb_register_team)
    RadioButton rbRegisterTeam;
    @Bind(R.id.rg_register_type)
    RadioGroup rgRegisterType;

    private Timer timer;
    private int second = 60;

    private String registPhoneNumber;
    private int type;
    private String phoneNum;
    private String verification;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("注册");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);
        editText.addTextChangedListener(this);
        textViewSendVerifyRequest.setOnClickListener(this);
        regiterNext.setOnClickListener(this);
        initListener();
    }

    private void initListener() {
        rgRegisterType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_register_user:
                        type = 0;
                        break;
                    case R.id.rb_register_master:
                        type = 1;
                        break;
                    case R.id.rb_register_team:
                        type = 2;
                        break;

                }
            }
        });
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
//                String s = editText.getText().toString();
//                if (TextUtils.isEmpty(s) || !Validator.isMobile(s)) {
//                    Toast.makeText(RegistActivity.this, "请输入有效的手机号码", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                LogUtils.E("---------------请求发送验证码");
                sendRequest(editText.getText().toString());
                break;
//            下一步填写注册信息
            case R.id.register_next:
                phoneNum = editText.getText().toString();
                verification = editText2.getText().toString();
                password = editText3.getText().toString();
//                if (TextUtils.isEmpty(phoneNum)) {
//                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
//                }else if (!Validator.isMobile(phoneNum)) {
//                    Toast.makeText(RegistActivity.this, "请输入有效的手机号码", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty()) {
//                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
//                } else if (!Validator.isPassword(password)) {
//                    Toast.makeText(RegistActivity.this, "请输入6-16位密码", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(registPhoneNumber)) {
//                    Toast.makeText(this, "注册前，请获取验证码", Toast.LENGTH_SHORT).show();
//                } else if(!phoneNum.equals(registPhoneNumber)){
//                    Toast.makeText(this, "当前手机号和获取验证码手机号不一致", Toast.LENGTH_SHORT).show();
//                } else {
                sendReques2t();
//                }
                break;
        }
    }

    private void userRegisterInfoActivity() {
        Intent intent = new Intent(RegistActivity.this, UserRegistInfoActivity.class);
        intent.putExtra("phoneNumber", phoneNum);
        intent.putExtra("verification", verification);
        intent.putExtra("password", password);
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


    private void sendReques2t() {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("mobile", phoneNum));
        list.add(new BasicNameValuePair("captcha", verification));
        try {
            getDataFromServer(list, URL.URL_CHECK_SMS, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (!DataUtils.dealResultFail(paramObject, 100)) {//正确
                        switch (type) {
//                            用户
                            case 0:
                                userRegisterInfoActivity();
                                break;
//                            修补工
                            case 1:
                                masterRegisterInfoActivity(type);
                                break;
//                            团队
                            case 2:
                                masterRegisterInfoActivity(type);
                                break;

                        }

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

    private void masterRegisterInfoActivity(int tag) {
        Intent intent = new Intent(RegistActivity.this, RegistInfoActivity.class);
        intent.putExtra("phoneNumber", phoneNum);
        intent.putExtra("type",tag );
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }
}
