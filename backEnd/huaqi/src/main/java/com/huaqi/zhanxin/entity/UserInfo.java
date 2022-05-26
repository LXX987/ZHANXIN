package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "user基本资料")
public class UserInfo {
    private Integer occupation;
    private Float annualIncome;
    private Integer workingYears;
    private Boolean authentication;
    private Integer userID;
    private Integer IDtype;
    private String IDcard;
    private String phone;
}
