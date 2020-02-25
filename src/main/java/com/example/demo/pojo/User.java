package com.example.demo.pojo;

import java.util.Date;

public class User {
    private Integer userId;

    private String nickname;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private String openidWeb;

    private String city;

    private String province;

    private String country;

    private String avatarurl;

    private String openidAnr;
    private  Integer role;



    public User(Integer userId, String nickname, String email, String phone, Date createTime, Date updateTime, String openidWeb, String city, String province, String country, String avatarurl, String openidAnr, Integer role) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.openidWeb = openidWeb;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarurl = avatarurl;
        this.openidAnr = openidAnr;
        this.role=role;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public String getOpenidWeb() {
        return openidWeb;
    }

    public void setOpenidWeb(String openidWeb) {
        this.openidWeb = openidWeb == null ? null : openidWeb.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getOpenidAnr() {
        return openidAnr;
    }

    public void setOpenidAnr(String openidAnr) {
        this.openidAnr = openidAnr == null ? null : openidAnr.trim();
    }
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}