package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.CreditAppeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

public interface CreditAppealService {
    int insertAppeal(int userID, Timestamp appealTime, String reason, String question, String require, int appealState);

    List<CreditAppeal> getCreditAppeal(int userId);

    List<JSONObject> getAppealList(String type, Integer pageNum, Integer pageSize);

    Integer judgeAppeal(Integer user_id, Timestamp appeal_time, String type, String detail, Double value, Integer state);
}
