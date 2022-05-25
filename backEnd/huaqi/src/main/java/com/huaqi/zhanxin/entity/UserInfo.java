package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "user基本资料")
public class UserInfo {
    private Integer occupation;
    private Float annualIncome;
    private Integer workingYears;
    private Boolean authentication;
    private Integer userID;
    private Integer IDtype;
    private String IDcard;

    private String phone;

    public UserInfo(Integer occupation, Float annualIncome, Integer workingYears, Boolean authentication, Integer userID, Integer IDtype, String IDcard, String phone) {
        this.occupation = occupation;
        this.annualIncome = annualIncome;
        this.workingYears = workingYears;
        this.authentication = authentication;
        this.userID = userID;
        this.IDtype = IDtype;
        this.IDcard = IDcard;
        this.phone = phone;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public Float getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Float annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Integer getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Integer workingYears) {
        this.workingYears = workingYears;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getIDtype() {
        return IDtype;
    }

    public void setIDtype(Integer IDtype) {
        this.IDtype = IDtype;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }
}
