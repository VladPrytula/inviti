package com.inviti.rest.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vladyslavprytula on 8/11/14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.inviti.rest.controller","com.inviti.service","com.inviti.repository" })
public class TestContext extends WebMvcConfigurerAdapter {
    //Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //to load static resource
}
