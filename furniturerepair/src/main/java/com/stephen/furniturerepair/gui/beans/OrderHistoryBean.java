package com.stephen.furniturerepair.gui.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stephen on 2016/4/11 0011.
 * Emial: 895745843@qq.com
 */
public class OrderHistoryBean implements Serializable {

    /**
     * time : 2016-3-12
     * shopList : [{"name":"康师傅","id":"56789","goodsBuyAmout":"12","goodsOldPrice":"45","img":"http://56789.jpg"}]
     */

    private String time;
    /**
     * name : 康师傅
     * id : 56789
     * goodsBuyAmout : 12
     * goodsOldPrice : 45
     * img : http://56789.jpg
     */

    private List<ShopListBean> shopList;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    public static class ShopListBean implements Serializable {
        private String name;
        private String id;
        private String unit;
        private String goodsBuyAmout;
        private String goodsOldPrice;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public ShopListBean() {

        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public OrderHistoryBean() {

    }
}
