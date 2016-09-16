package com.stephen.furniturerepair.app;

import android.app.Activity;
import android.content.Context;

import com.stephen.furniturerepair.service.BaiduLBSInfo;


/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class App {

    public static Context getContext(){
       return XunCaiApplication.getInstance().getApplicationContext();
    }

    public static void exitApp(){
        XunCaiApplication.getInstance().exitApp();
    }

    public static Activity getCurrentActivity(){
        return XunCaiApplication.getInstance().getCurrentActivity();
    }

//    public static BaiduLBSInfo getBaiduLBSInfo(){
//        return XunCaiApplication.getInstance().getBaiduLBSInfo();
//    }

    public static void clearAllActivity(){
        XunCaiApplication.getInstance().finishAllActivity();
    }
}
