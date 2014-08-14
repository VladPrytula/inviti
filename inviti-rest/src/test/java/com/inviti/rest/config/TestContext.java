package com.inviti.rest.config;

import com.inviti.model.User;
import com.inviti.service.basicservice.UserService;
import org.springframework.context.annotation.Bean;
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
@ComponentScan(basePackages = {"com.inviti.rest.controller"})
public class TestContext extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //TODO: this should be injected via mockito
    @Bean
    public UserService userService() {
        return new UserService() {
            @Override
            public void saveUser(User user) {
            }

            @Override
            public User findUser() {
                return new User();
            }

            @Override
            public User findUser(String name) {
                return new User();
            }
        };
    }
}
