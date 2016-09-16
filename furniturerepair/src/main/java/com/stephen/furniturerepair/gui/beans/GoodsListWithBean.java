package com.stephen.furniturerepair.gui.beans;

import java.util.List;

/**
 * Created by Stephen on 2016/4/15 0015.
 * Emial: 895745843@qq.com
 */
public class GoodsListWithBean {

    /**
     * type : 1
     * subtype : 1
     * goodsList : [{"name":"康师傅西红柿打卤面","img":"http://7xss7v.com2.z0.glb.qiniucdn.com/Fuj4eHXbBE9Bs6zF21pIKPL_VUGH","price":"42.50","unit":"箱","specification":"400g*12桶/箱","min_sale_amount":"1","brandId":"2","sale_amount":"100","sale_price":"70.00"},{"name":"统一老坛酸菜牛肉面","img":"http://7xss7v.com2.z0.glb.qiniucdn.com/FhVdxPhVdVxCXmtLZRbBf5z2cN3X","price":"55.00","unit":"箱","specification":"400g*10桶/箱","min_sale_amount":"1","brandId":"5","sale_amount":"50","sale_price":"80.00"},{"name":"康师傅红烧牛肉面","img":"http://7xss7v.com2.z0.glb.qiniucdn.com/Fuui18ZAR2wuf7xAFwFfmyIoKmwb","price":"42.50","unit":"箱","specification":"400g*12桶/箱","min_sale_amount":"1","brandId":"2","sale_amount":"200","sale_price":"70.00"},{"name":"康师傅","img":"http://7xss7v.com2.z0.glb.qiniucdn.com/FhVdxPhVdVxCXmtLZRbBf5z2cN3X","price":"43.00","unit":"箱","specification":"400g*12桶/箱","min_sale_amount":"1","brandId":"2","sale_amount":"200","sale_price":"70.00"}]
     */

    private String type;
    private String subtype;
    /**
     * name : 康师傅西红柿打卤面
     * img : http://7xss7v.com2.z0.glb.qiniucdn.com/Fuj4eHXbBE9Bs6zF21pIKPL_VUGH
     * price : 42.50
     * unit : 箱
     * specification : 400g*12桶/箱
     * min_sale_amount : 1
     * brandId : 2
     * sale_amount : 100
     * sale_price : 70.00
     */

    private List<GoodsListBean> goodsList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        private String id;
        private String name;
        private String img;
        private String price;
        private String unit;
        private String specification;
        private String min_sale_amount;
        private String brandId;
        private String sale_amount;
        private String sale_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getMin_sale_amount() {
            return min_sale_amount;
        }

        public void setMin_sale_amount(String min_sale_amount) {
            this.min_sale_amount = min_sale_amount;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getSale_amount() {
            return sale_amount;
        }

        public void setSale_amount(String sale_amount) {
            this.sale_amount = sale_amount;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }
    }
}
