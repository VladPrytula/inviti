package com.inviti.rest.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
@PropertySource("classpath:swagger.properties")
@ComponentScan("com.inviti.rest.controller")
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;
    @Value("${title}")
    private String title;
    @Value("${description}")
    private String description;
    @Value("${termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${contact}")
    private String contact;
    @Value("${license}")
    private String license;
    @Value("${licenseUrl}")
    private String licenseUrl;
    @Value("${applicationPath}")
    private String applicationPath;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo()).pathProvider(new SwaggerPathProvider() {
                    @Override
                    protected String applicationPath() {
                        return applicationPath;
                    }

                    @Override
                    protected String getDocumentationPath() {
                        return "/";
                    }
                });
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                title,
                description,
                termsOfServiceUrl,
                contact,
                license,
                licenseUrl
        );
        return apiInfo;
    }
}