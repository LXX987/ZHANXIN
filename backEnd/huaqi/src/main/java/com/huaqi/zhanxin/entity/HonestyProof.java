package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "user信誉证明")
public class HonestyProof {
    private Integer userID;
    private Integer bloodDonation;
    private Double volunteer;
    private Double contribution;
    private Boolean criminal;
    private Integer phoneCost;

//    public HonestyProof(Integer userID, Integer bloodDonation, Double volunteer, Double contribution, Boolean criminal) {
//        this.userID = userID;
//        this.bloodDonation = bloodDonation;
//        this.volunteer = volunteer;
//        this.contribution = contribution;
//        this.criminal = criminal;
//    }
//
//    public Integer getUserID() {
//        return userID;
//    }
//
//    public void setUserID(Integer userID) {
//        this.userID = userID;
//    }
//
//    public Integer getBloodDonation() {
//        return bloodDonation;
//    }
//
//    public void setBloodDonation(Integer bloodDonation) {
//        this.bloodDonation = bloodDonation;
//    }
//
//    public Double getVolunteer() {
//        return volunteer;
//    }
//
//    public void setVolunteer(Double volunteer) {
//        this.volunteer = volunteer;
//    }
//
//    public Double getContribution() {
//        return contribution;
//    }
//
//    public void setContribution(Double contribution) {
//        this.contribution = contribution;
//    }
//
//    public Boolean getCriminal() {
//        return criminal;
//    }
//
//    public void setCriminal(Boolean criminal) {
//        this.criminal = criminal;
//    }
}
