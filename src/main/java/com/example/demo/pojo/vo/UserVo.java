package com.example.demo.pojo.vo;

import com.example.demo.pojo.Invitation;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserVo {
    private Integer userId;


    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private String nickname;
    private String city;

    private String province;

    private String country;
    private Integer gender;
    private String avatarurl;

    //已经接的帖子
    List<Invitation> accept;
    //已发贴子
    List<Invitation> publish;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }


    public List<Invitation> getAccept() {
        return accept;
    }

    public void setAccept(List<Invitation> accept) {
        this.accept = accept;
    }

    public List<Invitation> getPublish() {
        return publish;
    }

    public void setPublish(List<Invitation> publish) {
        this.publish = publish;
    }
}
