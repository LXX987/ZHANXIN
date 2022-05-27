package com.huaqi.zhanxin.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务的使用，每周更新一次历史分数表
 * @author lxt
 */

@Component
public class TimeTask {
    @Scheduled(cron = "0 0 12 ? * WED")
    //0 0 12 ? * WED    表示每个星期三中午12点执行
    public void execute() {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        // System.out.println("欢迎访问我的博客 " + df.format(new Date()));
        // 1. 根据数据库的历史分数表 和 用户当前的各项信息，自动计算所有人的信用分数
        // 2. 计算新的信用分数 比 历史分数 增长了多少（用在银行政府部分）
        // 3. 更新历史分数表，将计算到的分数存入历史分数表

    }
}
