package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "安全问题")
public class SecurityQuestion {
    private Integer userID;
    private String teacher;
    private String city;
}
