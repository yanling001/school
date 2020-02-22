package com.example.demo.Config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaagerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
//                enable(ebable).select().apis(RequestHandlerSelectors.basePackage("com.demo.controller")). //扫描包
//                paths(PathSelectors.any()).build();
    }
}
