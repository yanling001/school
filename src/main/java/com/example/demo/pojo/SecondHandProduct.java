package com.example.demo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class SecondHandProduct {
    private Integer productId;

    private String category;

    private String name;

    private Integer userId;

    private Date createTime;

    private String videoAddress;

    private BigDecimal price;

    private Integer status;

    private String newDegree;

    private String location;

    private String content;

    public SecondHandProduct(Integer productId, String category, String name, Integer userId, Date createTime, String videoAddress, BigDecimal price, Integer status, String newDegree, String location, String content) {
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.userId = userId;
        this.createTime = createTime;
        this.videoAddress = videoAddress;
        this.price = price;
        this.status = status;
        this.newDegree = newDegree;
        this.location = location;
        this.content = content;
    }

    public SecondHandProduct() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress == null ? null : videoAddress.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree == null ? null : newDegree.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}