package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.MetricsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class MetricsController {

    @Resource
    MetricsService metricsService;

    @ApiOperation(value = "系统状态信息")
    @RequestMapping(value = "/metrics/sysinfo", method = RequestMethod.GET)
    public Result<?> displaySystemInfo(HttpServletRequest request) {
        JSONObject jsonObject = metricsService.displaySystemInfo();
        Integer count = (Integer) request.getServletContext().getAttribute("count");
        if (count == null)
            count = 0;
        jsonObject.put("onlineUsers", count);
        return Result.success(jsonObject);
    }

    @ApiOperation(value = "系统历史信息")
    @RequestMapping(value = "/metrics/history", method = RequestMethod.GET)
    public Result<?> getHistory()
    {
        List<JSONObject> jsonObjects = metricsService.getHistory();
        return Result.success(jsonObjects);
    }
}
