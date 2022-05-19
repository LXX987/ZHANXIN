package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.mapper.CreditMapper;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditMapper creditMapper;
    // 获取用户信用分数
    @Override
    public Credit selectScore(int userID){return creditMapper.selectScore(userID);}

    @Override
    public int insertScore(int userID,int a,int b,int c, int d, int e, int f) {
        return creditMapper.insertInfo(userID,0,0,0,0,0,0);
    }

    @Override
    public int updateIdentityScore(int indentityScore) {
        return creditMapper.updateIdentityScore(indentityScore);
    }

    @Override
    public int updateTotalScore(int totalScore) {
        return creditMapper.updateTotalScore(totalScore);
    }

    @Override
    public int updateAssetScore(int assetScore) {
        return creditMapper.updateAssetScore(assetScore);
    }

    @Override
    public int updateCreditScore(int credit_score) {
        return creditMapper.updateCreditScore(credit_score);
    }

    @Override
    public int updateBehaviorScore(int behavior_score) {
        return creditMapper.updateBehaviorScore(behavior_score);
    }

    @Override
    public int updateSocialScore(int social_score) {
        return creditMapper.updateSocialScore(social_score);
    }
}
