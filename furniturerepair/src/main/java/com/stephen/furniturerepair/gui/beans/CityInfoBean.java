package com.stephen.furniturerepair.gui.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShiWeiZong
 * date 2015/10/3015:27
 * email zong4wei@163.com
 */
public class CityInfoBean implements Parcelable {

    /**
     * CityID : 1
     * name : 北京市
     * ProID : 1
     * CitySort : 1
     */

    public int CityID;
    public String name;
    public int ProID;
    public int CitySort;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CityID);
        dest.writeString(this.name);
        dest.writeInt(this.ProID);
        dest.writeInt(this.CitySort);
    }

    public CityInfoBean() {
    }

    protected CityInfoBean(Parcel in) {
        this.CityID = in.readInt();
        this.name = in.readString();
        this.ProID = in.readInt();
        this.CitySort = in.readInt();
    }

    public static final Creator<CityInfoBean> CREATOR = new Creator<CityInfoBean>() {
        public CityInfoBean createFromParcel(Parcel source) {
            return new CityInfoBean(source);
        }

        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };
}
