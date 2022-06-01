package com.huaqi.zhanxin.component;

import com.huaqi.zhanxin.controller.CreditController;
import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.entity.HistoryScore;
import com.huaqi.zhanxin.service.CreditService;
import com.huaqi.zhanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * 定时任务的使用，每周更新一次历史分数表
 * @author lxt
 */

@Component
public class TimeTask {
    @Autowired
    private UserService userService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private CreditController creditController;

    @Scheduled(cron = "0 */2 * * * ?")
    //0 0 12 ? * WED    表示每个星期三中午12点执行
    public void execute() {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        // System.out.println("欢迎访问我的博客 " + df.format(new Date()));

        // 1. 每周三更新历史表，并更新用户页面分数
        // 调用CreditController计算最新分数
        // 获取所有用户的id，遍历运行CreditService的更新分数
        System.out.print("开始更新");
        List<Integer> userIdList = userService.selectUserIdList();
        System.out.print("拉取历史分数");
        Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
        for(int userId: userIdList) {
            Credit credit = creditService.selectScore(userId);
            int score = creditController.calculateCredit(credit.getIdentityScore(),credit.getAssetScore(),
                    credit.getCreditScore(),credit.getBehaviorScore(),credit.getSocialScore());
            creditService.updateTotalScore(score,userId);

            // 计算分数（历史与当前）
            // 判断当前是否是第1次，第1次直接赋值，后续按9:1更新
            HistoryScore historyCredit = userService.selectUserCredit(userId);
            if(historyCredit.getTotalScore() == 0) {
                // 第一次更新分数
                userService.updateHistoryRecord(userId, credit.getTotalScore(), currentTIme,
                        credit.getIdentityScore(), credit.getAssetScore(), credit.getCreditScore(),
                        credit.getBehaviorScore(), credit.getSocialScore());
            } else {
                // 非第1次更新
                userService.updateHistoryRecord(userId,
                        (int)(historyCredit.getTotalScore()*0.9+credit.getTotalScore()*0.1), currentTIme,
                        (int)(historyCredit.getIdentityScore()*0.9 + credit.getIdentityScore()*0.1),
                        (int)(historyCredit.getAssetScore()*0.9 + credit.getAssetScore()*0.1),
                        (int)(historyCredit.getCreditScore()*0.9+credit.getCreditScore()*0.1),
                        (int)(historyCredit.getBehaviorScore()*0.9+credit.getBehaviorScore()*0.1),
                        (int)(historyCredit.getSocialScore()*0.9+credit.getSocialScore()*0.1));
            }
        }
        // 控制台输出提示
        System.out.print("--------每周三更新信用分数！--------" + currentTIme);
    }

}
