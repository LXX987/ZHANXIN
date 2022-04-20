package com.huaqi.zhanxin.controller;
import com.huaqi.zhanxin.tools.MailService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("mail")
public class MailController {
        @Autowired
        private MailService mailService;
        @CrossOrigin
        @ApiOperation(value="邮箱")
        @PostMapping(value = "/mail")
        @ResponseBody
        public String getCheckCode(String email){
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "hi!!!你的注册验证码是："+checkCode;
            try {
                mailService.sendSimpleMail(email, "注册验证码", message);
            }catch (Exception e){
                e.printStackTrace();
                return "发送失败";
            }
            return checkCode;
        }
}
