package com.stephen.furniturerepair.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.SApplication;
import com.stephen.furniturerepair.common.interfaces.Constant;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.OkHttpUtils;
import com.stephen.furniturerepair.common.view.dialog.BufferDialog;

import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.E("onCreate Fragment: " + getClass().getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class BaseHandler extends Handler {

        private GlobalCallBack callBack;

        public BaseHandler(Context context, GlobalCallBack callBack) {
            this.callBack = callBack;
        }

        public void handleMessage(Message msg) {
            try {
                closeProgressDialog();
                if (msg.what == Constant.SUCCESS) {
                    callBack.processData((String) msg.obj);
                    if (msg.getData() != null) {
                        String url = msg.getData().getString("url", "");
                        boolean isFirst = msg.getData().getBoolean("isFirstCache", false);
                    }
                } else if (msg.what == Constant.Failure) {
                    callBack.responseError((String) msg.obj);
                }
            } catch (Exception e) {
                // LogUtils.e("Activity的基类 callBack:"+e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 从服务器上获取数据，并回调处理
     *
     * @param callBack
     */
    public void getDataFromServer(List<BasicNameValuePair> list, final String requestUrl, GlobalCallBack callBack) throws Exception {
        getDataFromServer(list,requestUrl,callBack,true);
    }


    /**
     * 从服务器上获取数据，并回调处理
     *
     * @param callBack
     */
    public void getDataFromServer(List<BasicNameValuePair> list, final String requestUrl, GlobalCallBack callBack, boolean isShowProgress ) throws IOException {
        LogUtils.E("请求接口为" + requestUrl );
        if (list != null && list.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (BasicNameValuePair basicNameValuePair : list) {
                stringBuilder.append(basicNameValuePair.getName());
                stringBuilder.append("=");
                stringBuilder.append(basicNameValuePair.getValue());
                stringBuilder.append("&");
            }
            String substring = stringBuilder.substring(0,stringBuilder.length() - 1);
            LogUtils.E(requestUrl + "?" + substring);
        }
        if (isShowProgress) {
            showProgressDialog();
        }
        final BaseHandler handler = new BaseHandler(getActivity(), callBack);
        OkHttpUtils.post(requestUrl, list, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Message msg = Message.obtain();
                msg.what = Constant.Failure;
                LogUtils.E( "返回数据 " + "错误！");
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message msg = Message.obtain();
                msg.what = Constant.SUCCESS;
                msg.obj = response.body().string();
                LogUtils.E( "返回数据 " + msg.obj);
                handler.sendMessage(msg);

            }
        });
    }

    public void showProgressDialog() {
        if (dialog_buffer == null) {
            String string = SApplication.getInstance().getAppContext().getString(R.string.app_name);
            String string1 = SApplication.getInstance().getAppContext().getString(R.string.dialog_loading);
            dialog_buffer = new BufferDialog(App.getContext(),string,string1,R.style.MyDialogStyleTop);
        }
        dialog_buffer.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        if (!dialog_buffer.isShowing()) {
            dialog_buffer.show();
        }
    }

    public void closeProgressDialog() {
        if (dialog_buffer != null) {
            dialog_buffer.dismiss();
            dialog_buffer = null;
        }
    }

    private BufferDialog dialog_buffer;
}
