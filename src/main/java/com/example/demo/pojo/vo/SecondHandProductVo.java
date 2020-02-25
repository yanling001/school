package com.example.demo.pojo.vo;
import com.example.demo.pojo.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SecondHandProductVo {
    /*
     “tel”：xxx	//电话号码
     “qq”：xxx	//qq号
     “newDegree”：8成新  //新旧程度
     “location”: 陕西西安 //交易地点
     “price”: 6666	//交易价格
    */


    private Integer productId;
    private String title;//对应商品的name
    private String name;//用户的name
    private  String qq;
    private String tel;
    private Date createTime;
    private BigDecimal price;
    private String videoAddress;
    private String content;
    private  Integer status;
    private String newDegree;
    private String location;
    private List<String> images;
    private  List<ProductCommentVo> comments;//二手商品评论

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<ProductCommentVo> getComments() {
        return comments;
    }

    public void setComments(List<ProductCommentVo> comments) {
        this.comments = comments;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

}
