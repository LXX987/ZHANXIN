package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "user邀请码")
public class InviteCode {
    private Integer userID;
    private String inviteCode;
    private String invitedCode;
    private Timestamp submitTime;

    public InviteCode(Integer userID, String inviteCode,  Timestamp submitTime,String invitedCode) {
        this.userID = userID;
        this.inviteCode = inviteCode;
        this.invitedCode = invitedCode;
        this.submitTime = submitTime;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }
}
