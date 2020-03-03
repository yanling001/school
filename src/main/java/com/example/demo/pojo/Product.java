package com.example.demo.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product  implements Serializable {
    private Integer productId;

    private BigDecimal price;

    private String productName;

    private String productImg;

    private Date createTime;

    private Integer shopId;

    private String productDescrible;

    public Product(Integer productId, BigDecimal price, String productName, String productImg, Date createTime, Integer shopId, String productDescrible) {
        this.productId = productId;
        this.price = price;
        this.productName = productName;
        this.productImg = productImg;
        this.createTime = createTime;
        this.shopId = shopId;
        this.productDescrible = productDescrible;
    }

    public Product() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg == null ? null : productImg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getProductDescrible() {
        return productDescrible;
    }

    public void setProductDescrible(String productDescrible) {
        this.productDescrible = productDescrible == null ? null : productDescrible.trim();
    }
}