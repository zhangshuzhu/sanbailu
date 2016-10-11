package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.utils.DataUtils;
import com.stephen.furniturerepair.common.utils.GsonUtils;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
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
 *
 * 补充注册信息
 */
public class RegistInfoActivity extends BaseActivity {
    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText4)
    EditText editText4;
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
    @Bind(R.id.editText12)
    EditText editText12;
    @Bind(R.id.editText13)
    EditText editText13;
    @Bind(R.id.editText14)
    EditText editText14;
    @Bind(R.id.editText15)
    EditText editText15;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;
    private String phoneNumber;
    private String verification;
    private String passWord;
    //    private com.bigkoo.pickerview.OptionsPopupWindow pwOptions;
    private boolean isClickCity = true;
    private String sex = "男";
    private String type = "0";

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
        verification = intent.getStringExtra("verification");
        passWord = intent.getStringExtra("password");
        editText6.setText(phoneNumber);
        initListener();


//        initCityData();
//        editText3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isClickCity) {
//                    SoftKeyBoardUtils.hideSoftKeyBoard(RegistInfoActivity.this);
//                    showCityOpton(v, "hometown");
//                    isClickCity = false;
//                } else {
//                    isClickCity = true;
//                }
//            }
//        });
    }

    private void initListener() {
        //        确定注册信息
        regiterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString().trim();//姓名
                String value1 = editText2.getText().toString().trim();//账号
                type = editText4.getText().toString().trim();//用户类型
                if (TextUtils.isEmpty(type))
                    type = "0";
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
                    goRegist(value, value1);
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
    }

    private void goRegist(String name, String account) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
//        list.add(new BasicNameValuePair("phoneNum", phoneNumber));
//        list.add(new BasicNameValuePair("verifyCode", verification));
//        list.add(new BasicNameValuePair("password", passWord));


        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("password", passWord));
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("sex", sex));
        list.add(new BasicNameValuePair("type", type)); //类型(0:用户注册，1：修补工2：团队)
        list.add(new BasicNameValuePair("address", "北京市海淀区西北旺东路10号院百度科技园1号楼"));
        list.add(new BasicNameValuePair("mobile", phoneNumber));
        list.add(new BasicNameValuePair("cert_type", "身份证"));
        list.add(new BasicNameValuePair("cert_no", "230119198209012988"));
        list.add(new BasicNameValuePair("technical", "手机贴膜"));
        list.add(new BasicNameValuePair("expertise", "手机专业贴膜十年"));
        list.add(new BasicNameValuePair("prictise", "3年以上"));
        list.add(new BasicNameValuePair("pay_type", "支付宝"));
        list.add(new BasicNameValuePair("pay_no", phoneNumber));
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

    ArrayList<String> provicesItems = new ArrayList<String>();
    ArrayList<ArrayList<String>> cityItems = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<ArrayList<String>>> districtItems = new ArrayList<ArrayList<ArrayList<String>>>();
//    }

    PopupWindow.OnDismissListener mOnDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
        }
    };

    /**
     * 注册成功后将返回下来的数据存储到sp中
     *
     * @param paramObject 数据
     */
    private void dealResult(String paramObject) {
        String s = DataUtils.dealResult(paramObject);
        if (!TextUtils.isEmpty(s)) {
            UserInfoBean userInfo = GsonUtils.fromJson(s, UserInfoBean.class);
            if (userInfo != null) {
                SPUtils.getInstance(this).setUserId(userInfo.getUserId());
                SPUtils.getInstance(this).setUserPhone(userInfo.getPhoneNum());
                SPUtils.getInstance(this).setUserName(userInfo.getName());
                SPUtils.getInstance(this).setShopName(userInfo.getShopName());
                SPUtils.getInstance(this).setCity(userInfo.getCity());
                SPUtils.getInstance(this).setStreet(userInfo.getStreet());
                SPUtils.getInstance(this).setAddress(userInfo.getAddress());
                startActivity(new Intent(RegistInfoActivity.this, MainActivity.class));
                RegistInfoActivity.this.finish();
            }
        }
    }
}
