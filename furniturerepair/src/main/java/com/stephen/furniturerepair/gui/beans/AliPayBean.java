package com.stephen.furniturerepair.gui.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephen on 2016/4/29 0029.
 * Emial: 895745843@qq.com
 */
public class AliPayBean implements Parcelable {

    /**
     * goodsId : 56678
     * goodsBuyAmout : 2
     * goodsOldPrice : 40
     */

    private String goodsId;
    private String goodsBuyAmout;
    private String goodsOldPrice;

    public AliPayBean(String goodsId, String goodsBuyAmout, String goodsOldPrice) {
        this.goodsId = goodsId;
        this.goodsBuyAmout = goodsBuyAmout;
        this.goodsOldPrice = goodsOldPrice;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsBuyAmout() {
        return goodsBuyAmout;
    }

    public void setGoodsBuyAmout(String goodsBuyAmout) {
        this.goodsBuyAmout = goodsBuyAmout;
    }

    public String getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(String goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
    }

    @Override
    public String toString() {
        return "AliPayBean{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsBuyAmout='" + goodsBuyAmout + '\'' +
                ", goodsOldPrice='" + goodsOldPrice + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goodsId);
        dest.writeString(this.goodsBuyAmout);
        dest.writeString(this.goodsOldPrice);
    }

    protected AliPayBean(Parcel in) {
        this.goodsId = in.readString();
        this.goodsBuyAmout = in.readString();
        this.goodsOldPrice = in.readString();
    }

    public static final Creator<AliPayBean> CREATOR = new Creator<AliPayBean>() {
        @Override
        public AliPayBean createFromParcel(Parcel source) {
            return new AliPayBean(source);
        }

        @Override
        public AliPayBean[] newArray(int size) {
            return new AliPayBean[size];
        }
    };
}
