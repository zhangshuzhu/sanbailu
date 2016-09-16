package com.stephen.furniturerepair.common.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;


/**
 * GSON工具包
 *
 * @version 1.0
 * @created 2014-3-5 16:26:56
 */
public class GsonUtils {
    public static final GsonBuilder builder = new GsonBuilder();
    public static final Gson gson = builder.create();
    private static final String TAG = "GsonUtils";

    // 日志处理start
    // static String TAG =
    // LogUtils.generateTag(LogUtils.getCallerStackTraceElement());

    public static String toJson(Object target) {
        if (target == null) {
            return null;
        }

        try {

            return gson.toJson(target);
        } catch (Exception e) {
            //LogUtils.e("toJson:"+e);
        }
        return "";
    }

    public static String toJson(Object target, Type targetType) {
        if (target == null) {
            return null;
        }

        try {
            return gson.toJson(target, targetType);
        } catch (Exception e) {
            //LogUtils.e("toJson:"+e);
        }
        return "";
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (isEmpty(json)) {
            return null;
        }
        //String jsonstr = new String(json);
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            if (JSONUtils.isJson(json)) {
                LogUtils.E("clazz:" + clazz.getSimpleName() + "|jsonstr:" + json + "|========|" + e);
            }
        }
        return null;
    }

    public static <T> T fromJson(String json, Type type) {
        if (isEmpty(json)) {
            return null;
        }
        //String jsonstr = new String(json);
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            LogUtils.E(TAG, e.toString());
            if (JSONUtils.isJson(json)) {
                LogUtils.E("type:" + type + "|jsonstr:" + json + "|========|" + e);
            }
        }
        return null;
    }

    public static <T> T fromJson(String json, TypeToken<T> clazz) {
        if (isEmpty(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, clazz.getType());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.E("clazz:" + clazz.toString() + "|jsonstr:" + json + "|========|" + e.toString());
            LogUtils.E(e.toString());
            if (JSONUtils.isJson(json)) {
                LogUtils.E("clazz:" + clazz.toString() + "|jsonstr:" + json + "|========|" + e);
            }
        }
        return null;
    }

    public static boolean isEmpty(String inStr) {
        boolean reTag = false;
        if (inStr == null || "".equals(inStr)) {
            reTag = true;
        }
        return reTag;
    }

    private static Gson mGsonByWithoutExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static String toJsonByWithoutExpose(Object target) {
        if (target == null) {
            return null;
        }
        try {
            return mGsonByWithoutExpose.toJson(target);
        } catch (Exception e) {
            //LogUtils.E("toJson:"+e);
        }
        return "";
    }

    public static String getJsonValue(String data, String key) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String code = jsonObject.getString(key);
        if (!TextUtils.isEmpty(code)) {
            return code;
        }
        return "";
    }
}