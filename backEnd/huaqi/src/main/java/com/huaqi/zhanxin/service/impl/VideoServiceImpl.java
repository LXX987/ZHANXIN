package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.VideoInfo;
import com.huaqi.zhanxin.mapper.VideoMapper;
import com.huaqi.zhanxin.service.VideoService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    private final String UPLOAD_PATH = "D:/opt/JavaEE/images/";

    @Resource
    VideoMapper videoMapper;

    @Override
    public String uploadVideo(MultipartFile file, String title, String intro, HttpServletRequest request)
    {
        String url;
        try {
            url = UploadUtil.upload(file, UPLOAD_PATH, request);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        videoMapper.insert(title, LocalDateTime.now(), url, intro);
        return url;
    }

    @Override
    public List<JSONObject> getVideoList()
    {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<VideoInfo> videoInfos = videoMapper.selectAll();
        if(videoInfos.isEmpty())
            return null;
        for (var videoInfo:videoInfos) {
            JSONObject object = new JSONObject(videoInfo);
            jsonObjects.add(object);
        }
        return jsonObjects;
    }

    @Override
    public Integer deleteVideo(Integer id)
    {
        Integer count = videoMapper.deleteOneById(id);
        if(count == 1)
            return 1;
        else return -1;
    }

}
