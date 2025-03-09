package com.resume.blog.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories( basePackages = "com.resume.blog.repository.es" )
@EntityScan("com.resume.blog.repository.es")
public class SpringESConfig {
}
