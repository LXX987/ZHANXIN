package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.Asset;
import com.huaqi.zhanxin.entity.CreditAppeal;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.CreditAppealService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("appeal")
public class CreditAppealController {
    @Autowired
    private CreditAppealService creditAppealService;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "插入信用申诉申请")
    @PostMapping("insertAppeal")
    public Map<String, Object> insertAppeal(HttpServletRequest request, String reason, String question, String require){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(reason)||StringUtils.isEmpty(question)||StringUtils.isEmpty(require)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        Timestamp d = new Timestamp(System.currentTimeMillis());
        creditAppealService.insertAppeal(userID,d,reason, question, require, 0);
        map.put("msg", "申诉成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取信用申诉申请")
    @GetMapping("appealList")
    public List<CreditAppeal> userList(HttpServletRequest request){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        return creditAppealService.getCreditAppeal(userID);
    }
}
