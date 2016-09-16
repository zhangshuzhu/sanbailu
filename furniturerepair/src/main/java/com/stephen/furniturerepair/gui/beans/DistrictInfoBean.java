package com.stephen.furniturerepair.gui.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShiWeiZong
 * date 2015/10/3015:28
 * email zong4wei@163.com
 */
public class DistrictInfoBean implements Parcelable {


    /**
     * Id : 1
     * DisName : 东城区
     * CityID : 1
     * DisSort : null
     */

    public int Id;
    public String DisName;
    public int CityID;
    public String DisSort;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.DisName);
        dest.writeInt(this.CityID);
        dest.writeString(this.DisSort);
    }

    public DistrictInfoBean() {
    }

    protected DistrictInfoBean(Parcel in) {
        this.Id = in.readInt();
        this.DisName = in.readString();
        this.CityID = in.readInt();
        this.DisSort = in.readString();
    }

    public static final Creator<DistrictInfoBean> CREATOR = new Creator<DistrictInfoBean>() {
        public DistrictInfoBean createFromParcel(Parcel source) {
            return new DistrictInfoBean(source);
        }

        public DistrictInfoBean[] newArray(int size) {
            return new DistrictInfoBean[size];
        }
    };
}
