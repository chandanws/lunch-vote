package io.philipg.lunchvote.configuration;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationSwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lunch Vote REST API")
                .description("Lunch Vote REST API allows you to quickly and easily run ranked-ballot elections for deciding where to have lunch")
                .termsOfServiceUrl("http://philipg.io/")
                .license("MIT License")
                .licenseUrl("http://github.com/philipgold/lunch-vote/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}
