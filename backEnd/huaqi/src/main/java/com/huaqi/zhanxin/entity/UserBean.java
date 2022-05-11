package com.huaqi.zhanxin.entity;

import java.sql.Timestamp;
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
    private Timestamp userRegisterTime;
    private String userAvatar;
}
