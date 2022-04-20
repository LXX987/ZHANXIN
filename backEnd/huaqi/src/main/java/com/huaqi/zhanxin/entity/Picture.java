package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "图片基本资料")
public class Picture {
    private Integer userID;
    private Integer picID;
    private Integer state;
    private String picType;
    private String fileName;

    public Picture(Integer userID, Integer picID, Integer state, String picType, String fileName) {
        this.userID = userID;
        this.picID = picID;
        this.state = state;
        this.picType = picType;
        this.fileName = fileName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getPicID() {
        return picID;
    }

    public void setPicID(Integer picID) {
        this.picID = picID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
