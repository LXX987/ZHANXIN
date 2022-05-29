package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.CreditAppeal;
import com.huaqi.zhanxin.mapper.CreditAppealMapper;
import com.huaqi.zhanxin.service.CreditAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class CreditAppealServiceImpl implements CreditAppealService {
    @Autowired
    CreditAppealMapper creditAppealMapper;
    @Override
    public int insertAppeal(int userID, Timestamp appealTime, String reason, String question, String require, int appealState) {
        return creditAppealMapper.insertAppeal(userID,appealTime, reason, question, require, appealState);
    }

    @Override
    public List<CreditAppeal> getCreditAppeal(int userId) {
        return creditAppealMapper.getCreditAppeal(userId);
    }

    @Override
    public List<JSONObject> getAppealList(String type, Integer pageNum, Integer pageSize) {
        if (Objects.equals(type, "all"))
            return creditAppealMapper.selectAll(pageNum, pageSize);
        else return creditAppealMapper.selectAllByType(type, pageNum, pageSize);
    }

    @Override
    public Integer judgeAppeal(Integer user_id, Timestamp appeal_time, String type, String detail, Double value, Integer state)
    {
        if(Objects.equals(type, "信贷记录有误"))
            type = "Credit_Record";
        else type = "Reputation";
        if(Objects.equals(detail, "犯罪记录有误"))
            detail = "criminal";
        else if(Objects.equals(detail, "献血次数有误"))
            detail = "blood_donation";
        else if(Objects.equals(detail, "志愿服务时长有误"))
            detail = "volunteer";
        else if(Objects.equals(detail, "捐款数有误"))
            detail = "contribution";
        else if(Objects.equals(detail, "话费缴费有误"))
            detail = "phone_cost";
        else if(Objects.equals(detail, "月债务支出、赡养费、生活费除以总收入有误"))
            detail = "DebtRatio";
        else if(Objects.equals(detail, "抵押和房地产数量有误"))
            detail = "NumberRealEstateLoansOrLines";
        else if(Objects.equals(detail, "月收入有误"))
            detail = "MonthlyIncome";
        else if(Objects.equals(detail, "公开贷款（如汽车和抵押贷款）和信用限额（信用卡）数量有误"))
            detail = "NumberOfOpenCreditLinesAndLoans";
        else if(Objects.equals(detail, "信用卡循环利用率有误"))
            detail = "RevolvingUtilizationOfUnsecuredLines";
        else if(Objects.equals(detail, "贷款者过去两年中有30-59天逾期但不糟糕的次数有误"))
            detail = "NumberOfTime30-59DaysPastDueNotWorse";
        else if(Objects.equals(detail, "是否存在超过90天或者更长时间逾期未还的不良行为有误"))
            detail = "NumberOfTime90DaysLate";
        else if(Objects.equals(detail, "90天或以上贷款者逾期未还的次数有误"))
            detail = "NumberOfTime90DaysLate";
        else if(Objects.equals(detail, "贷款者过去两年中有60-89天逾期但不糟糕的次数有误"))
            detail = "NumberOfTime60-89DaysPastDueNotWorse";
        else if(Objects.equals(detail, "家庭成员数量有误"))
            detail = "NumberOfDependents";
        if(state == 1)
        {
            return creditAppealMapper.updateAppealStateToAccepted(user_id, appeal_time, type, detail, value);
        }
        else
            return creditAppealMapper.updateAppealStateToDenied(user_id, appeal_time);
    }
}
