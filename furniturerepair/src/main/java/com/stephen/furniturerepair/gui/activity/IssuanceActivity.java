package com.stephen.furniturerepair.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.LogUtils;
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
 * 发单
 */
public class IssuanceActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.tv_suance_type)
    TextView tvSuanceType;
    @Bind(R.id.ll_suance_type)
    LinearLayout llSuanceType;
    @Bind(R.id.iv_shop_neer_image)
    ImageView ivShopNeerImage;
    @Bind(R.id.ll_neer_upload)
    LinearLayout llNeerUpload;
    @Bind(R.id.iv_shop_far_image)
    ImageView ivShopFarImage;
    @Bind(R.id.ll_far_upload)
    LinearLayout llFarUpload;
    @Bind(R.id.tv_suance_color)
    TextView tvSuanceColor;
    @Bind(R.id.ll_suance_color)
    LinearLayout llSuanceColor;
    @Bind(R.id.et_suance_desc)
    EditText etSuanceDesc;
    @Bind(R.id.tv_suance_date)
    TextView tvSuanceDate;
    @Bind(R.id.ll_suance_date)
    LinearLayout llSuanceDate;
    @Bind(R.id.et_suance_name)
    EditText etSuanceName;
    @Bind(R.id.et_suance_phone)
    EditText etSuancePhone;
    @Bind(R.id.et_suance_address)
    EditText etSuanceAddress;
    @Bind(R.id.ll_suance_release)
    LinearLayout llSuanceRelease;

    @Override
    protected int setView() {
        return R.layout.activity_issuance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBar.setTitlBartitle("发布信息");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);

//        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag("one");
//        if (fragmentByTag == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.action_bar, new OneFragment()).addToBackStack("one").commit();
//        } else {
//            getSupportFragmentManager().popBackStack("one", 1);
//        }
//
//        Fragment fragmentByTag2 = getSupportFragmentManager().findFragmentByTag("two");
//        if (fragmentByTag2 == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.action_bar, new TwoFragment()).addToBackStack("two").commit();
//        }else {
//            getSupportFragmentManager().popBackStack();
//        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

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

    @OnClick({R.id.ll_suance_type, R.id.ll_neer_upload, R.id.ll_far_upload, R.id.ll_suance_color, R.id.ll_suance_date, R.id.ll_suance_release})
    public void onClick(View view) {
        switch (view.getId()) {
//            修补类型
            case R.id.ll_suance_type:
                break;
//            上传近照
            case R.id.ll_neer_upload:
                break;
//            上传远照
            case R.id.ll_far_upload:
                break;
//            颜色
            case R.id.ll_suance_color:
                break;
//            预订时间
            case R.id.ll_suance_date:
                break;
//            发布
            case R.id.ll_suance_release:
                String discr = etSuanceDesc.getText().toString().trim();
                String name = etSuanceName.getText().toString().trim();
                String phone = etSuancePhone.getText().toString().trim();
                String address = etSuanceAddress.getText().toString().trim();
                if (TextUtils.isEmpty(discr)) {
                    Toast.makeText(IssuanceActivity.this, "请填写客户需求信息", Toast.LENGTH_SHORT).show();
                } else {
                    publishInfo(discr, name, phone, address);
                }
                break;
        }
    }

    private void publishInfo(String descr, String name, String phone, String address) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
//        list.add(new BasicNameValuePair("account", account));
//        list.add(new BasicNameValuePair("title", passWord));
        list.add(new BasicNameValuePair("repair_type", tvSuanceType.getText().toString()));
//        list.add(new BasicNameValuePair("recent_phone", ));//二进制流  base64字符串
//        list.add(new BasicNameValuePair("far_phone", ));
//        list.add(new BasicNameValuePair("recent_name", ));//上传近照文件名称（在相册里读出的文件名）
//        list.add(new BasicNameValuePair("far_name", ));//上传远照名称
        list.add(new BasicNameValuePair("contact_address", address));//"北京市海淀区西北旺东路10号院百度科技园1号楼"
        list.add(new BasicNameValuePair("contact_number", phone));
        list.add(new BasicNameValuePair("contact_man", name));
        list.add(new BasicNameValuePair("color", tvSuanceColor.getText().toString()));
        list.add(new BasicNameValuePair("customer_demand", descr));
        list.add(new BasicNameValuePair("repair_time", tvSuanceDate.getText().toString()));
        String userId = SPUtils.getInstance(IssuanceActivity.this).getUserId();
        list.add(new BasicNameValuePair("id", userId));
//        list.add(new BasicNameValuePair("coupon", ));//卷号（默认生成8位卷号）
//        list.add(new BasicNameValuePair("status",));//订单状态(0:订单已发布，1：订单已购买，2：订单已支付，9:订单已取消)

        try {
            getDataFromServer(list, URL.URL_PUBLISH, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (TextUtils.isEmpty(paramObject)) {
                        Toast.makeText(IssuanceActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("message");
                            if (!TextUtils.isEmpty(msg)) {
                                Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                            if (code == 100) {
                                MainActivity.showIndex = 0;
                                startActivity(new Intent(IssuanceActivity.this, MainActivity.class));
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
