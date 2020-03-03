package com.example.demo.pojo.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class InvitationVo implements Serializable {
    private  Integer id;
    private String person;
    private String head;
    private Integer uesrId;
    private Date time;
    private Integer invitationStatus;
    private String content;
    private BigDecimal price;
    public InvitationVo(){
        super();
    }
    public InvitationVo(Integer id, String person, String head, Integer uesrId, Date time, Integer invitationStatus, String content, BigDecimal price) {
        this.id = id;
        this.person = person;
        this.head = head;
        this.uesrId = uesrId;
        this.time = time;
        this.invitationStatus = invitationStatus;
        this.content = content;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public Integer getUesrId() {
        return uesrId;
    }

    public void setUesrId(Integer uesrId) {
        this.uesrId = uesrId;
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
