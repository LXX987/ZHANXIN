package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.AssetService;
import com.huaqi.zhanxin.service.BankService;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    BankService bankService;
    @Autowired
    UserService userService;
    @Autowired
    CreditService creditService;
    @Autowired
    private AssetService assetService;

    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "用户列表")
    @GetMapping("bankSearchList")
    public List<BankSearch> bankSearchList() {
        return bankService.bankSearchList();
    }

    @ApiOperation(value = "用户列表")
    @PostMapping("bankSearchID")
    public List<BankSearch> bankSearchID(int userID,String userName) {
        if (StringUtils.isEmpty(userID)) {
            userID=0;
        }
        List<BankSearch> searchIDList=bankService.bankSearchID(userID);
        List<BankSearch> searchNameList=bankService.bankSearchName(userName);
        List<BankSearch> listAll = new ArrayList<BankSearch>();
        listAll.addAll(searchIDList);
        listAll.addAll(searchNameList);
        listAll = new ArrayList<BankSearch>(new LinkedHashSet<>(listAll));
        return listAll;
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("userBasicInfo")
    public Map<String, Object> userInfo(int userID) {
        Map<String, Object> map = new HashMap<>();
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
        map.put("phone", userInfo.getPhone());
        HonestyProof honestyProof = userService.selectHonestyProof(userID);
        map.put("bloodDonation", honestyProof.getBloodDonation());
        map.put("volunteer", honestyProof.getVolunteer());
        map.put("contribution", honestyProof.getContribution());
        map.put("criminal", honestyProof.getCriminal());
        map.put("phoneCost", honestyProof.getPhoneCost());
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
        Credit credit = creditService.selectScore(userID);
        map.put("total_score", credit.getTotalScore());
        map.put("identity_score", credit.getIdentityScore());
        map.put("asset_score", credit.getAssetScore());
        map.put("credit_score", credit.getCreditScore());
        map.put("behavior_score", credit.getBehaviorScore());
        map.put("social_score", credit.getSocialScore());
        int money=assetService.getMoney(userID);
        map.put("money", money);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }
}
