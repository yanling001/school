package com.example.demo.pojo;

import java.util.Date;

public class ProductComment {
    private Integer commentId;

    private Integer productId;

    private Integer userId;

    private Date createTime;

    private Integer star;

    private String content;

    public ProductComment(Integer commentId, Integer productId, Integer userId, Date createTime, Integer star, String content) {
        this.commentId = commentId;
        this.productId = productId;
        this.userId = userId;
        this.createTime = createTime;
        this.star = star;
        this.content = content;
    }

    public ProductComment() {
        super();
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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}