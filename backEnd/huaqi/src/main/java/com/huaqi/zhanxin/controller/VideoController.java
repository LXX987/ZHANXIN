package com.huaqi.zhanxin.controller;



import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin
@RestController
public class VideoController {
    @Resource
    VideoService videoService;

    @ApiOperation(value = "上传视频")
    @RequestMapping(value = "/videos", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> uploadVideo(@RequestParam(name="file") MultipartFile file, HttpServletRequest request)
    {
        String uploadInfo = videoService.uploadVideo(file,request);
        if(uploadInfo.equals("-1"))
            return Result.error("-1","上传失败");
        else return Result.success(uploadInfo);
    }

}
