package com.example.demo.pojo;

public class Shop {
    private Integer shopId;

    private String category;

    private String shopname;

    private String location;

    private String tel;

    private Integer userId;

    public Shop(Integer shopId, String category, String shopname, String location, String tel, Integer userId) {
        this.shopId = shopId;
        this.category = category;
        this.shopname = shopname;
        this.location = location;
        this.tel = tel;
        this.userId = userId;
    }

    public Shop() {
        super();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}