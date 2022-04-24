package com.huaqi.zhanxin.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface VideoService {

    String uploadVideo(MultipartFile file, HttpServletRequest request);
}
