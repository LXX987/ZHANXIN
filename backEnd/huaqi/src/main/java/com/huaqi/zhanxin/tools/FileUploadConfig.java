package com.huaqi.zhanxin.tools;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小 KB,MB 大写
        factory.setMaxFileSize(DataSize.parse("50MB"));
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}
