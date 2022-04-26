package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
}
