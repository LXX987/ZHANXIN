package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.service.PsychologyService;
import com.huaqi.zhanxin.service.QuestionService;
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

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private PsychologyService psychologyService;
    @Autowired
    private CreditService creditService;
    @Resource
    QuestionService questionService;
    RestControllerHelper helper = new RestControllerHelper();


    @RequestMapping("show")
    public List<UserBean> userList(){
        return userService.userList();
    }

    @ApiOperation(value = "验证身份")
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public Result<?> verifyIdentity(@RequestParam String userEmail, @RequestParam String userPassword)
    {
        UserBean user = userService.login(userEmail);
        if(user==null)
            return Result.error("404", "用户不存在");
        else {
            if(userPassword.equals(user.getUserPwd()) && user.getUserType().equals(1))
            {
                return Result.success();
            }
            else return Result.error("403", "验证失败");
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Map<String, Object> login(String userEmail, String userPassword, Integer userType, HttpServletRequest request, HttpServletResponse response) {
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
            if (Objects.equals(userType, user.getUserType())) {
                try {
                    Map<String, String> payload = new HashMap<>();
                    payload.put("userEmail", userEmail);
                    payload.put("userId", String.valueOf(user.getUserID()));
                    String token = JwtConfig.getToken(payload);
                    map.put("msg", "登录成功");
                    map.put("token", token);
                    HttpSession session = request.getSession();//session的创建
                    session.setAttribute("userEmail", userEmail);
                    session.setMaxInactiveInterval(15 * 60);
                    Cookie cookie = new Cookie("JSESSIONID", URLEncoder.encode(session.getId(), StandardCharsets.UTF_8));
                    cookie.setPath(request.getContextPath());
                    cookie.setMaxAge(48 * 60 * 60);//设置cookie有效期为2天
                    response.addCookie(cookie);
                } catch (Exception e) {
                    map.put("msg", e.getMessage());
                }
            }
            else map.put("msg", "账号权限错误");
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
        map.put("user_id", userID);
        map.put("userName", user.getUserName());
        map.put("userEmail", user.getUserEmail());
        map.put("userAvatar", user.getUserAvatar());
        UserInfo userInfo=userService.getInfo(userID);
        if(userInfo != null)
        {
            map.put("occupation", userInfo.getOccupation());
            map.put("annual_income", userInfo.getAnnualIncome());
            map.put("working_years", userInfo.getWorkingYears());
            map.put("authentication", userInfo.getAuthentication());
            map.put("IDtype", userInfo.getIDtype());
            map.put("IDcard", userInfo.getIDcard());
            map.put("phone", userInfo.getPhone());
            // 判断成年
            String Idcard=userInfo.getIDcard();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
            Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
            String nowTime = df.format(now);
            nowTime = nowTime.substring(0,4) + nowTime.substring(5, 7) + nowTime.substring(8, 10);
            int age = (Integer.parseInt(nowTime) - Integer.parseInt(Idcard.substring(6, 14))) / 10000;
            if(age<18) {
                map.put("adult", "未成年");
            } else {
                map.put("adult", "成年");
            }
            //计算身份得分并更新
            if (userInfo.getAuthentication()) {
                int indentityScore = calculateIdentity(userInfo.getOccupation(), userInfo.getAnnualIncome(), userInfo.getWorkingYears());
                creditService.updateIdentityScore(indentityScore, userID);
            }
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    /**
     * 计算身份得分的函数
     */
    public int calculateIdentity(int occupation,float annual_income,int working_years){
        //  归一化
        int identityScore = 0;
        double k1 = 0.007;
        double k2 = 0.001;
        double k3 = 20;
        double occupationScore,incomeScore,workingScore;
        occupationScore=k1*(occupation-5000);
        if(annual_income>=100000){
            incomeScore=100;
        }else{
            incomeScore=k2*annual_income;
        }
        if(working_years>=5){
            workingScore = 100;
        }else{
            workingScore=k3*working_years;
        }
        double identityScore1 = (0.4*occupationScore + 0.12*incomeScore + 0.48*workingScore)*5*0.15;
        identityScore=(int)identityScore1;
        return identityScore;
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
    public Map<String, Object> resetName(HttpServletRequest request, String userName, String userEmail,int occupation, float annualIncome, int workingYears, String phone) {

        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =2;
        System.out.println(userID);

        Map<String, Object> map = new HashMap<>();
        int resultInfo;
        if( userService.getInfo(userID)==null) {
            resultInfo=userService.insertInfo(occupation,annualIncome,workingYears,userID,phone);
        } else {
            resultInfo=userService.updateInfo(userID, occupation,annualIncome,workingYears,phone);
        }
        int resultName=userService.updateName(userID, userName, userEmail);

        if(resultName==1&&resultInfo==1) {
            map.put("msg", "修改成功");
            //计算身份得分并更新
            UserInfo userInfo=userService.getInfo(userID);
            if(userInfo.getAuthentication())
            {
                int indentityScore = calculateIdentity(occupation,annualIncome,workingYears);
                creditService.updateIdentityScore(indentityScore,userID);
            }
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
            // 向分数表插入数据
            user = userService.login(userEmail);
            int user_id = user.getUserID();
            creditService.insertScore(user_id,0,0,0,0,0,0);

            userService.insertNewInfo(user_id); //用户信息表插入数据
            userService.insertNewReputation(user_id); //用户信誉表插入数据
            userService.insertCreditRecord(user_id,0,0,0,0,0,0,0,0,0,0);

            // 向用户历史分数表中插入记录
            Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
            userService.insertHistoryRecord(user_id, 0, currentTIme, 0, 0,
                    0, 0, 0);
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
                UserInfo userInfo=userService.getInfo(userID);
                if(userInfo.getAuthentication())
                {
                    int indentityScore = calculateIdentity(userInfo.getOccupation(),userInfo.getAnnualIncome(),userInfo.getWorkingYears());
                    creditService.updateIdentityScore(indentityScore,userID);
                }
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

        int behaviorScore = calculateBehaviorScore(userID,honestyProof.getBloodDonation(),honestyProof.getVolunteer(),honestyProof.getContribution(),honestyProof.getCriminal(),honestyProof.getPhoneCost());
        creditService.updateBehaviorScore(behaviorScore,userID);

        map.put("bloodDonation", honestyProof.getBloodDonation());
        map.put("volunteer", honestyProof.getVolunteer());
        map.put("contribution", honestyProof.getContribution());
        map.put("criminal", honestyProof.getCriminal());
        map.put("phoneCost", honestyProof.getPhoneCost());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    /**
    计算行为方面得分
     */
    public int calculateBehaviorScore(int userId,int bloodDonation, double volunteer, double contribution,boolean criminal,int phoneCost){

        List<PsychologyBean> historyList = psychologyService.getResult(userId);
        historyList.sort((t1, t2) -> t2.getTestTime().compareTo(t1.getTestTime()));
        //System.out.println(historyList.get(0));
        int conscientiousness =  historyList.get(0).getConscientiousness();
        int agreeableness = historyList.get(0).getAgreeableness();
        int extraversion=historyList.get(0).getExtraversion();
        int neuroticism=historyList.get(0).getNeuroticism();
        int openness=historyList.get(0).getOpenness();
        // 计算心理测试得分
        int psyScore= 0;
        if(neuroticism<=20){psyScore+=5;}
        else if(neuroticism<=25){psyScore+=4;}
        else if(neuroticism<=30){psyScore+=3;}
        else if(neuroticism<=38){psyScore+=2;}
        else {psyScore+=1;}

        if(conscientiousness<=36){psyScore+=1;}
        else if(conscientiousness<=39){psyScore+=2;}
        else if(conscientiousness<=41){psyScore+=3;}
        else if(conscientiousness<=43){psyScore+=4;}
        else {psyScore+=5;}

        if(openness<=32){psyScore+=1;}
        else if(openness<=37){psyScore+=2;}
        else if(openness<=42){psyScore+=3;}
        else if(openness<=46){psyScore+=4;}
        else {psyScore+=5;}

        if(extraversion<=26){psyScore+=1;}
        else if(extraversion<=32){psyScore+=2;}
        else if(extraversion<=38){psyScore+=3;}
        else if(extraversion<=41){psyScore+=4;}
        else {psyScore+=5;}

        if(agreeableness<=30){psyScore+=1;}
        else if(agreeableness<=35){psyScore+=2;}
        else if(agreeableness<=40){psyScore+=3;}
        else if(agreeableness<=48){psyScore+=4;}
        else {psyScore+=5;}

        // 计算视频分
        int videoScore = 0;
        List<VideoScore> questionList = questionService.getVideoScore(userId);
        for(int i = 0;i<questionList.size();i++){
            videoScore += questionList.get(i).getQuestion_score();
        }

        // 计算声誉分
        int conScore = 0;
        if(contribution <=100){
            conScore = 10;
        }
        else if(contribution<=500){
            conScore = 13;
        }
        else if(contribution<=1000){
            conScore = 15;
        }
        else{
            conScore = 16;
        }
        int criScore = 30;
        if(!criminal){
            criScore = 0;
        }
        int phoScore = 10 - phoneCost;

        double  behaviorScore = videoScore + psyScore + bloodDonation*5 + volunteer + conScore + criScore + phoScore;
        int score = (int)behaviorScore;
        return score;
    }

    @ApiOperation(value = "获取信贷记录信息")
    @GetMapping("getCreditRecord")
    public Map<String, Object> getCreditRecord(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        CreditRecord creditRecord=userService.selectCreditRecord(userID);
        double DebtRatio = creditRecord.getDebtRatio();
        int numberRealEstateLoansOrLines = creditRecord.getNumberRealEstateLoansOrLines();
        int numberOfOpenCreditLinesAndLoans = creditRecord.getNumberOfOpenCreditLinesAndLoans();
        int numberOfTime30To59DaysPastDueNotWorse = creditRecord.getNumberOfTime30To59DaysPastDueNotWorse();
        double revolvingUtilizationOfUnsecuredLines = creditRecord.getRevolvingUtilizationOfUnsecuredLines();
        int seriousDlqin2yrs =  creditRecord.getSeriousDlqin2yrs();
        double monthlyIncome = creditRecord.getMonthlyIncome();
        int numberOfTime90DaysLate = creditRecord.getNumberOfTime90DaysLate();
        int numberOfDependents = creditRecord.getNumberOfDependents();
        int numberOfTime60To89DaysPastDueNotWorse = creditRecord.getNumberOfTime60To89DaysPastDueNotWorse();

        // 计算分数
        int creditScore = calculateCreditScore(userID,DebtRatio,
                numberRealEstateLoansOrLines,
                numberOfOpenCreditLinesAndLoans,
                numberOfTime30To59DaysPastDueNotWorse,
                revolvingUtilizationOfUnsecuredLines,
                seriousDlqin2yrs,
                monthlyIncome,
                numberOfTime90DaysLate,
                numberOfDependents,
                numberOfTime60To89DaysPastDueNotWorse
        );
        // 更新分数
        creditService.updateCreditScore(creditScore,userID);

        // 数据返回给前端
        map.put("DebtRatio", DebtRatio);
        map.put("numberRealEstateLoansOrLines", numberRealEstateLoansOrLines);
        map.put("numberOfOpenCreditLinesAndLoans", numberOfOpenCreditLinesAndLoans);
        map.put("numberOfTime30To59DaysPastDueNotWorse", numberOfTime30To59DaysPastDueNotWorse);
        map.put("revolvingUtilizationOfUnsecuredLines", revolvingUtilizationOfUnsecuredLines);
        map.put("seriousDlqin2yrs", seriousDlqin2yrs);
        map.put("monthlyIncome", monthlyIncome);
        map.put("numberOfTime90DaysLate", numberOfTime90DaysLate);
        map.put("numberOfDependents", numberOfDependents);
        map.put("numberOfTime60To89DaysPastDueNotWorse", numberOfTime60To89DaysPastDueNotWorse);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    /**
    计算信贷方面的得分
     */
    public int calculateCreditScore(int userID,double DebtRatio,
                                    int numberRealEstateLoansOrLines,
                                    int numberOfOpenCreditLinesAndLoans,
                                    int numberOfTime30To59DaysPastDueNotWorse,
                                    double revolvingUtilizationOfUnsecuredLines,
                                    int seriousDlqin2yrs,
                                    double monthlyIncome,
                                    int numberOfTime90DaysLate,
                                    int numberOfDependents,
                                    int numberOfTime60To89DaysPastDueNotWorse)
    {
        // 待完成
        double k1=100*0.39,
                k2=1*0.0387,
                k3=10*0.225,
                k4=100*0.012,
                k5=0.001*0.0084,
                k6=10*0.02,
                k7=10*0.1925,
                k8=100*0.006,
                k9=1*0.106,
                k10=30*0.0014;
        UserInfo userInfo=userService.getInfo(userID);
        String IdCard = userInfo.getIDcard();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
        String nowTime = df.format(now);
        nowTime = nowTime.substring(0,4) + nowTime.substring(5, 7) + nowTime.substring(8, 10);
        int age = (Integer.parseInt(nowTime) - Integer.parseInt(IdCard.substring(6, 14))) / 10000;
        double creditScore = (DebtRatio*k4
                + age*k2
                + numberRealEstateLoansOrLines*k8
                + numberOfOpenCreditLinesAndLoans*k6
                + numberOfTime30To59DaysPastDueNotWorse*k3
                + revolvingUtilizationOfUnsecuredLines*k1
                + monthlyIncome*k5
                + numberOfTime90DaysLate*k7
                + numberOfDependents*k10
                + numberOfTime60To89DaysPastDueNotWorse*k9)*0.35;
        int score = (int)creditScore;
        return score;
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

    @ApiOperation(value = "登录异常")
    @PostMapping("loginException")
    public Map<String, Object> loginException(String userEmail){
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isEmpty(userEmail)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.login(userEmail);
        int userID=user.getUserID();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        userService.insertException(userID,d);
        map.put("msg", "登录异常");
        return map;
    }

    @ApiOperation(value = "设置安全问题")
    @PostMapping("insertSecurityQuestion")
    public Map<String, Object> insertSecurityQuestion(HttpServletRequest request, String teacher, String city){
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if (StringUtils.isEmpty(teacher) || StringUtils.isEmpty(city)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        userService.insertSecurityQuestion(userID,teacher,city);
        map.put("msg", "安全问题设置成功");
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "查询安全问题")
    @GetMapping("selectSecurityQuestion")
    public Map<String, Object> selectSecurityQuestion(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        SecurityQuestion securityQuestion=userService.selectSecurityQuestion(userID);
        if(securityQuestion==null){
            map.put("msg", "0");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } else {
            map.put("msg", "1");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value = "回答安全问题")
    @PostMapping("answerSecurityQuestion")
    public Map<String, Object> answerSecurityQuestion(HttpServletRequest request, String teacher, String city){
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if (StringUtils.isEmpty(teacher) || StringUtils.isEmpty(city)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        SecurityQuestion securityQuestion=userService.selectSecurityQuestion(userID);
        if(teacher.equals(securityQuestion.getTeacher())&&city.equals(securityQuestion.getCity())) {
            map.put("msg", "正确");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } else {
            map.put("msg", "错误");
            helper.setMsg("Fail");
            helper.setData(map);
            return helper.toJsonMap();
        }

    }

    @ApiOperation(value = "获取用户的年龄分布")
    @PostMapping("getUsersAges")
    public Map<String, Object> getUserAges() {
        Map<String, Object> map = new HashMap<>();
        List<AgeScoreBean> identityInfoList = userService.selectAllAges();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
        String nowTime = df.format(now);
        nowTime = nowTime.substring(0,4) + nowTime.substring(5, 7) + nowTime.substring(8, 10);
        System.out.println(nowTime);
        System.out.println(identityInfoList);
        //计算年龄，得到userId->userAge
        for (AgeScoreBean ageScoreBean : identityInfoList) {
            if (ageScoreBean.getIDcard() != null && !ageScoreBean.getIDcard().isEmpty()) {
                String Idcard = ageScoreBean.getIDcard();
                int age = (Integer.parseInt(nowTime) - Integer.parseInt(Idcard.substring(6, 14))) / 10000;
                System.out.println(age);
                ageScoreBean.setAge(age);
            } else {
                ageScoreBean.setAge(0);
            }
        }
        int unAuthentication = 0;
        int num1822 = 0;
        int num2225 = 0;
        int num2530 = 0;
        int num30 = 0;
        for(AgeScoreBean ageScoreBean : identityInfoList) {
            int age = ageScoreBean.getAge();
            if(age == 0) {
                System.out.println("未实名认证");
                unAuthentication += 1;
            } else if(age < 18) {
                System.out.println("!!!，未成年不可以信贷！！！");
            } else if(age < 22) {
                num1822 += 1;
            } else if(age < 25) {
                num2225 += 1;
            } else if(age < 30) {
                num2530 += 1;
            } else {
                num30 += 1;
            }
        }
        //1.获取年龄分布
        //2.获取不同等级的年龄特征
        map.put("unauthentication", unAuthentication);
        map.put("num1822", num1822);
        map.put("num2225", num2225);
        map.put("num2530", num2530);
        map.put("num30", num30);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "用户获取信用分数")
    @PostMapping("getCreditScore")
    public Map<String, Object> getCreditScore(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID = 3;

        HistoryScore currentCredit = userService.selectUserCredit(userID);
        map.put("totalScore", currentCredit.getTotalScore());
        map.put("identityScore", currentCredit.getIdentityScore());
        map.put("assetsScore", currentCredit.getAssetScore());
        map.put("creditScore",currentCredit.getCreditScore());
        map.put("behaviourScore", currentCredit.getBehaviorScore());
        map.put("socialScore", currentCredit.getSocialScore());
        map.put("lastUpdateTime", currentCredit.getHistoryTime());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

}
