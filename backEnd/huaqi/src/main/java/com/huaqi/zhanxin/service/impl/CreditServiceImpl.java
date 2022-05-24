package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.mapper.CreditMapper;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int updateIdentityScore(int indentityScore,int userID) {
        return creditMapper.updateIdentityScore(indentityScore,userID);
    }

    @Override
    public int updateTotalScore(int totalScore,int userID) {
        return creditMapper.updateTotalScore(totalScore,userID);
    }

    @Override
    public int updateAssetScore(int assetScore,int userID) {
        return creditMapper.updateAssetScore(assetScore,userID);
    }

    @Override
    public int updateCreditScore(int credit_score,int userID) {
        return creditMapper.updateCreditScore(credit_score,userID);
    }

    @Override
    public int updateBehaviorScore(int behavior_score,int userID) {
        return creditMapper.updateBehaviorScore(behavior_score,userID);
    }

    @Override
    public int updateSocialScore(int social_score,int userID) {
        return creditMapper.updateSocialScore(social_score,userID);
    }

    @Override
    public List<Credit> getAllCredit() {
        return creditMapper.selectAllCredit();
    }
}
