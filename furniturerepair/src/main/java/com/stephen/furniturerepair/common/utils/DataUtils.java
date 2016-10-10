package com.stephen.furniturerepair.common.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.stephen.furniturerepair.app.App;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Stephen on 2016/4/4 0004.
 * Emial: 895745843@qq.com
 */
public class DataUtils {
    private static final int SECCESS_CODE = 100;

    /**
     * 成功返 回data数据 失败 弹出土司
     * @param json json数据
     * @return 成功 / 失败
     */
    public static String dealResultData(String json) {
       return dealResultData(json, SECCESS_CODE);
    }

    /**
     * 成功返 回data数据 失败 弹出土司
     * @param json json数据
     * @return 成功 / 失败
     */
    public static String dealResultData(String json, int seccessCode) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("code");
            if (code != seccessCode) {
                String msg = jsonObject.getString("messge");
                LogUtils.E("HTTP erroCode: "  + code + " errorMsg: " + msg );
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                return jsonObject.getString("return");
            }
        } catch (JSONException e) {
            LogUtils.E(json);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 成功 土司
     * @param json json数据
     * @return 成功 true
     */
    public static boolean dealResultSeccess(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("code");
            if (code == SECCESS_CODE) {
                String msg = jsonObject.getString("messge");
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        } catch (JSONException e) {
            LogUtils.E(json);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 失败 土司
     * @param json json数据
     * @return 失败 true
     */
    public static boolean dealResultFail(String json) {
        return dealResultFail(json, SECCESS_CODE);
    }

    /**
     * 失败 土司
     * @param json json数据
     * @return 失败 true
     */
    public static boolean dealResultFail(String json, int SeccessCode) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String data = jsonObject.getString("return");
            int code = jsonObject.getInt("code");
            String msg = jsonObject.getString("messge");
            if (code != SeccessCode) {
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        } catch (JSONException e) {
            LogUtils.E(json);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 无论成功失败 都土司
     * @param json json 数据
     */
    public static void dealToastMsg(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("code");
            String msg = jsonObject.getString("messge");
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            LogUtils.E(json);
            e.printStackTrace();
        }
    }

    /**
     * 无论成功失败 都土司 并返回结果
     * @param json json 数据
     * @return 成功 true 失败 false
     */
    public static boolean dealToastMsgWithRightAndWrong(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("code");
            String msg = jsonObject.getString("messge");
            if (!TextUtils.isEmpty(msg)) {
                Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
            if (code == SECCESS_CODE) {
                return true;
            }
        } catch (JSONException e) {
            LogUtils.E(json);
            e.printStackTrace();
        }
        return false;
    }
}
