package com.example.demo.pojo;

public class Image {
    private Integer imageId;

    private Integer commentId;

    private Integer productId;

    private String imgAddress;

    public Image(Integer imageId, Integer commentId, Integer productId, String imgAddress) {
        this.imageId = imageId;
        this.commentId = commentId;
        this.productId = productId;
        this.imgAddress = imgAddress;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress == null ? null : imgAddress.trim();
    }
}