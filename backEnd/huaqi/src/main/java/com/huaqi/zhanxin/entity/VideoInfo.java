package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "视频")
public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer videoId;

    private String videoName;

    private Timestamp videoTime;

    private String videoUrl;

    private String videoIntro;

    private String videoCover;

    private String videoType;
}
