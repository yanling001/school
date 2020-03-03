package com.example.demo.pojo;

public class Image {
    private Integer imageId;

    private String imgAddress;

    private Integer productId;

    private Integer commentId;

    private Integer shopId;

    public Image(Integer imageId, String imgAddress, Integer productId, Integer commentId, Integer shopId) {
        this.imageId = imageId;
        this.imgAddress = imgAddress;
        this.productId = productId;
        this.commentId = commentId;
        this.shopId = shopId;
    }

    public Image() {
        super();
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress == null ? null : imgAddress.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}