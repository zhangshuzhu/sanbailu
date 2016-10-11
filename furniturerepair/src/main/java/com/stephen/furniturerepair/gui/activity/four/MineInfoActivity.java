package com.stephen.furniturerepair.gui.activity.four;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.bean.User;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.GsonUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

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
    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv_myinfo_photo)
    ImageView ivMyinfoPhoto;
    @Bind(R.id.rl_myinfo_photo)
    RelativeLayout rlMyinfoPhoto;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.tv_myinfo_nickname)
    TextView tvMyinfoNickname;
    @Bind(R.id.rl_myinfo_nickname)
    RelativeLayout rlMyinfoNickname;
    @Bind(R.id.iv3)
    ImageView iv3;
    @Bind(R.id.tv_myinfo_sex)
    TextView tvMyinfoSex;
    @Bind(R.id.rl_myinfo_sex)
    RelativeLayout rlMyinfoSex;
    @Bind(R.id.iv4)
    ImageView iv4;
    @Bind(R.id.tv_myinfo_phone)
    TextView tvMyinfoPhone;
    @Bind(R.id.rl_myinfo_phone)
    RelativeLayout rlMyinfoPhone;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.iv5)
    ImageView iv5;
    @Bind(R.id.tv_myinfo_address)
    TextView tvMyinfoAddress;
    @Bind(R.id.rl_myinfo_address)
    RelativeLayout rlMyinfoAddress;

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

    private void init(User user) {
        if (!TextUtils.isEmpty(user.getName()))
            tvMyinfoNickname.setText(user.getName());
        if (!TextUtils.isEmpty(user.getSex()))
            tvMyinfoSex.setText(user.getSex());
        if (!TextUtils.isEmpty(user.getMobile()))
            tvMyinfoPhone.setText(user.getMobile());
        if (!TextUtils.isEmpty(user.getAddress()))
            tvMyinfoAddress.setText(user.getAddress());

    }


    @Override
    protected void onResume() {
        super.onResume();
        String userInfo = SPUtils.getInstance(MineInfoActivity.this).getUserInfo();
        try {
            User user = GsonUtils.fromJson(userInfo, User.class);
            init(user);
            textView.setText("Json: " + userInfo + "\n\n JsonObject" + user.toString());
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
//            修改密码
            case R.id.linearLayout_loginOut:
                startActivity(new Intent(MineInfoActivity.this, ChangePasswordActivity.class));
                break;
        }
    }
}
