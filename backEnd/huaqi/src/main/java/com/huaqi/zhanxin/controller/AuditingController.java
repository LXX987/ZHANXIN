package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.AssetService;
import com.huaqi.zhanxin.service.AuditingService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AuditingController {
    @Resource
    AuditingService auditingService;

    @Resource
    AssetService assetService;

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

    @ApiOperation(value = "审核银行账户")
    @RequestMapping(value = "/bankcertificates", method = RequestMethod.POST)
    public Result<?> examineBankCertificates(@RequestParam("certificate_id")Integer certificate_id, @RequestParam("user_id")Integer user_id, @RequestParam("bank")String bank, @RequestParam("money")Integer money, @RequestParam("state")Integer state){
        if (bank == null||money == null) {
            return Result.error("400", "缺失参数");
        }
        auditingService.examineBankCertificate(certificate_id, state);
        if(state == 2)
            return Result.success();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        assetService.insertAsset(user_id,bank,money,time);
        return Result.success();
    }
}
