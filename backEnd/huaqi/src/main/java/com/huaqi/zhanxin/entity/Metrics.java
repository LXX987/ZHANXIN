package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "视频")
public class Metrics implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer logId;

    private Integer cpuCore;

    private String cpuUsage;

    private String memorySize;

    private String memoryUsage;

    private Integer concurrentUser;

    private Timestamp logTime;
}
