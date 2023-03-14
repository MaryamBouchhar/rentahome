package com.fpt.rentahome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173/")
                .allowedMethods("GET","POST");
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    }

}

