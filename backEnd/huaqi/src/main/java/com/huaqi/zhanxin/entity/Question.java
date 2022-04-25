package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "视频题目")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer questionId;

    private Integer videoId;

    private String questionText;

    private String questionAnswer;
}
