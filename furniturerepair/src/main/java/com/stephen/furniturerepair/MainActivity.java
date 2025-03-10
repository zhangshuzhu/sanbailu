package com.stephen.furniturerepair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.IntentFilters;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.gui.activity.IssuanceActivity;
import com.stephen.furniturerepair.gui.activity.four.FourFragment;
import com.stephen.furniturerepair.gui.activity.four.MineActivity;
import com.stephen.furniturerepair.gui.activity.login.LoginActivity;
import com.stephen.furniturerepair.gui.activity.one.OneFragment;
import com.stephen.furniturerepair.gui.activity.three.ThreeFragment;
import com.stephen.furniturerepair.gui.activity.two.TwoFragment;
import com.stephen.furniturerepair.gui.widget.MyFragmentTabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @Bind(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @Bind(android.R.id.tabhost)
    MyFragmentTabHost tabhost;
    @Bind(R.id.radio0)
    RadioButton radio0;
    @Bind(R.id.radio1)
    RadioButton radio1;
    @Bind(R.id.radio2)
    RadioButton radio2;
    @Bind(R.id.radio3)
    RadioButton radio3;
    @Bind(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @Bind(R.id.act_main_nav_rl)
    RelativeLayout actMainNavRl;
    @Bind(R.id.main_content_rl)
    RelativeLayout mainContentRl;

    private MyBrocastReceiver myBrocastReceiver;
    private FragmentManager supportFragmentManager = getSupportFragmentManager();

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (getIntent().getExtras() == null) {
//            startActivity(new Intent(MainActivity.this, GuideActivity.class));
//            finish();
//            return;
//        }
        super.onCreate(savedInstanceState);
        registBrocastReceiver();
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initTabHost();
    }

    @Override
    protected void onResume() {
        LogUtils.E("TAG", "-------------onResume()  showIndex:" + showIndex);
        LogUtils.E("TAG", "-------------onResume()  currentIndex:" + currentIndex);
        if (showIndex == 0) {
            radioGroup1.check(R.id.radio0);
            tabhost.setCurrentTabByTag("0");
            currentIndex = 0;
        } else if (showIndex == 1) {
            radioGroup1.check(R.id.radio1);
            tabhost.setCurrentTabByTag("1");
            currentIndex = 1;
        } else {
            radioGroup1.check(R.id.radio0);
            tabhost.setCurrentTabByTag("0");
            currentIndex = 0;
        }
        super.onResume();
    }

    private void initTabHost() {
        // 设置TabHost R.id.realtabcontent 为显示Fragment的容器
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 创建Tabs，设置显示的标题
        TabHost.TabSpec tabSpecA = tabhost.newTabSpec("0").setIndicator("A");
        TabHost.TabSpec tabSpecB = tabhost.newTabSpec("1").setIndicator("B");
//        TabHost.TabSpec tabSpecC = tabhost.newTabSpec("2").setIndicator("C");
//        TabHost.TabSpec tabSpecD = tabhost.newTabSpec("3").setIndicator("D");
        tabhost.addTab(tabSpecA, OneFragment.class, null);
        tabhost.addTab(tabSpecB, TwoFragment.class, null);
//        tabhost.addTab(tabSpecC, ThreeFragment.class, null);
//        tabhost.addTab(tabSpecD, EmptyFragment.class, null);
      /*  if (TextUtils.isEmpty(SPUtils.getInstance(MainActivity.this).getUserId())) {
            Bundle bundle = new Bundle();
            bundle.putString("title","购物车");
            tabhost.addTab(tabSpecB, EmptyFragment.class, bundle);
            Bundle bundle2 = new Bundle();
            bundle2.putString("title","我的");
            tabhost.addTab(tabSpecC, EmptyFragment.class, bundle2);
        } else {
            tabhost.addTab(tabSpecB, ShopCartFragment.class, null);
            tabhost.addTab(tabSpecC, MineFragment.class, null);
        }*/
        tabhost.getTabWidget().setVisibility(View.GONE);    // 隐藏 TabWidget
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup1.check(R.id.radio0);
        showIndex = 0;
    }

    private static final int CODE_ISSUANCE = 0x5;
    private static final int CODE_MINE = 0x6;

    private int currentIndex;
    public static int showIndex;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
//            首页
            case R.id.radio0:
                LogUtils.E("TAG", "-------------onCheckedChanged()  0");
                tabhost.setCurrentTabByTag("0");
                currentIndex = 0;
                showIndex = 0;
                break;
//            联盟
            case R.id.radio1:
                LogUtils.E("TAG", "-------------onCheckedChanged()  1");
                tabhost.setCurrentTabByTag("1");
                currentIndex = 1;
                showIndex = 1;
                break;
//            发单
            case R.id.radio2:
                LogUtils.E("TAG", "-------------onCheckedChanged()  2");

                if (currentIndex == 2) {
                    return;
                }

                if (SPUtils.getInstance(MainActivity.this).getLoginState()) {
                    startActivity(new Intent(MainActivity.this, IssuanceActivity.class));
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), CODE_ISSUANCE);
                }
                currentIndex = 2;
                break;
