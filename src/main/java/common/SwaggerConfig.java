package common;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/23 18:51
 */
@EnableWebMvc // spring boot 项目不需要添加此注解，SSM 项目需要加上此注解，否则将会报错。
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.leeyom.controller"))// controller路径。
                .paths(PathSelectors.any())
                .build();
    }

    // 配置 API 文档标题、描述、作者等等相关信息。
    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("校园全景图系统API接口文档")
                .termsOfServiceUrl("")
                .description("Restful API")
                .build();

    }
}
