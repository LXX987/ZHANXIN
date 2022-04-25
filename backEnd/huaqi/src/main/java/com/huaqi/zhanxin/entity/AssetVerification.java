package com.huaqi.zhanxin.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "审核信息")
public class AssetVerification implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer certicficateId;

    private Integer state;

    private String certificate;

    private String fileName;
}
