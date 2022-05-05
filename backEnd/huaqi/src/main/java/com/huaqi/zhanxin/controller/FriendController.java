package com.huaqi.zhanxin.controller;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.FriendService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class FriendController {

    @Resource
    FriendService friendService;

    @ApiOperation(value = "好友列表")
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Result<?> getFriendList(@RequestParam("id") Integer id,
                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {

        List<JSONObject> jsonObjects = friendService.getFriendList(id, pageNum, pageSize);
        if(jsonObjects == null)
            return Result.error("404", "暂无好友");
        else return Result.success(jsonObjects);
    }

    @ApiOperation(value = "添加好友")
    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public Result<?> sendFriendRequest()
    {
        return Result.success();
    }

    @ApiOperation(value = "获取我的邀请码")
    @RequestMapping(value = "/codes", method = RequestMethod.GET)
    public Result<?> getMyCode(HttpServletRequest request)
    {
        GetInformationFromRequest tokenInfo = new GetInformationFromRequest(request);
        Integer user_id = tokenInfo.getUserId();
//        Integer user_id = 1;
        String code = friendService.getMyCode(user_id);
        System.out.println(code);
        if(code == null)
            return Result.error("404", "暂未生成邀请码");
        else return Result.success(code);
    }
}
