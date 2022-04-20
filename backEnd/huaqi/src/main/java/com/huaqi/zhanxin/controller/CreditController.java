package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("credit")
public class CreditController {
    @Autowired
    private CreditService creditService;
    RestControllerHelper helper = new RestControllerHelper();
    @ApiOperation(value = "获取信用信息")
    @GetMapping("userCredit")
    public Map<String, Object> getUserId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // token信息获取用户ID
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID=1;
        Credit credit = creditService.selectScore(userID);

        map.put("total_score", credit.getTotalScore());
        map.put("identity_score", credit.getIdentityScore());
        map.put("asset_score", credit.getAssetScore());
        map.put("credit_score", credit.getCreditScore());
        map.put("behavior_score", credit.getBehaviorScore());
        map.put("social_score", credit.getSocialScore());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

}
