package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "Psychology_test对象")
public class PsychologyQuestionsBean {
    private Integer testID;
    private String testContent;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private Integer item_score;

    public PsychologyQuestionsBean(Integer testID, String testContent, String option1, String option2, String option3, String option4, String option5, Integer item_score) {
        this.testID = testID;
        this.testContent = testContent;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.item_score = item_score;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public Integer getItem_score() {
        return item_score;
    }

    public void setItem_score(Integer item_score) {
        this.item_score = item_score;
    }

    public Integer getTestID() {
        return testID;
    }

    public void setTestID(Integer testID) {
        this.testID = testID;
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }
}
