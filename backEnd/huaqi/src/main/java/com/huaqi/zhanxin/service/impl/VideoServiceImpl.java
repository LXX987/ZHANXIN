package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.mapper.VideoMapper;
import com.huaqi.zhanxin.service.VideoService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Service
public class VideoServiceImpl implements VideoService {

    private String UPLOAD_PATH = "D:/opt/JavaEE/images/";

    @Resource
    VideoMapper videoMapper;

    @Override
    public String uploadVideo(MultipartFile file, HttpServletRequest request)
    {
        String url;
        try {
            url = UploadUtil.upload(file, UPLOAD_PATH, request);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        return url;
    }

}
