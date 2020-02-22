package com.example.demo.pojo;

public class Collect {
    private Integer collectId;

    private Integer productId;

    private Integer userId;

    public Collect(Integer collectId, Integer productId, Integer userId) {
        this.collectId = collectId;
        this.productId = productId;
        this.userId = userId;
    }

    public Collect() {
        super();
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}