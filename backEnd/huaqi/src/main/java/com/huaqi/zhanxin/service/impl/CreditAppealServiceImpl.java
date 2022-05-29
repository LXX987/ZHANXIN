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
}
