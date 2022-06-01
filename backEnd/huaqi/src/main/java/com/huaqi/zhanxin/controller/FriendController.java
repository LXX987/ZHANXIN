package com.huaqi.zhanxin.controller;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.service.FriendService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CreditService creditService;
    @ApiOperation(value = "好友列表")
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Result<?> getFriendList(HttpServletRequest request,
                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        GetInformationFromRequest tokenInfo = new GetInformationFromRequest(request);
        Integer id = tokenInfo.getUserId();
        //Integer id = 1;
        int friendNum = 0;
        List<JSONObject> jsonObjects = friendService.getFriendList(id, pageNum, pageSize);
        if(jsonObjects == null){
            return Result.error("404", "暂无好友");
        }
        else{
            // 计算好友分
            friendNum = jsonObjects.size();
            int avgScore = 0;
            for(JSONObject friend :jsonObjects){
                System.out.println(friend);
                avgScore += friend.getInt("total_score");
            }
            int socialScore = calculateFriendScore(friendNum,avgScore);
            // 更新数据
            creditService.updateSocialScore(socialScore,id);
            return Result.success(jsonObjects);
        }
    }

    public int calculateFriendScore(int friendNum, int avgScore){
        double socialScore = 0.1*friendNum*avgScore*2*0.05;
        int score = (int)socialScore;
        return score;
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

    @ApiOperation(value = "删除好友")
    @RequestMapping(value = "/friends", method = RequestMethod.DELETE)
    public Result<?> deleteFriend(@RequestParam Integer friend_id, HttpServletRequest request)
    {
        GetInformationFromRequest tokenInfo = new GetInformationFromRequest(request);
        Integer user_id = tokenInfo.getUserId();
        Integer count = friendService.deleteFriend(user_id, friend_id);
        if(count <= 0)
            return Result.error("500", "删除失败");
        else return Result.success();
    }
}
