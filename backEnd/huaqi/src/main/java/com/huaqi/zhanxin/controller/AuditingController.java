package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.AuditingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AuditingController {
    @Resource
    AuditingService auditingService;

    @ApiOperation(value="审核列表")
    @RequestMapping(value = "/certificates", method = RequestMethod.GET)
    public Result<?> getCertificateList(@RequestParam("type") String type,
                                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        List<JSONObject> jsonObjects = auditingService.getCertificateList(type, pageNum, pageSize);
        if(jsonObjects == null)
            return Result.error("404", "暂无审核信息");
        else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("listSize", jsonObjects.size());
            jsonObject.put("data", jsonObjects);
            return Result.success(jsonObject);
        }
    }

    @ApiOperation(value = "审核")
    @RequestMapping(value = "/certificates", method = RequestMethod.PUT)
    public Result<?> examineCertificate(@RequestParam("id") Integer id, @RequestParam("state") Integer state, @RequestParam("num") Integer num, @RequestParam("type") String type)
    {
        Integer count = auditingService.examineCertificate(id, state, num, type);
        if(count == 1)
            return Result.success();
        else return Result.error("500", "操作失败");
    }
}
