package com.huaqi.zhanxin.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

    @Value("${path.picture-upload-path}")
    private String PICTURE_UPLOAD_PATH;

    @Value("${path.video-upload-path}")
    private String VIDEO_UPLOAD_PATH;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/**")
                .excludePathPatterns("/**");


//                .excludePathPatterns("/user/login","/user/register","/hello","/admin/**","***/error")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/upload/images/**")
                .addResourceLocations("file:"+PICTURE_UPLOAD_PATH);
        registry.addResourceHandler("/upload/videos/**")
                .addResourceLocations("file:"+VIDEO_UPLOAD_PATH);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOriginPatterns("*");
    }
}
