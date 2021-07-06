package com.unipampa.scheduling.swagger.config;

import java.util.Optional;

import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(Predicates.not(PathSelectors.regex("/error")))
        .build()
        .apiInfo(metadata())
        .useDefaultResponseMessages(false)
        .tags(new Tag("users", "Operações sobre usuários"))
        .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()
        .title("Scheduling API")
        .description("API de agendamento.")
        .version("1.0.0")
        .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")
        .contact(new Contact("Auri Gabriel", "https://www.linkedin.com/in/auri-gabriel-castro-de-melo-486712196/","ririmello.a@gmail.com"))
        .build();
  }
}
