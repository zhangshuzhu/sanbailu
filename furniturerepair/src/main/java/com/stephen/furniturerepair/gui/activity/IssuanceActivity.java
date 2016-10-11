package com.stephen.furniturerepair.gui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stephen on 09/16/2016.
 * Emial: 895745843@qq.com
 *
 *  发单
 */
public class IssuanceActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;

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

    private void closeActivity(){
//        setResult(Activity.RESULT_OK);
        finish();
    }
}
