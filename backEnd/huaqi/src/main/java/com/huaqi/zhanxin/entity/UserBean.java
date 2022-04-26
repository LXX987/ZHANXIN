package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "user对象")
public class UserBean {
    private Integer userID;
    private String userPwd;
    private String userName;
    private String userEmail;
    private Integer userType;
    private LocalDateTime userRegisterTime;
    private String userAvatar;

//    public UserBean(Integer userID, String userPwd, String userName, String userEmail) {
//        this.userID = userID;
//        this.userPwd = userPwd;
//        this.userName = userName;
//        this.userEmail = userEmail;
//    }
//
//    public Integer getUserID() {
//        return userID;
//    }
//
//    public void setUserID(Integer userID) {
//        this.userID = userID;
//    }
//
//    public String getUserPwd() {
//        return userPwd;
//    }
//
//    public void setUserPwd(String userPwd) {
//        this.userPwd = userPwd;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
}
