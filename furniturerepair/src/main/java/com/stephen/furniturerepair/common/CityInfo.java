package com.stephen.furniturerepair.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShiWeiZong
 * date 2015/10/817:25
 * email zong4wei@163.com
 */
public class CityInfo implements Parcelable {
    /**
     * cityName : 北京
     * cityID : 1
     */

    public String cityName;
    public String cityID;
    public boolean isSelect;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.cityID);
        dest.writeByte(isSelect ? (byte) 1 : (byte) 0);
    }

    public CityInfo() {
    }

    protected CityInfo(Parcel in) {
        this.cityName = in.readString();
        this.cityID = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<CityInfo> CREATOR = new Creator<CityInfo>() {
        public CityInfo createFromParcel(Parcel source) {
            return new CityInfo(source);
        }

        public CityInfo[] newArray(int size) {
            return new CityInfo[size];
        }
    };
}
