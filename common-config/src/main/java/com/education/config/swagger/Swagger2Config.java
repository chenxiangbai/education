package com.education.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author admin
 * @Date 2020-08-18 15:59
 * @Version 1.0
 * @Description 接口文档引擎（http://localhost:8085/swagger-ui.html）
 */
@Configuration
public class Swagger2Config {
    @Value("${swagger.basePackage:com.education.controller}")
    private String basePackage;
    @Value("${swagger.title:简易试卷测试系统 API 文档}")
    private String title;
    @Value("${swagger.description:简易试卷测试系统 API 网关接口，使用resultFul Api 设计规范}")
    private String description;
    @Value("${swagger.termsOfServiceUrl:}")
    private String termsOfServiceUrl;
    @Value("${swagger.version:1.0.0}")
    private String version;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .build();
    }
}