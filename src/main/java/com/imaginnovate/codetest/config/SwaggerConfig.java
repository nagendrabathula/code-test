package com.imaginnovate.codetest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
//import io.swagger.models.parameters.Parameter
//  io.swagger.models.parameters.Parameter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
    	
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")
         .modelRef(new ModelRef("string"))
         .parameterType("header")
         .required(true).build();
        
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
    	
    	
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/api/**/*")) 
          .build()
          .globalOperationParameters(aParameters)
          .apiInfo(apiInfo());
          //.build();                                           
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("All About Apis")
            .description("Document For AAA")
            .version("1.0")
            .termsOfServiceUrl("http://terms-of-services.url")
            .license("LICENSE")
            .licenseUrl("http://url-to-license.com")
            .build();
    }
}