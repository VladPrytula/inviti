package com.inviti.repository.config;

import com.inviti.repository.annotations.ProductionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by vladyslavprytula on 8/14/14.
 */
@Configuration
@ProductionConfig
@PropertySource("classpath:com/inviti/repository/inviti-repository-local.properties")
public class PropertiesConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