//            我的
            case R.id.radio3:
                LogUtils.E("TAG", "-------------onCheckedChanged()  3");
//                tabhost.setCurrentTabByTag("3");

                if (currentIndex == 3) {
                    return;
                }
                if (SPUtils.getInstance(MainActivity.this).getLoginState()) {
                    startActivity(new Intent(MainActivity.this, MineActivity.class));
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), CODE_MINE);
                }
                currentIndex = 3;
                break;
        }
    }

    private long startTime;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - startTime < 2000) {
            App.exitApp();
        } else {
            Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
            startTime = System.currentTimeMillis();
        }
    }

    private void registBrocastReceiver() {
        myBrocastReceiver = new MyBrocastReceiver();
        registerReceiver(myBrocastReceiver, new IntentFilter(IntentFilters.IntentFilter_cityChanged));
        registerReceiver(myBrocastReceiver, new IntentFilter(IntentFilters.IntentFilter_refreshFirst));
        registerReceiver(myBrocastReceiver, new IntentFilter(IntentFilters.IntentFilter_refreshSecond));
        registerReceiver(myBrocastReceiver, new IntentFilter(IntentFilters.IntentFilter_refreshThird));
        registerReceiver(myBrocastReceiver, new IntentFilter(IntentFilters.IntentFilter_refreshFourth));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegistBrocastReceiver();
    }

    private void unRegistBrocastReceiver() {
        unregisterReceiver(myBrocastReceiver);
    }

    class MyBrocastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case IntentFilters.IntentFilter_cityChanged:
                    Fragment classificationFragment = supportFragmentManager.findFragmentByTag("0");
                    if (classificationFragment instanceof OneFragment) {
                        ((OneFragment) classificationFragment).refreshCity();
                    }
                    break;
                case IntentFilters.IntentFilter_refreshFirst:
                    Fragment fragmentByTag0 = supportFragmentManager.findFragmentByTag("0");
                    if (fragmentByTag0 instanceof OneFragment) {
                        ((OneFragment) fragmentByTag0).reLoadData();
                    }
                    break;
                case IntentFilters.IntentFilter_refreshSecond:
                    Fragment fragmentByTag1 = supportFragmentManager.findFragmentByTag("1");
                    if (fragmentByTag1 instanceof TwoFragment) {
                        ((TwoFragment) fragmentByTag1).reLoadData();
                    }
                    break;
                case IntentFilters.IntentFilter_refreshThird:
                    Fragment fragmentByTag2 = supportFragmentManager.findFragmentByTag("2");
                    if (fragmentByTag2 instanceof ThreeFragment) {
                        ((ThreeFragment) fragmentByTag2).reLoadData();
                    }
                    break;
                case IntentFilters.IntentFilter_refreshFourth:
                    Fragment fragmentByTag3 = supportFragmentManager.findFragmentByTag("3");
                    if (fragmentByTag3 instanceof FourFragment) {
                        ((FourFragment) fragmentByTag3).reLoadData();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && data != null) {
            switch (requestCode) {
                case CODE_ISSUANCE:
                    startActivity(new Intent(MainActivity.this, IssuanceActivity.class));
                    break;
                case CODE_MINE:
                    startActivity(new Intent(MainActivity.this, MineActivity.class));
                    break;
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
