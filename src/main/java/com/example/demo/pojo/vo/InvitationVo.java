package com.example.demo.pojo.vo;

import com.example.demo.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
@ApiModel(description="帖子信息InvitationVo")
public class InvitationVo {
    @ApiModelProperty(value="帖子发出者的用户信息")
    private User user;
    @ApiModelProperty(value="跑腿帖子")
    private Integer invitationId;
    @ApiModelProperty(value="发帖子的用户id")
    private Integer uesrId;
    @ApiModelProperty(value="帖子的创建时间")
    private Date createTime;
    @ApiModelProperty(value="帖子信息更新时间")
    private Date updateTime;
    @ApiModelProperty(value="跑腿帖子的状态：0表示未被接受，1：表示已经被别人接受，2：表示跑腿完成")
    private Integer invitationStatus;
    @ApiModelProperty(value="跑腿帖子的内容")
    private String content;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
