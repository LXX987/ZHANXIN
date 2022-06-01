package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "user历史信用分数")
public class HistoryScore {
    private Integer userID;
    private Integer totalScore;
    private Timestamp historyTime;
    private Integer identityScore;
    private Integer assetScore;
    private Integer creditScore;
    private Integer behaviorScore;
    private Integer socialScore;

}
