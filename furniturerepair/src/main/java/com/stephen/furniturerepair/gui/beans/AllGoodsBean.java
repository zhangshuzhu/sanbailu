package com.stephen.furniturerepair.gui.beans;

import java.util.List;

/**
 * Created by Stephen on 2016/4/4 0004.
 * Emial: 895745843@qq.com
 */
public class AllGoodsBean {

    /**
     * id : 1
     * name : 食品
     * order : 1
     * category : [{"id":"1","name":"方便面","order":"1"},{"id":"2","name":"饼干","order":"2"}]
     */

    private String id;
    private String name;
    private String order;
    /**
     * id : 1
     * name : 方便面
     * order : 1
     */

    private List<CategoryBean> category;

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {
        private String id;
        private String name;
        private String order;

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

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}

