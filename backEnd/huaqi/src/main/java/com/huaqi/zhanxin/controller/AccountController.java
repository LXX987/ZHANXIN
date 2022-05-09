package com.huaqi.zhanxin.controller;


import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AccountController {

    @Resource
    AccountService accountService;

    @ApiOperation(value = "账号列表")
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public Result<?> getAccountList(@RequestParam("type") String type,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        List<JSONObject> jsonObjects = accountService.getAccountList(type, pageNum, pageSize);
        if(jsonObjects == null)
            return Result.error("404", "暂无用户信息");
        else
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("listSize", jsonObjects.get(jsonObjects.size()-1).get("count"));
            jsonObjects.remove(jsonObjects.size()-1);
            jsonObject.set("data", jsonObjects);
            return Result.success(jsonObject);
        }
    }

    @ApiOperation(value = "修改账号权限")
    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public Result<?> modifyAccountAuthority(@RequestParam("id") Integer id, @RequestParam("authority") Integer authority)
    {
        Integer count = accountService.modifyAccountAuthority(id, authority);
        if(count == 1)
            return Result.success();
        else return Result.error("500", "修改失败");
    }

    @ApiOperation(value = "注销账号")
    @RequestMapping(value = "/accounts", method = RequestMethod.DELETE)
    public Result<?> deactivateAccount(@RequestParam("id") Integer id)
    {
        Integer count = accountService.deactivateAccount(id);
        if(count == 1)
            return Result.success();
        else return Result.error("500", "删除失败");
    }
}
