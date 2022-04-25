package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {

    String uploadVideo(MultipartFile file, String title, String intro, HttpServletRequest request);

    List<JSONObject> getVideoList();

    Integer deleteVideo(Integer id);
}
