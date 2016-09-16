package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.GlobalVariable;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.utils.DataUtils;
import com.stephen.furniturerepair.common.utils.GsonUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;
import com.stephen.furniturerepair.gui.beans.CityInfoBean;
import com.stephen.furniturerepair.gui.beans.DistrictInfoBean;
import com.stephen.furniturerepair.gui.beans.ProvicesInfoBean;
import com.stephen.furniturerepair.gui.beans.UserInfoBean;

import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class RegistInfoActivity extends BaseActivity {
    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    TextView editText3;
    @Bind(R.id.editText4)
    EditText editText4;
    @Bind(R.id.editText5)
    EditText editText5;
    @Bind(R.id.register_next)
    LinearLayout regiterNext;
    private String phoneNumber;
    private String verification;
    private String passWord;
//    private com.bigkoo.pickerview.OptionsPopupWindow pwOptions;
    private boolean isClickCity = true;

    @Override
    protected int setView() {
        return R.layout.activity_resiter_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("注册");
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        verification = intent.getStringExtra("verification");
        passWord = intent.getStringExtra("password");
        regiterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString().trim();
                String value1 = editText2.getText().toString().trim();
                String value2 = editText3.getText().toString().trim();
                String value3 = editText4.getText().toString().trim();
                String value4 = editText5.getText().toString().trim();
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(RegistInfoActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(value1)) {
                    Toast.makeText(RegistInfoActivity.this, "店铺名称不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(value2)) {
                    Toast.makeText(RegistInfoActivity.this, "城市名称不能为空", Toast.LENGTH_SHORT).show();
                } /*else if (TextUtils.isEmpty(value3)) {
                    Toast.makeText(RegistInfoActivity.this, "街道名字不能为空", Toast.LENGTH_SHORT).show();
                }*/ else if (TextUtils.isEmpty(value4)) {
                    Toast.makeText(RegistInfoActivity.this, "请输入您的详细地址", Toast.LENGTH_SHORT).show();
                } else {
                    goRegist(value, value1, value2, value3, value4);
                }
            }
        });

        initCityData();
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

    private void goRegist(String value, String value1, String value2, String value3, String value4) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("phoneNum", phoneNumber));
        list.add(new BasicNameValuePair("verifyCode", verification));
        list.add(new BasicNameValuePair("password", passWord));
        list.add(new BasicNameValuePair("name", value));
        list.add(new BasicNameValuePair("shopName", value1));
        list.add(new BasicNameValuePair("city", value2));
        list.add(new BasicNameValuePair("street", value3));
        list.add(new BasicNameValuePair("address", value4));
        list.add(new BasicNameValuePair("from", GlobalVariable.FROM));
        list.add(new BasicNameValuePair("purpose", "0"));
        try {
            getDataFromServer(list, URL.URL_REGIST, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (TextUtils.isEmpty(paramObject)) {
                        Toast.makeText(RegistInfoActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    } else {
                        dealResult(paramObject);
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
    private int[] cityPositon = new int[]{0, 0, 0, 0, 0, 0};

//    private void showCityOpton(final View v, final String itemKey) {
//        //选项选择器
//        pwOptions = new OptionsPopupWindow(this);
//        pwOptions.setTextSize(DensityUtils.dp2px(this, 20));
//        pwOptions.setPicker(provicesItems, cityItems, districtItems, true);
//        if ("hometown".equals(itemKey)) {
//            pwOptions.setSelectOptions(cityPositon[0], cityPositon[1], cityPositon[2]);
//            isClickCity = true;
//        } else {
//            pwOptions.setSelectOptions(cityPositon[3], cityPositon[4], cityPositon[5]);
//            isClickCity = true;
//        }
//        pwOptions.setCyclic(false);
//        pwOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3) {
//                if (provicesItems.get(options1) != null && !provicesItems.get(options1).equals(cityItems.get(options1).get(option2))) {
//                    editText3.setText(provicesItems.get(options1) + " " + cityItems.get(options1).get(option2) + " " + districtItems.get(options1).get(option2).get(options3));
//                } else {
//                    editText3.setText(cityItems.get(options1).get(option2) + " " + districtItems.get(options1).get(option2).get(options3));
//                }
//
//                if ("hometown".equals(itemKey)) {
//                    cityPositon[0] = options1;
//                    cityPositon[1] = option2;
//                    cityPositon[2] = options3;
//                } else {
//                    cityPositon[3] = options1;
//                    cityPositon[4] = option2;
//                    cityPositon[5] = options3;
//                }
//            }
//        });
//        pwOptions.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//        pwOptions.setOnDismissListener(mOnDismissListener);
//    }

    PopupWindow.OnDismissListener mOnDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
        }
    };
    private String provicesObj;
    private String cityObj;
    private String districtObj;

    private void initCityData() {
        try {
            for (int i = 0; i < 3; i++) {
                StringBuffer provicesSb = new StringBuffer();
                InputStream is = getAssets().open(i == 0 ? "provices.json" : (i == 1 ? "city.json" : "district.json"));
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    provicesSb.append(new String(buf, 0, len));
                }
                is.close();
                if (i == 0) {
                    provicesObj = provicesSb.toString();
                } else if (i == 1) {
                    cityObj = provicesSb.toString();
                } else {
                    districtObj = provicesSb.toString();
                }
            }

            ArrayList<ProvicesInfoBean> provicesInfos = GsonUtils.fromJson(provicesObj, new TypeToken<ArrayList<ProvicesInfoBean>>() {
            });
            for (ProvicesInfoBean pInfo : provicesInfos) {
                provicesItems.add(pInfo.name);
            }

            ArrayList<CityInfoBean> cityInfoBeen = GsonUtils.fromJson(cityObj, new TypeToken<ArrayList<CityInfoBean>>() {
            });
            for (ProvicesInfoBean pinfo : provicesInfos) {
                ArrayList<String> list = new ArrayList<String>();
                for (CityInfoBean cInfo : cityInfoBeen) {
                    if (pinfo.ProID == cInfo.ProID) {
                        list.add(cInfo.name);
                    }

                }
                cityItems.add(list);
            }

            ArrayList<DistrictInfoBean> districtInfoBeen = GsonUtils.fromJson(districtObj, new TypeToken<ArrayList<DistrictInfoBean>>() {
            });
            for (ProvicesInfoBean pinfo : provicesInfos) {
                ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
                for (CityInfoBean cinfo : cityInfoBeen) {
                    if (pinfo.ProID == cinfo.ProID) {

                        ArrayList<String> list2 = new ArrayList<String>();
                        for (DistrictInfoBean dInfo : districtInfoBeen) {
                            if (cinfo.CityID == dInfo.CityID) {
                                list2.add(dInfo.DisName);
                            }
                        }
                        list1.add(list2);
                    }
                }
                districtItems.add(list1);
            }
//            long endTime = System.currentTimeMillis();
//            LogUtils.e("时间" + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册成功后将返回下来的数据存储到sp中
     * @param paramObject 数据
     */
    private void dealResult(String paramObject) {
        String s = DataUtils.dealResultData(paramObject);
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
