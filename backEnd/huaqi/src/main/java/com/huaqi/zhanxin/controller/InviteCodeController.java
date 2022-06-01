package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.InviteCode;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.InviteCodeService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("InviteCode")
public class InviteCodeController {
    @Autowired
    private InviteCodeService inviteCodeService;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "生成邀请码")
    @PostMapping("generareCode")
    public Map<String, Object> generate(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
        int userID = getInfo.getUserId();
        //int userID = 1;
        if (StringUtils.isEmpty(userID)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        InviteCode inviteCode = inviteCodeService.searchUserCode(userID);
        if(inviteCode!=null){//该用户已有邀请码
            map.put("inviteCode", inviteCode.getInviteCode());
        }else{
            String code = generateCode();
            InviteCode inviteCode1 = inviteCodeService.searchCode(code);
            while(inviteCode1!=null)// 该邀请码不唯一
            {
                code = generateCode();
                inviteCode1 = inviteCodeService.searchCode(code);
            }
            inviteCodeService.insertCode(userID,code,currentTIme);
            map.put("inviteCode", code);
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "填写邀请码")
    @PostMapping("friendCode")
    public Map<String, Object> friendCode(HttpServletRequest request,String invitedCode) {
        Map<String, Object> map = new HashMap<>();
        Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();
//        int userId = 2;
        if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(invitedCode)) {
            map.put("msg", "关键数据缺失");
            return map;
        }

        inviteCodeService.insertFriend(userId,invitedCode,currentTIme);
        map.put("msg", "提交成功！");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    // 生成邀请码的函数
    public static String generateCode() {
        String charList = "ABCDEFGHIJKLMNPQRSTUVWXY";
        String numList = "0123456789";
        String rev = "";
        int maxNumCount = 4;
        Random f = new Random();
        for(int i = 0; i < 6; i++) {
            if (f.nextBoolean() && maxNumCount > 0) {
                maxNumCount--;
                rev += numList.charAt(f.nextInt(numList.length()));
            } else {
                rev += charList.charAt(f.nextInt(charList.length()));
            }
        }
        return rev;
    }
}
