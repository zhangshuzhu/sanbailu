package com.stephen.furniturerepair.common;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class DataService extends Service {
    private static final String TAG = "DataService";
    private MyBinder myBinder = new MyBinder();
    private Timer timer;
    private boolean isRequest;
    private String userID;
    private String userImg;

    public class MyBinder extends Binder {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        timer = new Timer();
        isRequest = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isRequest = true;
            }
        }, 1, 55000);
//        EventBus.getDefault().register(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isRequest) {
//            upJingWei();
            isRequest = false;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
