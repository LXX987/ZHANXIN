package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.entity.UserBean;
import com.huaqi.zhanxin.entity.VideoScore;
import com.huaqi.zhanxin.service.QuestionService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import com.huaqi.zhanxin.tools.JwtConfig;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController

public class QuestionController {

    @Resource
    QuestionService questionService;

    @ApiOperation(value = "问题列表")
    @RequestMapping(value = "/common/questions/{id}", method = RequestMethod.GET)
    public Result<?> getQuestionList(@PathVariable("id") Integer video_id)
    {
        List<JSONObject> questionList = questionService.getQuestionList(video_id);
        if(questionList == null)
            return Result.error("404", "暂无问题");
        return Result.success(questionList);
    }

    @ApiOperation(value = "添加问题")
    @RequestMapping(value = "/admin/questions", method = RequestMethod.POST)
    public Result<?> addQuestion(@RequestParam("video_id") Integer video_id, @RequestParam("question_text")String question_text, @RequestParam("question_answer")String question_answer)
    {
        Integer count = questionService.addQuestion(video_id, question_text, question_answer);
        if(count == -1)
            return Result.error("500", "添加失败");
        else return Result.success();
    }

    @ApiOperation(value = "提交问题")
    @PostMapping("videoquestion")
    public Map<String, Object> submitQuestion(HttpServletRequest request,Integer video_id, Integer watch, Integer score) {
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =2;
        System.out.println(userID);
        Map<String, Object> map = new HashMap<>();
        try {
            Integer count = questionService.submitQuestion(userID,video_id,watch,score);
            if(count == -1){
                map.put("msg", "插入失败");
            }else{
                map.put("msg", "提交成功");
            }
            return map;
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation(value = "获取视频得分列表")
    @PostMapping("videoScore")
    public Map<String, Object> getVideoScore(HttpServletRequest request){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =2;
        Map<String, Object> map = new HashMap<>();
        List<VideoScore> questionList = questionService.getVideoScore(userID);
        try {
            if (questionList == null) {
                map.put("msg", "暂无记录");

            } else {
                map.put("videoScore", questionList);
                for(int i=0;i<questionList.size();i++){
                    ArrayList<Integer> video_list = new ArrayList<>();
                }
                System.out.println(questionList);
                map.put("msg", "获取成功");
            }
        }catch (Exception e){
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
