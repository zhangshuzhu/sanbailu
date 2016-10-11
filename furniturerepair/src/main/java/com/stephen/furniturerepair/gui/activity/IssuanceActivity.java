package com.stephen.furniturerepair.gui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

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
                // TODO: 2016/10/11
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
                break;
        }
    }
}
