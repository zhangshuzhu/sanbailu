package com.stephen.furniturerepair.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.CityInfo;
import com.stephen.furniturerepair.common.Config;
import com.stephen.furniturerepair.common.DataService;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.Constant;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.utils.UniverImageLoadConfig;
import com.stephen.furniturerepair.service.BaiduLBSInfo;

import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class XunCaiApplication extends Application {

    private static XunCaiApplication instance = null;
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
        initBDMap();
    }
    public static XunCaiApplication getInstance(){
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

/*    private void initBaiduLBS() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(60000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mLocationClient.start();
    }

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            boolean isStopServer = false;
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
                isStopServer = true;
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("网络定位成功");
                isStopServer = true;
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
                isStopServer = true;
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
            if (isStopServer) {
                String time = location.getTime();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String country = location.getCountry();
                String city = location.getCity();
                String district = location.getDistrict();
                String street = location.getStreet();
                String addrStr = location.getAddrStr();
                String locationDescribe = location.getLocationDescribe();
                mBaiduLBSInfo = new BaiduLBSInfo(time,latitude,longitude,country,city,district,street,addrStr,locationDescribe);
                sendBroadcast(new Intent(IntentFilters.IntentFilter_cityChanged));
                mLocationClient.stop();
                mLocationClient.unRegisterLocationListener(myListener);
            }
        }
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
    }*/

    public static LocationClient mLocClient;
    public static ServiceConnection conn;
    private long preTime = 0;

    private void initBDMap() {
        SDKInitializer.initialize(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        int span = 60000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        mLocClient = new LocationClient(this);
        mLocClient.setLocOption(option);
        mLocClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                String lat = location.getLatitude() + "";
                String lng = location.getLongitude() + "";
                String city = location.getCity();
                if (city != null) {
                    if (Config.CITY_INFO == null) {
//                        if (cityInfos != null) {
//                            for (int i = 0; i < cityInfos.size(); i++) {
//                                CityInfo cityInfo = cityInfos.get(i);
//                                if (TextUtils.isEmpty(city) || TextUtils.isEmpty(cityInfo.cityName)) {
//                                    return;
//                                }
//                                if (cityInfo.cityName.contains(city) || city.contains(cityInfo.cityName)) {
//                                    Config.CITY_INFO = cityInfo;
//                                    sendBroadcast(new Intent("cityChanged"));
//                                    break;
//                                }
//                            }
//                        }
                    }
                }
                if (TextUtils.isEmpty(lat) && TextUtils.isEmpty(lng)) {
                    Constant.latitude = Double.valueOf(lat);
                    Constant.longitude = Double.valueOf(lng);
                } else {
                    return;
                }
                SPUtils.getInstance(XunCaiApplication.getInstance()).setString("Latitude",Constant.latitude + "");
                SPUtils.getInstance(XunCaiApplication.getInstance()).setString("Longitude",Constant.longitude + "");
                if (System.currentTimeMillis() - preTime < 20000) {
                    return;
                }
                preTime = System.currentTimeMillis();
                startService(new Intent(XunCaiApplication.getInstance(), DataService.class));
            }

        });
        mLocClient.start();
    }
}
