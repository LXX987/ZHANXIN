package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "user信用分数")
public class Credit {
    private Integer userID;
    private Integer totalScore;
    private Integer identityScore;
    private Integer assetScore;
    private Integer creditScore;
    private Integer behaviorScore;
    private Integer socialScore;


    public Credit(Integer userID, Integer totalScore, Integer identityScore, Integer assetScore, Integer creditScore, Integer behaviorScore, Integer socialScore) {
        this.userID = userID;
        this.totalScore = totalScore;
        this.identityScore = identityScore;
        this.assetScore = assetScore;
        this.creditScore = creditScore;
        this.behaviorScore = behaviorScore;
        this.socialScore = socialScore;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getIdentityScore() {
        return identityScore;
    }

    public void setIdentityScore(Integer identityScore) {
        this.identityScore = identityScore;
    }

    public Integer getAssetScore() {
        return assetScore;
    }

    public void setAssetScore(Integer assetScore) {
        this.assetScore = assetScore;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getBehaviorScore() {
        return behaviorScore;
    }

    public void setBehaviorScore(Integer behaviorScore) {
        this.behaviorScore = behaviorScore;
    }

    public Integer getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(Integer socialScore) {
        this.socialScore = socialScore;
    }
}
