package com.stephen.furniturerepair.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {

    private static SPUtils instance;
    private SharedPreferences sp;

    private static final String SP_NAME                         = "91xuncai.com";
    private static final String USER_ID                         = "userId";
    private static final String USER_PHONE                      = "phoneNum";
    private static final String USER_NAME                       = "name";
    private static final String SHOP_NAME                       = "shopName";
    private static final String CITY                            = "city";
    private static final String STREET                          = "street";
    private static final String ADDRESS                         = "address";
    private static final String IS_LOGIN                        = "isLogin";

    /** 新增 */
    private static final String PASSWORD                        = "password";

    public SPUtils(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SPUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SPUtils(context);
        }
        return instance;
    }

    public void setUserId(String userId){
        sp.edit().putString(USER_ID,userId).apply();
    }

    public String getUserId() {
        return sp.getString(USER_ID,"");
    }

    public void setUserPhone(String userPhone){
        sp.edit().putString(USER_PHONE,userPhone).apply();
    }

    public String getUserPhone() {
        return sp.getString(USER_PHONE,"");
    }

    public void setUserName(String userName){
        sp.edit().putString(USER_NAME,userName).apply();
    }

    public String getUserName() {
        return sp.getString(USER_NAME,"");
    }

    public void setShopName(String shopName){
        sp.edit().putString(SHOP_NAME,shopName).apply();
    }

    public String getShopName() {
        return sp.getString(SHOP_NAME,"");
    }

    public void setCity(String city){
        sp.edit().putString(CITY,city).apply();
    }

    public String getCity() {
        return sp.getString(CITY,"");
    }


    public void setStreet(String street){
        sp.edit().putString(STREET,street).apply();
    }


    public String getStreet() {
        return sp.getString(STREET,"");
    }


    public void setAddress(String address){
        sp.edit().putString(ADDRESS,address).apply();
    }


    public String getAddress() {
        return sp.getString(ADDRESS,"");
    }

    public void setLoginState(boolean value) {
        sp.edit().putBoolean(IS_LOGIN, value).apply();
    }

    public Boolean getLoginState() {
        return sp.getBoolean(IS_LOGIN, false);
    }

    public void setPasswod(String password) {
        sp.edit().putString(PASSWORD, password).apply();
    }

    public String getPassword() {
        return sp.getString(PASSWORD,"");
    }

    /**  自己设置key */

    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }
}
