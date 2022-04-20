package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "user资产证明")
public class Asset {
    private Integer userID;
    private Integer house;
    private Integer car;

    public Asset(Integer userID, Integer house, Integer car) {
        this.userID = userID;
        this.house = house;
        this.car = car;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }
}
