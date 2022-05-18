package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "登录异常")
public class LoginException {
    private Integer userID;
    private Timestamp exceptionTime;
}
