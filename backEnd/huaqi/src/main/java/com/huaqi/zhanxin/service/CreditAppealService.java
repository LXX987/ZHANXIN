package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.CreditAppeal;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CreditAppealService {
    int insertAppeal(int userID, Timestamp appealTime, String reason, String question, String require, int appealState);
    List<CreditAppeal> getCreditAppeal(int userId);
}
