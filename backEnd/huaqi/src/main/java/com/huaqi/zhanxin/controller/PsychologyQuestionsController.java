package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.PsychologyQuestionsBean;
import com.huaqi.zhanxin.mapper.PsychologyQuestionsMapper;
import com.huaqi.zhanxin.service.PsychologyQuestionsService;
import com.huaqi.zhanxin.tools.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("psychologyQuestions")
public class PsychologyQuestionsController {
    @Autowired
    private PsychologyQuestionsService psychologyQuestionsService;

    @RequestMapping("show")
    public  List<PsychologyQuestionsBean> psychologyQuestionsList(){
        return  psychologyQuestionsService.psychologyQuestionsList();
    }

    @ApiOperation(value = "获取心理测评题目")
    @PostMapping("getQuestions")
    public Map<String, Object> getQuestions() {
        Map<String, Object> map = new HashMap<>();
        List<PsychologyQuestionsBean> psychologyQuestionsList = psychologyQuestionsService.psychologyQuestionsList();

            try {
                map.put("msg", "获取题目成功");
                map.put("questions", psychologyQuestionsList);
            } catch (Exception e) {
                map.put("msg", e.getMessage());
            }

            return map;

    }

}
