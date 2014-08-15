package com.inviti.rest.config;

import com.inviti.repository.annotations.ProductionConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vladyslavprytula on 8/11/14.
 */
@Configuration
@EnableWebMvc
/**
 * we have to exclude repository dbconfig, since it will use inMemoryDB and services would use remote host.could be reconfigured
 */
@ComponentScan(basePackages = {"com.inviti.rest.controller","com.inviti.service","com.inviti.repository" },
        excludeFilters = @ComponentScan.Filter(value = ProductionConfig.class,type = FilterType.ANNOTATION))
public class TestContext extends WebMvcConfigurerAdapter {
    //Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //to load static resource
}
