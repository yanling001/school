package com.example.demo.pojo;

public class InvitationCollect {
    private Integer invitationCollectId;

    private Integer invitationId;

    private Integer userId;

    public InvitationCollect(Integer invitationCollectId, Integer invitationId, Integer userId) {
        this.invitationCollectId = invitationCollectId;
        this.invitationId = invitationId;
        this.userId = userId;
    }

    public InvitationCollect() {
        super();
    }

    public Integer getInvitationCollectId() {
        return invitationCollectId;
    }

    public void setInvitationCollectId(Integer invitationCollectId) {
        this.invitationCollectId = invitationCollectId;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}