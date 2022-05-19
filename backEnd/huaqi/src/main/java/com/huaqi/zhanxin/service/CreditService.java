package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.Credit;

public interface CreditService {
    public Credit selectScore(int userID);

    int insertScore(int userID,int a,int b,int c,int d,int e,int f);

    int updateIdentityScore(int indentityScore,int userID);

    int updateTotalScore(int totalScore,int userID);

    int updateAssetScore(int assetScore,int userID);

    int updateCreditScore(int credit_score,int userID);

    int updateBehaviorScore(int behavior_score,int userID);

    int updateSocialScore(int social_score,int userID);
}
