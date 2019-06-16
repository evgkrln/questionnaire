package com.evgkrln.questionnaire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/questions").setViewName("questions");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/field").setViewName("field");
        registry.addViewController("/changepass").setViewName("changepass");
        registry.addViewController("/editprofile").setViewName("editprofile");
        registry.addViewController("/success").setViewName("success");
        registry.addViewController("/responses").setViewName("responses");
    }
}
