package com.example.ImperiaConquest.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/hello").setViewName("hello");
        //registry.addViewController("/admin").setViewName("admin");
        //registry.addViewController("/user").setViewName("user");
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/").setViewName("login");
    }

}