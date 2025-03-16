package com.resume.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Blog Application APIs",
                description = "APIs Documentation for Blog Application",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class SwaggerConfig {
    @Value("${version}")
    private String version;

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder().group("blog-api").addOpenApiCustomizer(openApi -> openApi.getInfo().setVersion(version)).build();
    }

}
