package com.stephen.furniturerepair.gui.beans;

/**
 * Created by Stephen on 2016/4/11 0011.
 * Emial: 895745843@qq.com
 */
public class OrderBean{

    /**
     * img : http://7xnnlv.com1.z0.glb.clouddn.com/97e03d11-c598-4904-a672
     * id : 456789
     * name : 方便面
     * goodsOldPrice : 23
     * unit : 袋
     * specification : 100g*13袋/箱
     * min_sale_amount : 30
     * brandName : 康师傅
     * brandId : 4356789
     * goodsBuyAmount : 30
     */

    private String img;
    private String id;
    private String name;
    private String goodsOldPrice;
    private String unit;
    private String specification;
    private int min_sale_amount;
    private String brandName;
    private String brandId;

    public OrderBean(String img, String id, String name, String goodsOldPrice, String unit, String specification, int min_sale_amount, String brandName, String brandId, int goodsBuyAmout) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.goodsOldPrice = goodsOldPrice;
        this.unit = unit;
        this.specification = specification;
        this.min_sale_amount = min_sale_amount;
        this.brandName = brandName;
        this.brandId = brandId;
        this.goodsBuyAmout = goodsBuyAmout;
    }

    public int getGoodsBuyAmout() {
        return goodsBuyAmout;
    }

    public void setGoodsBuyAmout(int goodsBuyAmout) {
        this.goodsBuyAmout = goodsBuyAmout;
    }

    private int goodsBuyAmout;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

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

    public String getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(String goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
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

    public int getMin_sale_amount() {
        return min_sale_amount;
    }

    public void setMin_sale_amount(int min_sale_amount) {
        this.min_sale_amount = min_sale_amount;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

}
