package com.huaqi.zhanxin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "psychology对象")
public class PsychologyBean {
    private Integer userID;
    private Timestamp testTime;
    private Integer testScore;
    private Integer openness;
    private Integer conscientiousness;
    private Integer extraversion;
    private Integer agreeableness;
    private Integer neuroticism;

    public PsychologyBean(Integer userID, Timestamp testTime, Integer testScore, Integer openness, Integer conscientiousness, Integer extraversion, Integer agreeableness, Integer neuroticism) {
        this.userID = userID;
        this.testTime = testTime;
        this.testScore = testScore;
        this.openness = openness;
        this.conscientiousness = conscientiousness;
        this.extraversion = extraversion;
        this.agreeableness = agreeableness;
        this.neuroticism = neuroticism;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Timestamp getTestTime() {
        return testTime;
    }

    public void setTestTime(Timestamp testTime) {
        this.testTime = testTime;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Integer getOpenness() {
        return openness;
    }

    public void setOpenness(Integer openness) {
        this.openness = openness;
    }

    public Integer getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(Integer conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public Integer getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(Integer extraversion) {
        this.extraversion = extraversion;
    }

    public Integer getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(Integer agreeableness) {
        this.agreeableness = agreeableness;
    }

    public Integer getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(Integer neuroticism) {
        this.neuroticism = neuroticism;
    }
}
