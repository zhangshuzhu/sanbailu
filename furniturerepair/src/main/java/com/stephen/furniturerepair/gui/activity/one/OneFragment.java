package com.stephen.furniturerepair.gui.activity.one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.common.base.BaseFragment;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;
import com.stephen.furniturerepair.service.BaiduLBSInfo;


/**
 * Created by Stephen on 09/14/2016.
 * Emial: 895745843@qq.com
 */
public class OneFragment extends BaseFragment {
    private static final String TAG = "OneFragment";

    private TitleBar titleBarShopCart;
    private MapView mapMv;

    private BaiduMap mBaiduMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_one, container, false);

        titleBarShopCart = (TitleBar) view.findViewById(R.id.titleBar_shopCart);
        mapMv = (MapView) view.findViewById(R.id.map_mv);

        initView();
        return view;
    }

    private void initView() {
        titleBarShopCart.setTitlBartitle("首页");

        BaiduLBSInfo baiduLBSInfo = App.getBaiduLBSInfo();
        if (baiduLBSInfo != null) {
            double latitude = baiduLBSInfo.getLatitude();
            double lontitude = baiduLBSInfo.getLontitude();
            LogUtils.E(TAG, "--------->latitude:" + latitude);
            LogUtils.E(TAG, "--------->lontitude:" + lontitude);

            initBLSMap(latitude, lontitude);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initBLSMap(double mapLat, double mapLng) {
        try {
            mBaiduMap = mapMv.getMap();
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));

            LatLng point = new LatLng(mapLat, mapLng);

            reLocation(point);

            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshCity() {

    }

    public void reLoadData() {
        // TODO: 09/14/2016 do something
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapMv.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapMv.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapMv.onDestroy();
    }

    //移动地图 点击定位按钮 地图移动回当前点
    public void reLocation(LatLng ll) {
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
    }
}
