package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Value("${path.picture-upload-path}")
    private String PIC_UPLOAD_PATH;

    @Override
    public List<UserBean> userList() {
        return userMapper.userList();
    }

    @Override
    public UserBean login(String userEmail) {
        return userMapper.loginUser(userEmail);
    }

    @Override
    public UserBean selectName(int userID) {
        return userMapper.selectName(userID);
    }

    @Override
    public UserInfo getInfo(int userID) {
        return userMapper.getInfo(userID);
    }

    @Override
    public int updateName(int userID, String userName,String userEmail) {
        return userMapper.updateName(userID,userName,userEmail);
    }

    @Override
    public int updateInfo(int userID, int occupation, float annualIncome, int workingYears) {
        return userMapper.updateInfo(userID,occupation,annualIncome,workingYears);
    }

    @Override
    public int insertInfo(int occupation, float annualIncome, int workingYears, int userID) {
        return userMapper.insertInfo(occupation,annualIncome,workingYears,userID);
    }

    // 返回数据库注册结果
    @Override
    public int register(String userEmail, String userPwd, int userType,LocalDateTime userRegisterTime){return userMapper.registerUser(userEmail,userPwd,userType,userRegisterTime);}

    // 返回数据库实名结果
    @Override
    public int updateAuthentication(int userID, boolean authentication, int IDtype, String IDcard) {
        return userMapper.updateAuthentication(userID,authentication,IDtype,IDcard);
    }

    // 返回查询到的信誉证明信息
    @Override
    public HonestyProof selectHonestyProof(int userID) {
        return userMapper.selectHonestyProof(userID);
    }

    @Override
    public Asset selectAsset(int userID) {
        return userMapper.selectAsset(userID);
    }

    @Override
    public CreditRecord selectCreditRecord(int userID) {
        return userMapper.selectCreditRecord(userID);
    }

    @Override
    public int updatePwd(String userPwd, String userEmail) {
        return userMapper.updatePwd(userPwd,userEmail);
    }

    @Override
    public int changePwd(String userPwd, int userID) {
        return userMapper.changePwd(userPwd, userID);
    }

    @Override
    public String updateAvatar(int userID, MultipartFile file, HttpServletRequest request) {

        String pic_url;
        try {
            pic_url = UploadUtil.upload(file, PIC_UPLOAD_PATH, request);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        if(pic_url == null)
            return "-2";
        userMapper.updateAvatar(pic_url,userID);
        return pic_url;
    }

    @Override
    public int insertException(int userID, Timestamp exceptionTime) {
        return userMapper.insertException(userID,exceptionTime);
    }

    @Override
    public int insertSecurityQuestion(int userID, String teacher, String city) {
        return userMapper.insertSecurityQuestion(userID,teacher,city);
    }

    @Override
    public SecurityQuestion selectSecurityQuestion(int userID) {
        return userMapper.selectSecurityQuestion(userID);
    }


}
