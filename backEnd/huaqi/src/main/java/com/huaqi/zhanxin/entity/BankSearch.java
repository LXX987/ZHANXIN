package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "bank查询")
public class BankSearch {
    private Integer userID;
    private String userName;
    private String IDcard;
}
