package com.example.demo.pojo;

public class CollectShop {
    private Integer collectshopId;

    private Integer shopId;

    private Integer userId;

    public CollectShop(Integer collectshopId, Integer shopId, Integer userId) {
        this.collectshopId = collectshopId;
        this.shopId = shopId;
        this.userId = userId;
    }

    public CollectShop() {
        super();
    }

    public Integer getCollectshopId() {
        return collectshopId;
    }

    public void setCollectshopId(Integer collectshopId) {
        this.collectshopId = collectshopId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}