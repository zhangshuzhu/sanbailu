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
       return SApplication.getInstance().getApplicationContext();
    }

    public static void exitApp(){
        SApplication.getInstance().exitApp();
    }

    public static Activity getCurrentActivity(){
        return SApplication.getInstance().getCurrentActivity();
    }

    public static BaiduLBSInfo getBaiduLBSInfo(){
        return SApplication.getInstance().getBaiduLBSInfo();
    }

    public static void clearAllActivity(){
        SApplication.getInstance().finishAllActivity();
    }
}
