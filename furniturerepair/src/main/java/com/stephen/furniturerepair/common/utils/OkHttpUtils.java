package com.stephen.furniturerepair.common.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    static {

        mOkHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

    }

    public static String run(String url) throws IOException {

        Request request = new Request.Builder().url(url).build();

        Response response = mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();

        } else {
            throw new IOException("Unexpected code " + response);

        }

    }

    /**
     * 该不会开启异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */

    public static Response execute(Request request) throws IOException {

        return mOkHttpClient.newCall(request).execute();

    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */

    public static void enqueue(Request request, Callback responseCallback) {

        mOkHttpClient.newCall(request).enqueue(responseCallback);

    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */

    public static void enqueue(Request request) {

        mOkHttpClient.newCall(request).enqueue(new Callback() {


            @Override

            public void onResponse(Response arg0) throws IOException {


            }


            @Override

            public void onFailure(Request arg0, IOException arg1) {


            }

        });

    }

    public static String getStringFromServer(String url) throws IOException {

        Request request = new Request.Builder().url(url).build();

        Response response = execute(request);

        if (response.isSuccessful()) {

            String responseUrl = response.body().string();

            return responseUrl;

        } else {

            throw new IOException("Unexpected code " + response);

        }

    }

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 这里使用了HttpClinet的API。只是为了方便
     *
     * @param params
     * @return
     */

    public static String formatParams(List<BasicNameValuePair> params) {

        return URLEncodedUtils.format(params, CHARSET_NAME);

    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */

    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {

        return url + "?" + formatParams(params);

    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */

    public static String attachHttpGetParam(String url, String name, String value) {

        return url + "?" + name + "=" + value;

    }
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    public static String post(String url, String json) throws IOException {
//        Log.i("", "json:" + json);
        RequestBody body = RequestBody.create(JSON, json);
//        Log.i("", "body:" + body.toString());
        Request request = new Request.Builder()

                .url(url)

                .post(body)

                .build();

        Response response = mOkHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {

            return response.body().string();

        } else {
            throw new IOException("Unexpected code " + response);

        }

    }
    public static void post(String url, String json,Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()

                .url(url)

                .post(body)

                .build();

        if (callback!=null) {
            mOkHttpClient.newCall(request).enqueue(callback);
        }

    }
    public static String  post(String url, List<BasicNameValuePair> list) throws IOException {
        return  post(url,formatParams(list));
    }
    public static void  post(String url, List<BasicNameValuePair> list,Callback callback) throws IOException {
        post(url,formatParams(list),callback);
    }
}