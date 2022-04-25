package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "好友关系")
public class SocialRelationship implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer friendId;

    private Double score;
}
