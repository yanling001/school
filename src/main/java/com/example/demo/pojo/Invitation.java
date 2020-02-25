package com.example.demo.pojo;

import java.math.BigDecimal;
import java.util.Date;


public class Invitation {
    private Integer invitationId;

    private Integer uesrId;

    private Date createTime;
    private Date updateTime;
    private Integer invitationStatus;
    private Integer acceptUserId;
    private String content;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Invitation(Integer invitationId, Integer uesrId, Date createTime, Date updateTime, Integer invitationStatus, Integer acceptUserId, BigDecimal price,String content) {
        this.invitationId = invitationId;
        this.uesrId = uesrId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.invitationStatus = invitationStatus;
        this.acceptUserId = acceptUserId;
        this.price=price;
        this.content = content;
    }

    public Invitation() {
        super();
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getUesrId() {
        return uesrId;
    }

    public void setUesrId(Integer uesrId) {
        this.uesrId = uesrId;
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

    public Integer getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(Integer invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public Integer getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}