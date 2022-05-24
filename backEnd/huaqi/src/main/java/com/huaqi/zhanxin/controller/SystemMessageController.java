package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.mapper.SystemMessageMapper;
import com.huaqi.zhanxin.service.PsychologyService;
import com.huaqi.zhanxin.service.SystemMessageService;
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
@RequestMapping("systemMessage")
public class SystemMessageController {
    @Autowired
    private SystemMessageService systemMessageService;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "发布信息")
    @PostMapping("releaseMessage")
    public Map<String, Object> releaseSystemMessage(String messageTitle, String messageContent){
        Map<String, Object> map = new HashMap<>();
        Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
        int result = systemMessageService.insetSystemMessage(messageTitle, messageContent, currentTIme);
        map.put("computeResult", result);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "查看信息")
    @PostMapping("getMessage")
    public Map<String, Object> getMessage() {
        Map<String, Object> map = new HashMap<>();
        List<SystemMessageBean> messageList = systemMessageService.getMessage();
        map.put("messageList", messageList);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
