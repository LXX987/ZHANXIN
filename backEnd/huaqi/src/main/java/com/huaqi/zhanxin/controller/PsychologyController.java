package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.entity.PsychologyBean;
import com.huaqi.zhanxin.entity.UserBean;
import com.huaqi.zhanxin.entity.UserInfo;
import com.huaqi.zhanxin.mapper.PsychologyMapper;
import com.huaqi.zhanxin.service.PsychologyService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import com.huaqi.zhanxin.tools.JwtConfig;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("psychology")
public class PsychologyController {
    @Autowired
    private PsychologyService psychologyService;
    RestControllerHelper helper = new RestControllerHelper();

    @RequestMapping("show")
    public List<PsychologyBean> psychologyBeanList(){
        return psychologyService.psychologyList();
    }

    @ApiOperation(value = "获取心理测试结果")
    @PostMapping("getResult")
    public Map<String, Object> getResult(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();
        if (userId == 0) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        List<PsychologyBean> historyList = psychologyService.getResult(userId);
        if (historyList == null) {
            map.put("msg", "无作答测评记录");
            return map;
        }
        historyList.sort((t1, t2) -> t2.getTestTime().compareTo(t1.getTestTime()));
//        System.out.println("------------倒序后-----------------");
//        for (PsychologyBean order : historyList) {
//            System.out.println("Score=" + order.getTestScore() + ",Time=" + order.getTestTime());
//        }

        try {
            map.put("ResultScore", historyList.get(0));
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();
        }

    }

    @ApiOperation(value = "获取历史心理测评记录")
    @PostMapping("getHistory")
    public Map<String, Object> getHistory(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();
        //int userId = 1;

        if (userId == 0) {
            map.put("msg", "关键数据缺失");
            return map;
        }

        List<PsychologyBean> historyList = psychologyService.getHistory(userId);
        if (historyList == null) {
            map.put("msg", "无历史测评记录");
            return map;
        }

        try {
            map.put("historyList", historyList);
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            helper.setMsg("Failure");
            helper.setData(map);
            return helper.toJsonMap();
        }


    }

    @ApiOperation(value = "计算并返回心理测评结果")
    @PostMapping("getScore")
    public Map<String, Object> getScore(HttpServletRequest request, int totalScore, int score1, int score2, int score3, int score4, int score5){
        //score1:openness顺序依次后推
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        if (userID == 0){
            map.put("msg", "用户不存在");
            return  map;
        }
        Timestamp currentTIme = new Timestamp(System.currentTimeMillis());

        int result = psychologyService.recordScore(userID,currentTIme,score1,score2,score3,score4,score5,totalScore);


        map.put("computeResult", result);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
