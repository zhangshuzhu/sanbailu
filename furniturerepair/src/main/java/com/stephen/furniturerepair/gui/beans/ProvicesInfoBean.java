package com.stephen.furniturerepair.gui.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShiWeiZong
 * date 2015/10/3015:25
 * email zong4wei@163.com
 */
public class ProvicesInfoBean implements Parcelable {


    /**
     * ProID : 1
     * name : 北京市
     * ProSort : 1
     * ProRemark : 直辖市
     */

    public int ProID;
    public String name;
    public int ProSort;
    public String ProRemark;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProID);
        dest.writeString(this.name);
        dest.writeInt(this.ProSort);
        dest.writeString(this.ProRemark);
    }

    public ProvicesInfoBean() {
    }

    protected ProvicesInfoBean(Parcel in) {
        this.ProID = in.readInt();
        this.name = in.readString();
        this.ProSort = in.readInt();
        this.ProRemark = in.readString();
    }

    public static final Creator<ProvicesInfoBean> CREATOR = new Creator<ProvicesInfoBean>() {
        public ProvicesInfoBean createFromParcel(Parcel source) {
            return new ProvicesInfoBean(source);
        }

        public ProvicesInfoBean[] newArray(int size) {
            return new ProvicesInfoBean[size];
        }
    };
}
