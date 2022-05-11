package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.Credit;

public interface CreditService {
    public Credit selectScore(int userID);

    int insertScore(int userID,int a,int b,int c,int d,int e,int f);

    int updateIdentityScore(int indentityScore);

    int updateTotalScore(int totalScore);

    int updateAssetScore(int assetScore);

    int updateCreditScore(int credit_score);

    int updateBehaviorScore(int behavior_score);

    int updateSocialScore(int social_score);
}
