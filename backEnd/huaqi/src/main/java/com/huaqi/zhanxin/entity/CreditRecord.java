package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "user信贷记录")
public class CreditRecord {
    private Integer userID;
    private Double debtRatio;
    private Integer numberRealEstateLoansOrLines;
    private Integer numberOfOpenCreditLinesAndLoans;
    private Integer numberOfTime30To59DaysPastDueNotWorse;
    private Double revolvingUtilizationOfUnsecuredLines;
    private Integer seriousDlqin2yrs;
    private Double monthlyIncome;
    private Integer numberOfTime90DaysLate;
    private Integer numberOfDependents;
    private Integer numberOfTime60To89DaysPastDueNotWorse;

    public CreditRecord(Integer userID, Double debtRatio, Integer numberRealEstateLoansOrLines, Integer numberOfOpenCreditLinesAndLoans, Integer numberOfTime30To59DaysPastDueNotWorse, Double revolvingUtilizationOfUnsecuredLines, Integer seriousDlqin2yrs, Double monthlyIncome, Integer numberOfTime90DaysLate, Integer numberOfDependents, Integer numberOfTime60To89DaysPastDueNotWorse) {
        this.userID = userID;
        this.debtRatio = debtRatio;
        this.numberRealEstateLoansOrLines = numberRealEstateLoansOrLines;
        this.numberOfOpenCreditLinesAndLoans = numberOfOpenCreditLinesAndLoans;
        this.numberOfTime30To59DaysPastDueNotWorse = numberOfTime30To59DaysPastDueNotWorse;
        this.revolvingUtilizationOfUnsecuredLines = revolvingUtilizationOfUnsecuredLines;
        this.seriousDlqin2yrs = seriousDlqin2yrs;
        this.monthlyIncome = monthlyIncome;
        this.numberOfTime90DaysLate = numberOfTime90DaysLate;
        this.numberOfDependents = numberOfDependents;
        this.numberOfTime60To89DaysPastDueNotWorse = numberOfTime60To89DaysPastDueNotWorse;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Double getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(Double debtRatio) {
        this.debtRatio = debtRatio;
    }

    public Integer getNumberRealEstateLoansOrLines() {
        return numberRealEstateLoansOrLines;
    }

    public void setNumberRealEstateLoansOrLines(Integer numberRealEstateLoansOrLines) {
        this.numberRealEstateLoansOrLines = numberRealEstateLoansOrLines;
    }

    public Integer getNumberOfOpenCreditLinesAndLoans() {
        return numberOfOpenCreditLinesAndLoans;
    }

    public void setNumberOfOpenCreditLinesAndLoans(Integer numberOfOpenCreditLinesAndLoans) {
        this.numberOfOpenCreditLinesAndLoans = numberOfOpenCreditLinesAndLoans;
    }

    public Integer getNumberOfTime30To59DaysPastDueNotWorse() {
        return numberOfTime30To59DaysPastDueNotWorse;
    }

    public void setNumberOfTime30To59DaysPastDueNotWorse(Integer numberOfTime30To59DaysPastDueNotWorse) {
        this.numberOfTime30To59DaysPastDueNotWorse = numberOfTime30To59DaysPastDueNotWorse;
    }

    public Double getRevolvingUtilizationOfUnsecuredLines() {
        return revolvingUtilizationOfUnsecuredLines;
    }

    public void setRevolvingUtilizationOfUnsecuredLines(Double revolvingUtilizationOfUnsecuredLines) {
        this.revolvingUtilizationOfUnsecuredLines = revolvingUtilizationOfUnsecuredLines;
    }

    public Integer getSeriousDlqin2yrs() {
        return seriousDlqin2yrs;
    }

    public void setSeriousDlqin2yrs(Integer seriousDlqin2yrs) {
        this.seriousDlqin2yrs = seriousDlqin2yrs;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getNumberOfTime90DaysLate() {
        return numberOfTime90DaysLate;
    }

    public void setNumberOfTime90DaysLate(Integer numberOfTime90DaysLate) {
        this.numberOfTime90DaysLate = numberOfTime90DaysLate;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public Integer getNumberOfTime60To89DaysPastDueNotWorse() {
        return numberOfTime60To89DaysPastDueNotWorse;
    }

    public void setNumberOfTime60To89DaysPastDueNotWorse(Integer numberOfTime60To89DaysPastDueNotWorse) {
        this.numberOfTime60To89DaysPastDueNotWorse = numberOfTime60To89DaysPastDueNotWorse;
    }
}
