package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import com.huaqi.zhanxin.tools.JwtConfig;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    RestControllerHelper helper = new RestControllerHelper();

    @RequestMapping("show")
    public List<UserBean> userList(){
        return userService.userList();
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Map<String, Object> login(String userEmail, String userPassword, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(userEmail)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.login(userEmail);
        if (user == null) {
            map.put("msg", "用户不存在");
            return map;
        }
        if (userPassword.equals(user.getUserPwd())) {
            try {
                Map<String, String> payload = new HashMap<>();
                payload.put("userEmail", userEmail);
                payload.put("userId",String.valueOf(user.getUserID()));
                String token = JwtConfig.getToken(payload);
                map.put("msg", "登录成功");
                map.put("token", token);
                HttpSession session=request.getSession();//session的创建
                session.setAttribute("userEmail",userEmail);
                session.setMaxInactiveInterval(15*60);
                Cookie cookie = new Cookie("JSESSIONID", URLEncoder.encode(session.getId(), StandardCharsets.UTF_8));
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(48*60*60);//设置cookie有效期为2天
                response.addCookie(cookie);
            } catch (Exception e) {
                map.put("msg", e.getMessage());
            }
        } else {
            map.put("msg", "密码错误");
        }
        return map;
    }

    @ApiOperation(value = "获取身份信息")
    @GetMapping("userInfo")
    public Map<String, Object> userInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        UserBean user = userService.selectName(userID);
        map.put("userName", user.getUserName());
        map.put("userEmail", user.getUserEmail());
        UserInfo userInfo=userService.getInfo(userID);
        map.put("user_id", userInfo.getUserID());
        map.put("occupation", userInfo.getOccupation());
        map.put("annual_income", userInfo.getAnnualIncome());
        map.put("working_years", userInfo.getWorkingYears());
        map.put("authentication", userInfo.getAuthentication());
        map.put("IDtype", userInfo.getIDtype());
        map.put("IDcard", userInfo.getIDcard());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "获取用户姓名")
    @GetMapping("getUserName")
    public Map<String, Object> getUserName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID = 4;
        UserBean user = userService.selectName(userID);
        map.put("userName", user.getUserName());
        map.put("userID", user.getUserID());
        map.put("userEmail", user.getUserEmail());
        map.put("userAvatar", user.getUserAvatar());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "更新身份信息")
    @PostMapping("updateUserInfo")
    public Map<String, Object> resetName(HttpServletRequest request, String userName, String userEmail,int occupation, float annualIncome, int workingYears) {

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =2;
        System.out.println(userID);

        Map<String, Object> map = new HashMap<>();
        int resultInfo;
        if( userService.getInfo(userID)==null) {
            resultInfo=userService.insertInfo(occupation,annualIncome,workingYears,userID);
        } else {
            resultInfo=userService.updateInfo(userID, occupation,annualIncome,workingYears);
        }
        int resultName=userService.updateName(userID, userName, userEmail);

        if(resultName==1&&resultInfo==1) {
            map.put("msg", "修改成功");
        } else {
            map.put("msg", "修改失败，未查找到该账号数据");
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "注册")
    @PostMapping("registerUser")
    public Map<String, Object> register(String userEmail, String userPwd, int userType){
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isEmpty(userEmail)||StringUtils.isEmpty(userPwd)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.login(userEmail);
        if (user == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            userService.register(userEmail,userPwd,userType,localDateTime);
            map.put("msg", "注册成功");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        else{
            map.put("msg", "该账号已存在");
            helper.setMsg("Fail");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value = "更新实名信息")
    @PostMapping("updateAuthentication")
    public Map<String, Object> updateAuthentication(HttpServletRequest request, String userName,int IDtype, String IDcard) throws IOException{

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        System.out.println(userID);

        // 调用实名接口
        String url = "https://idcardv2.shumaidata.com/new-idcard";
        String appCode = "e4e022782c4f4b848da20bd497419f6a";

        Map<String, String> params = new HashMap<>();
        params.put("idcard", IDcard);
        params.put("name", userName);

        String result = get(appCode, url, params);
        //String result ="{\"msg\":\"成功\",\"success\":true,\"code\":200,\"data\":{\"result\":0,\"order_no\":\"654645355292741231\",\"desc\":\"一致\",\"sex\":\"女\",\"birthday\":\"20001118\",\"address\":\"甘肃省兰州市七里河区\"}}";
        System.out.println("result:"+result);
        int resultNum;
        if(result==null){
            resultNum=3;
        } else {
            resultNum = Integer.parseInt(result);
        }
        Map<String, Object> map = new HashMap<>();
        if(resultNum==0) {
            boolean authentication=false;
            authentication=true;
            map.put("result", "实名认证成功");
            int sqlResult=userService.updateAuthentication(userID,authentication,IDtype,IDcard);
            if(sqlResult==1) {
                map.put("msg", "数据库修改成功");
                map.put("success", "1");
            } else {
                map.put("msg", "数据库修改失败，未查找到该账号数据");
                map.put("success", "2");
            }
        } else if(resultNum==1) {
            map.put("result", "实名认证失败，姓名与身份证号不一致");
            map.put("msg", "实名认证失败");
            map.put("success", "0");
        } else if(resultNum==2) {
            map.put("result", "实名认证失败，无记录");
            map.put("msg", "实名认证失败");
            map.put("success", "0");
        } else {
            map.put("result", "姓名或身份证号错误");
            map.put("msg", "接口调用错误");
            map.put("success", "2");
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    public static String get(String appCode, String url, Map<String, String> params) throws IOException {
        url = url + buildRequestUrl(params);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        if(response.code()==200) {
            String result = response.body().string();
            JSONObject parseObject = JSONArray.parseObject(result);
            JSONObject data = parseObject.getJSONObject("data");
            String getResult=data.getString("result");
            return getResult;
        } else {
            return "400";
        }

    }

    public static String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }

    @ApiOperation(value = "获取信誉证明信息")
    @GetMapping("getHonestyProof")
    public Map<String, Object> getHonestyProof(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        HonestyProof honestyProof = userService.selectHonestyProof(userID);
        map.put("bloodDonation", honestyProof.getBloodDonation());
        map.put("volunteer", honestyProof.getVolunteer());
        map.put("contribution", honestyProof.getContribution());
        map.put("criminal", honestyProof.getCriminal());
        map.put("phoneCost", honestyProof.getPhoneCost());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取信贷记录信息")
    @GetMapping("getCreditRecord")
    public Map<String, Object> getCreditRecord(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        CreditRecord creditRecord=userService.selectCreditRecord(userID);
        map.put("DebtRatio", creditRecord.getDebtRatio());
        map.put("numberRealEstateLoansOrLines", creditRecord.getNumberRealEstateLoansOrLines());
        map.put("numberOfOpenCreditLinesAndLoans", creditRecord.getNumberOfOpenCreditLinesAndLoans());
        map.put("numberOfTime30To59DaysPastDueNotWorse", creditRecord.getNumberOfTime30To59DaysPastDueNotWorse());
        map.put("revolvingUtilizationOfUnsecuredLines", creditRecord.getRevolvingUtilizationOfUnsecuredLines());
        map.put("seriousDlqin2yrs", creditRecord.getSeriousDlqin2yrs());
        map.put("monthlyIncome", creditRecord.getMonthlyIncome());
        map.put("numberOfTime90DaysLate", creditRecord.getNumberOfTime90DaysLate());
        map.put("numberOfDependents", creditRecord.getNumberOfDependents());
        map.put("numberOfTime60To89DaysPastDueNotWorse", creditRecord.getNumberOfTime60To89DaysPastDueNotWorse());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("setPassword")
    public Map<String, Object> setPassword(String userEmail, String userPwd){
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isEmpty(userEmail)||StringUtils.isEmpty(userPwd)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.login(userEmail);
        if (user == null) {
            map.put("msg", "该邮箱暂未注册账号");
            helper.setMsg("Fail");
            helper.setData(map);
            return helper.toJsonMap();
        } else{
            userService.updatePwd(userPwd,userEmail);
            map.put("msg", "修改密码成功");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @ApiOperation(value="上传头像")
    @ResponseBody
    @PostMapping("uploadAvatar")
    public Map<String, Object> uploadAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = userService.updateAvatar(userID,file,request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("changePassword")
    public Map<String, Object> changePassword(HttpServletRequest request, String oldPwd, String newPwd){
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.selectName(userID);
        if (!oldPwd.equals(user.getUserPwd())) {
            LOGGER.info(oldPwd);
            LOGGER.info(user.getUserPwd());
            map.put("msg", "原密码错误");
            helper.setMsg("Fail");
            helper.setData(map);
            return helper.toJsonMap();
        } else{
            userService.changePwd(newPwd,userID);
            map.put("msg", "修改密码成功");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }
}
