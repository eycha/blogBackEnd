package com.tlc.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url="/")})
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String moduleName;
    @Value("${spring.application.desc}")
    private String description;

    @Bean
    public OpenAPI customOpenAPI() {
        final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));

        return new OpenAPI()
                .info(new Info().title(apiTitle).version("v1").description(description));
    }
}
