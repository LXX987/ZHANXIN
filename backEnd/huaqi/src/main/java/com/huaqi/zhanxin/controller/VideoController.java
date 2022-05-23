package com.huaqi.zhanxin.controller;



import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController

public class VideoController {
    @Resource
    VideoService videoService;

    @ApiOperation(value = "上传视频")
    @RequestMapping(value = "/admin/videos", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> uploadVideo(@RequestParam(name="video") MultipartFile video, @RequestParam(name="cover") MultipartFile cover, String title, String intro, String type, HttpServletRequest request) {
        String uploadInfo = videoService.uploadVideo(video, cover, title, intro, type, request);
        if (uploadInfo.equals("-1"))
            return Result.error("500", "上传失败");
        else if (uploadInfo.equals("-2"))
            return Result.error("415", "文件类型错误");
        else return Result.success(uploadInfo);
    }

    @ApiOperation(value = "查看视频列表")
    @RequestMapping(value = "/common/videos/{type}", method = RequestMethod.GET)
    public Result<?> getVideoList(@PathVariable String type)
    {
        List<JSONObject> jsonObjects = videoService.getVideoList(type);
        if(jsonObjects == null)
            return Result.error("404", "暂无视频");
        else return Result.success(jsonObjects);
    }

    @ApiOperation(value = "删除视频")
    @RequestMapping(value = "/admin/videos/{id}", method = RequestMethod.DELETE)
    public Result<?> deleteVideo(@PathVariable Integer id)
    {
        Integer count = videoService.deleteVideo(id);
        if(count == -1)
            return Result.error("500", "删除失败");
        else return Result.success();
    }

    @ApiOperation(value = "通过id列表获取视频信息")
    @RequestMapping(value = "/common/videos", method = RequestMethod.POST)
    public Result<?> getVideoInfo(@RequestParam String id_list)
    {
        System.out.println(id_list);
        id_list = id_list.replace("[","");
        id_list = id_list.replace("]","");
        System.out.println(id_list);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(id_list.split(",")));
        ArrayList<Integer> resultList = getIntegerArray(list);
        List<JSONObject> jsonObjects = videoService.getVideoInfo(resultList);

        if(jsonObjects.isEmpty())
            return Result.error("404", "暂无数据");
        else return Result.success(jsonObjects);
    }
    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
                //Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }


}
