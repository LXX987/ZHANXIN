package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PictureService {

    public String uploadFile(int userID, MultipartFile file, String picType, HttpServletRequest request) throws Exception;
    int getCurNumber();
    Picture getHouse(int userId);
    List<Picture> getCrime(int userId);
    List<Picture> getBlood(int userId);
    List<Picture> getVolunteer(int userId);
    List<Picture> getDonation(int userId);
    List<Picture> getPhoneCost(int userId);
    List<Picture> pictureList();
}
