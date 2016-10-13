package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

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
 * <p/>
 * 修补工、团队注册信息
 */
public class RegistInfoActivity extends BaseActivity {
    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText5)
    EditText editText5;
    @Bind(R.id.register_next)
    LinearLayout regiterNext;
    @Bind(R.id.editText6)
    EditText editText6;
    @Bind(R.id.editText7)
    EditText editText7;
    @Bind(R.id.editText8)
    EditText editText8;
    @Bind(R.id.editText9)
    EditText editText9;
    @Bind(R.id.editText10)
    EditText editText10;
    @Bind(R.id.editText11)
    EditText editText11;
    @Bind(R.id.editText13)
    EditText editText13;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;
    @Bind(R.id.rb_male)
    RadioButton rbMale;
    @Bind(R.id.rb_female)
    RadioButton rbFemale;
    @Bind(R.id.ll_register_sex)
    LinearLayout llRegisterSex;
    @Bind(R.id.rb_pay_wx)
    RadioButton rbPayWx;
    @Bind(R.id.rb_pay_alipay)
    RadioButton rbPayAlipay;
    @Bind(R.id.rg_pay)
    RadioGroup rgPay;
    private String phoneNumber;
    private String passWord;
    private String sex = "男";
    private int type;
    private String pay_type="微信";

    @Override
    protected int setView() {
        return R.layout.activity_resiter_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("注册信息");
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        passWord = intent.getStringExtra("password");
        type = intent.getIntExtra("type", 0);
        if (type == 1) {
            llRegisterSex.setVisibility(View.VISIBLE);
        } else {
            llRegisterSex.setVisibility(View.GONE);
        }
        editText6.setText(phoneNumber);
        initListener();
    }

    private void initListener() {
        //        确定注册信息
        regiterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString().trim();//姓名
                String value1 = editText2.getText().toString().trim();//账号
                String value4 = editText5.getText().toString().trim();
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(RegistInfoActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(value1)) {
                    Toast.makeText(RegistInfoActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } /*else if (TextUtils.isEmpty(value2)) {
                    Toast.makeText(RegistInfoActivity.this, "城市名称不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(value3)) {
                    Toast.makeText(RegistInfoActivity.this, "街道名字不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(value4)) {
                    Toast.makeText(RegistInfoActivity.this, "请输入您的详细地址", Toast.LENGTH_SHORT).show();
                }*/ else {
                    goRegist(value, value1, value4);
                }
            }
        });
//        性别选择
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
//            女
                    case R.id.rb_female:
                        sex = "女";
                        break;
//            男
                    case R.id.rb_male:
                        sex = "男";
                        break;

                }
            }
        });
//        支付方式
        rgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
//            支付宝
                    case R.id.rb_pay_alipay:
                        pay_type = "支付宝";
                        break;
//            微信
                    case R.id.rb_male:
                        pay_type = "微信";
                        break;

                }
            }
        });
    }

    private void goRegist(String name, String account, String address) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("password", passWord));
        list.add(new BasicNameValuePair("name", name));
        if (type == 1) {
            list.add(new BasicNameValuePair("sex", sex));
        }
        list.add(new BasicNameValuePair("type", String.valueOf(type))); //类型(0:用户注册，1：修补工2：团队)
        list.add(new BasicNameValuePair("address", address));
        list.add(new BasicNameValuePair("mobile", phoneNumber));
        list.add(new BasicNameValuePair("cert_type", editText7.getText().toString().trim()));
        list.add(new BasicNameValuePair("cert_no", editText8.getText().toString().trim()));
        list.add(new BasicNameValuePair("technical", editText9.getText().toString().trim()));
        list.add(new BasicNameValuePair("expertise", editText10.getText().toString().trim()));
        list.add(new BasicNameValuePair("prictise", editText11.getText().toString().trim()));
        list.add(new BasicNameValuePair("pay_type", pay_type));
        list.add(new BasicNameValuePair("pay_no", editText13.getText().toString().trim()));
        list.add(new BasicNameValuePair("workman", "工人列表数组"));
        list.add(new BasicNameValuePair("telephone", "工人电话号码"));
        try {
            getDataFromServer(list, URL.URL_REGISTER, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (TextUtils.isEmpty(paramObject)) {
                        Toast.makeText(RegistInfoActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("message");
                            if (!TextUtils.isEmpty(msg)) {
                                Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                            if (code == 100) {
                                finish();
                            }
                        } catch (JSONException e) {
                            LogUtils.E(paramObject);
                            e.printStackTrace();
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

}
