package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopProductVo  implements Serializable {
    private Integer productId;

    private BigDecimal price;

    private String productName;

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
        this.productName = productName;
    }

}
