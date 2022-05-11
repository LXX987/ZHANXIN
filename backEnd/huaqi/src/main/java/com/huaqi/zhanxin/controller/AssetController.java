package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.Asset;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.entity.UserBean;
import com.huaqi.zhanxin.service.AssetService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("asset")
public class AssetController {
    @Autowired
    private AssetService assetService;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "获取资产列表")
    @GetMapping("assetList")
    public List<Asset> userList(HttpServletRequest request){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        List<Asset> assetList=assetService.assetList(userID);

        return assetService.assetList(userID);
    }

    @ApiOperation(value = "插入银行账户")
    @PostMapping("insertAsset")
    public Map<String, Object> insertAsset(HttpServletRequest request, String bank, int money){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(bank)||StringUtils.isEmpty(money)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(d);
        System.out.println(bank);
        System.out.println(money);
        System.out.println(userID);
        assetService.insertAsset(userID,bank,money,d);
        map.put("msg", "添加成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取资产钱数")
    @GetMapping("getMoney")
    public Map<String, Object> getMoney(HttpServletRequest request){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        Map<String, Object> map = new HashMap<>();
        int money=assetService.getMoney(userID);
        System.out.println(money);
        map.put("money", money);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "删除银行账户")
    @PostMapping("deleteAsset")
    public Map<String, Object> deleteAsset(HttpServletRequest request, Timestamp addTime){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        Map<String, Object> map = new HashMap<>();
        assetService.deleteAsset(userID,addTime);
        map.put("msg", "删除成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "管理银行账户")
    @PostMapping("updateAsset")
    public Map<String, Object> updateAsset(HttpServletRequest request, String bank, int money, Timestamp addTime){
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        Map<String, Object> map = new HashMap<>();
        assetService.updateAsset(userID, bank, money, addTime);
        map.put("msg", "编辑成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
