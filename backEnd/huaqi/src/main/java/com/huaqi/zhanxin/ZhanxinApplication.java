package com.huaqi.zhanxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 开启定时任务
@ServletComponentScan
public class ZhanxinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhanxinApplication.class, args);
    }

}
