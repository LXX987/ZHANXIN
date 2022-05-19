package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "信用申诉")
public class CreditAppeal {
    private Integer userID;
    private Timestamp appealTime;
    private String reason;
    private String question;
    private String require;
    private Integer appealState;
}
