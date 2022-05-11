package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "user资产证明")
public class Asset {
    private Integer userID;
    private String bank;
    private Integer money;
    private Timestamp addTime;
}
