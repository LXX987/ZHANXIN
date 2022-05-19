package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface VideoService {

    String uploadVideo(MultipartFile video, MultipartFile cover, String title, String intro, String type, HttpServletRequest request);

    List<JSONObject> getVideoList(String type);

    Integer deleteVideo(Integer id);

    List<JSONObject> getVideoInfo(ArrayList<Integer> id_list);
}
