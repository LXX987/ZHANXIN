package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.entity.HistoryScore;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("credit")
public class CreditController {
    @Autowired
    private CreditService creditService;
    @Autowired
    private UserService userService;
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

        // 计算并更新总分
        int score = calculateCredit(credit.getIdentityScore(),credit.getAssetScore(),credit.getCreditScore(),credit.getBehaviorScore(),credit.getSocialScore());
        creditService.updateTotalScore(score,userID);

        //从history_score中获取当前分数，并将分数返回到前端
        HistoryScore currentCredit = userService.selectUserCredit(userID);
        map.put("total_score", currentCredit.getTotalScore());
        map.put("identity_score", currentCredit.getIdentityScore());
        map.put("asset_score", currentCredit.getAssetScore());
        map.put("credit_score",currentCredit.getCreditScore());
        map.put("behavior_score", currentCredit.getBehaviorScore());
        map.put("social_score", currentCredit.getSocialScore());
        map.put("lastUpdateTime", currentCredit.getHistoryTime());

        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    /**
    总分更新
     */
    public int calculateCredit(int identity_score, int asset_score, int credit_score, int behavior_score, int social_score){
        double totalScore = identity_score+asset_score + credit_score + behavior_score + social_score;
        int score = (int)totalScore;
        return score;
    }

    @ApiOperation(value = "获取全部信用分数信息")
    @GetMapping("getAllCredit")
    public  Map<String, Object> getAllCredit() {
        Map<String, Object> map = new HashMap<>();
        // 计算信用分数各区间人数占比,最大最小，平均值
        List<Credit> creditList = creditService.getAllCredit();
//        System.out.println(creditList.get(0).getTotalScore());
        double totalScoreSum = 0;
        int maxCredit = creditList.get(0).getTotalScore();
        int minCredit = creditList.get(0).getTotalScore();
        //五个区间
        /* excellent430~500  * veryGood360~430   * good290~360   * fair220~290
        * poor150~220   * veryBad小于150*/
        int excellentCount = 0;
        int veryGoodCount = 0;
        int goodCount = 0;
        int fairCount = 0;
        int poorCount = 0;
        int veryBadCount = 0;
        for(int i = 0; i< creditList.size(); i++) {
            int score = creditList.get(i).getTotalScore();
            totalScoreSum += score;
            if(minCredit > score) {
                minCredit = score;
            }
            if(maxCredit < score) {
                maxCredit = score;
            }
            if(score < 150) {
                veryBadCount += 1;
            } else if(score < 220) {
                poorCount += 1;
            } else if(score < 290) {
                fairCount += 1;
            } else if(score < 360) {
                goodCount += 1;
            } else if(score < 430) {
                veryGoodCount += 1;
            } else {
                excellentCount += 1;
            }
        }
        totalScoreSum = totalScoreSum/creditList.size();
        map.put("totalCount", creditList.size());
        map.put("totalScoreSum", totalScoreSum);
        map.put("excellentCount", excellentCount);
        map.put("veryGoodCount", veryGoodCount);
        map.put("goodCount", goodCount);
        map.put("fairCount", fairCount);
        map.put("poorCount", poorCount);
        map.put("veryBadCount", veryBadCount);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
