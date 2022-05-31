package com.huaqi.zhanxin.controller;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
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

    @ApiOperation(value = "申诉列表")
    @RequestMapping(value = "/appeals", method = RequestMethod.GET)
    public Result<?> getAppealList(@RequestParam(value = "type",defaultValue = "all") String type,
                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        List<JSONObject> jsonObjects = creditAppealService.getAppealList(type, pageNum, pageSize);
        if(jsonObjects.isEmpty())
            return Result.error("404", "暂无数据");
        else
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("listSize", jsonObjects.size());
            jsonObject.put("data", jsonObjects);
            return Result.success(jsonObject);
        }
    }

    @ApiOperation(value = "审核申诉")
    @RequestMapping(value = "/appeals", method = RequestMethod.PUT)
    public Result<?> judgeAppeal(@RequestParam Integer user_id, @RequestParam Timestamp appeal_time, @RequestParam String type, @RequestParam String detail, @RequestParam Double value, @RequestParam Integer state)
    {
        Integer count = creditAppealService.judgeAppeal(user_id, appeal_time, type, detail, value, state);
        if(count == 1)
            return Result.success();
        else return Result.error("500", "操作失败");
    }
}
