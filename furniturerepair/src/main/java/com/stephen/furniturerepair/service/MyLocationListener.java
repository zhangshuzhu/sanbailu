package com.stephen.furniturerepair.service;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.stephen.furniturerepair.common.interfaces.GlobalBack;

import java.util.List;

public class MyLocationListener implements BDLocationListener {
    private Context mContext;
//    private BaiduLBSInfo mBaiduLBSInfo;
//    private LocationClient mLocationClient;
//    private BDLocationListener myListener;
    private GlobalBack globalBack;

    public MyLocationListener(Context context) {
        this.mContext = context;
    }

    public MyLocationListener(Context mContext, BaiduLBSInfo mBaiduLBSInfo, LocationClient mLocationClient, BDLocationListener myListener) {
        this.mContext = mContext;
    }

    public MyLocationListener(Context mContext, GlobalBack globalBack) {
        this.mContext = mContext;
        this.globalBack = globalBack;
    }

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
                BaiduLBSInfo mBaiduLBSInfo = new BaiduLBSInfo(time,latitude,longitude,country,city,district,street,addrStr,locationDescribe);
                globalBack.back(mBaiduLBSInfo);
//                mContext.sendBroadcast(new Intent(IntentFilters.IntentFilter_cityChanged));
//                mLocationClient.stop();
//                mLocationClient.unRegisterLocationListener(myListener);
            }
        }
    }