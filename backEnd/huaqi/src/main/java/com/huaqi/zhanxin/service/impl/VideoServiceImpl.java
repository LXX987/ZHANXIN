package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.VideoInfo;
import com.huaqi.zhanxin.mapper.VideoMapper;
import com.huaqi.zhanxin.service.VideoService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {


    @Value("${path.video-upload-path}")
    private String VIDEO_UPLOAD_PATH;

    @Value("${path.picture-upload-path}")
    private String COVER_UPLOAD_PATH;

    @Resource
    VideoMapper videoMapper;

    @Override
    public String uploadVideo(MultipartFile video, MultipartFile cover, String title, String intro, String type, HttpServletRequest request)
    {
        String video_url;
        String cover_url;
        try {
            video_url = UploadUtil.upload(video, VIDEO_UPLOAD_PATH, request);
            cover_url = UploadUtil.upload(cover, COVER_UPLOAD_PATH, request);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        if(video_url == null || cover_url == null)
            return "-2";
        videoMapper.insert(title, LocalDateTime.now(), video_url, intro, type, cover_url);
        return video_url;
    }

    @Override
    public List<JSONObject> getVideoList(String type)
    {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<VideoInfo> videoInfos = videoMapper.selectAllByType(type);
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

    @Override
    public List<JSONObject> getVideoInfo(ArrayList<Integer> id_list)
    {
        String ids = id_list.toString().replace("[", "(").replace("]", ")");

        return videoMapper.selectVideoById(ids);
    }

}
