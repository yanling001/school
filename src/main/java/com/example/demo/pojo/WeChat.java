package com.example.demo.pojo;

public class WeChat {
    private Integer wechatId;

    private String nickname;

    private String city;

    private String province;

    private String country;

    private String avatarurl;

    private Integer gender;

    private String unionid;

    public WeChat(Integer wechatId, String nickname, String city, String province, String country, String avatarurl, Integer gender, String unionid) {
        this.wechatId = wechatId;
        this.nickname = nickname;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarurl = avatarurl;
        this.gender = gender;
        this.unionid = unionid;
    }

    public WeChat() {
        super();
    }

    public Integer getWechatId() {
        return wechatId;
    }

    public void setWechatId(Integer wechatId) {
        this.wechatId = wechatId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}