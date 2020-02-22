package com.example.demo.pojo;

public class OrderToProduct {
    private Integer id;

    private Integer orderId;

    private Integer productid;

    public OrderToProduct(Integer id, Integer orderId, Integer productid) {
        this.id = id;
        this.orderId = orderId;
        this.productid = productid;
    }

    public OrderToProduct() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}