package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<UserBean> userList();
    public UserBean login(String userEmail);
    public UserBean selectName(int userID);
    public UserInfo getInfo(int userID);
    public int updateName(int userID,String userName,String userEmail);
    public int updateInfo(int userID,int occupation,float annualIncome,int workingYears);
    int insertInfo(int occupation,float annualIncome,int workingYears,int userID);
    public int register(String userEmail, String userPwd);
    public int updateAuthentication(int userID,boolean authentication,int IDtype,String IDcard);
    public HonestyProof selectHonestyProof(int userID);
    public Asset selectAsset(int userID);
    public CreditRecord selectCreditRecord(int userID);
}
