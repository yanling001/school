package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;


@ApiModel(description="跑腿帖子实体对象")
public class Invitation {
    @ApiModelProperty(value="跑腿帖子",name="username")
    private Integer invitationId;
    @ApiModelProperty(value="发帖子的用户id",name="uesrId")
    private Integer uesrId;
    @ApiModelProperty(value="帖子的创建时间",name="createTime")
    private Date createTime;
    @ApiModelProperty(value="帖子信息更新时间",name="updateTime")
    private Date updateTime;
    @ApiModelProperty(value="跑腿帖子的状态：0表示未被接受，1：表示已经被别人接受，2：表示跑腿完成",name="invitationStatus")
    private Integer invitationStatus;
    @ApiModelProperty(value="接帖子的用户id",name="acceptUserId")
    private Integer acceptUserId;
    @ApiModelProperty(value="跑腿帖子的内容",name="content")
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