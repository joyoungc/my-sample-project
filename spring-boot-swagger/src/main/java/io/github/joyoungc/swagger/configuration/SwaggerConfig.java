package io.github.joyoungc.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;


import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket mobileApiImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("1.Mobile API")
                .apiInfo(mobileApiInfo())
                .select()
                .paths(regex("/mobile.*"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    @Bean
    public Docket adminApiImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("2.Admin API")
                .apiInfo(adminApiInfo())
                .select()
                .paths(regex("/admin.*"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo mobileApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Swagger Page for Mobile")
                .description("개발된 API를 swagger를 이용해 실시간 html 문서로 반영하기")
//			.license("Apache 2.0")
//			.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//			.termsOfServiceUrl("")
                .version("1.0.1 RELEASE")
                .contact(new Contact("Joyoungc", "https://github.com/joyoungc/spring-boot-swagger", ""))
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Swagger Page for Admin")
                .description("개발된 API를 swagger를 이용해 실시간 html 문서로 반영하기")
                .version("0.1.1 SNAPSHOT")
                .contact(new Contact("Joyoungc", "https://github.com/joyoungc/spring-boot-swagger", ""))
                .build();
    }

}
