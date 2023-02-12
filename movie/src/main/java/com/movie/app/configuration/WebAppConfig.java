package com.movie.app.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.movie.app.interceptor.SecurityInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer{
  @Autowired
  SecurityInterceptor securityInterceptor;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(securityInterceptor)
    		.addPathPatterns("/admin/*","/user/*","/admin/user/*");
  }
}
