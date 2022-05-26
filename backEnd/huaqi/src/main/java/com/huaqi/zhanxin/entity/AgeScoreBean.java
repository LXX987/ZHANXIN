package com.huaqi.zhanxin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "年龄分数情况")
public class AgeScoreBean {
    private Integer userId;
    private Integer score;
    private Integer age;
    private String IDcard;

    public AgeScoreBean(Integer userId, Integer score, String IDcard) {
        this.userId = userId;
        this.score = score;
        this.age = 0;
        this.IDcard = IDcard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }
}
