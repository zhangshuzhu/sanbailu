package com.stephen.furniturerepair.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalBack;
import com.stephen.furniturerepair.common.utils.UniverImageLoadConfig;
import com.stephen.furniturerepair.service.BaiduLBSInfo;
import com.stephen.furniturerepair.service.MyLocationListener;

import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class SApplication3 extends Application {

    private static SApplication3 instance = null;
    private AppContext mAppContext = null;
    private List<BaseActivity> mActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        mAppContext = new AppContext(this);

        //初始化universal_image_load
        UniverImageLoadConfig.initUniverImageLoder(this, R.mipmap.ic_launcher);

        //初始化数据库
        LitePalApplication.initialize(this);

        //百度定位
        //initBDMap();
        initBaiduLBS();
    }
    public static SApplication3 getInstance(){
        return instance;
    }

    public Context getAppContext(){
        return mAppContext;
    }

    public void putActivity(BaseActivity activity){
        mActivities.add(activity);
    }

    public void removoeActivity(BaseActivity activity) {
        mActivities.remove(activity);
    }

    public void finishAllActivity() {
        for (BaseActivity a : mActivities) {
            a.finish();
        }
    }

    public Activity getCurrentActivity(){
        if (mActivities.size() > 0) {
            return mActivities.get(mActivities.size() - 1);
        }
        return null;
    }

    public void exitApp(){
        for (BaseActivity activity : mActivities) {
            activity.finish();
        }
        System.exit(0);
    }

    private void initBaiduLBS() {
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(60000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mLocationClient.start();
    }

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener(this, new GlobalBack(){
        @Override
        public void back(Object object) {
            if (object != null && object instanceof BaiduLBSInfo) {
                BaiduLBSInfo baiduLBSInfo = (BaiduLBSInfo) object;

                setmBaiduLBSInfo(baiduLBSInfo);
                mLocationClient.stop();
                mLocationClient.unRegisterLocationListener(myListener);
            }
        }
    });


    public void setmBaiduLBSInfo(BaiduLBSInfo mBaiduLBSInfo) {
        this.mBaiduLBSInfo = mBaiduLBSInfo;
    }

    private BaiduLBSInfo mBaiduLBSInfo;

    public BaiduLBSInfo getBaiduLBSInfo(){
        if (mBaiduLBSInfo == null) {
            if (myListener != null) {
                mLocationClient.requestLocation();
            }
            return null;
        }
        return mBaiduLBSInfo;
    }
}
