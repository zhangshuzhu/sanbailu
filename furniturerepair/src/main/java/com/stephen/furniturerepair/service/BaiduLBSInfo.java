package com.stephen.furniturerepair.service;

/**
 * Created by Stephen on 2016/4/29 0029.
 * Emial: 895745843@qq.com
 */
public class BaiduLBSInfo {
    private String time;
    private double latitude;
    private double lontitude;
    private String country;
    private String city;
    private String district;
    private String street;
    private String addrStr;
    private String locationDescribe;

    public BaiduLBSInfo(String time, double latitude, double lontitude, String country, String city, String district, String street, String addrStr, String locationDescribe) {
        this.time = time;
        this.latitude = latitude;
        this.lontitude = lontitude;
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.addrStr = addrStr;
        this.locationDescribe = locationDescribe;
    }

    public String getTime() {
        return time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLontitude() {
        return lontitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public String getAddrStr() {
        return addrStr;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    @Override
    public String toString() {
        return "BaiduInfo{" +
                "time='" + time + '\'' +
                ", latitude='" + latitude + '\'' +
                ", lontitude='" + lontitude + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", addrStr='" + addrStr + '\'' +
                ", locationDescribe='" + locationDescribe + '\'' +
                '}';
    }
}
