package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    public List<UserBean> userList();
    public UserBean login(String userEmail);
    public UserBean selectName(int userID);
    public UserInfo getInfo(int userID);
    public int updateName(int userID,String userName,String userEmail);
    public int updateInfo(int userID,int occupation,float annualIncome,int workingYears);
    int insertInfo(int occupation,float annualIncome,int workingYears,int userID,String phone);
    public int register(String userEmail, String userPwd, int userType,LocalDateTime userRegisterTime);
    public int updateAuthentication(int userID,boolean authentication,int IDtype,String IDcard);
    public HonestyProof selectHonestyProof(int userID);
    public Asset selectAsset(int userID);
    public CreditRecord selectCreditRecord(int userID);
    int updatePwd(String userPwd, String userEmail);
    int changePwd(String userPwd, int userID);
    String updateAvatar(int userID, MultipartFile file, HttpServletRequest request);
    int insertException(int userID, Timestamp exceptionTime);
    int insertSecurityQuestion(int userID, String teacher, String city);
    SecurityQuestion selectSecurityQuestion(int userID);
}
