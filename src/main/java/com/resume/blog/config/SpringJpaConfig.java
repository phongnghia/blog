package com.resume.blog.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories( basePackages = "com.resume.blog.repository.jpa" )
@EntityScan("com.resume.blog.entity.jpa")
@EnableJpaAuditing
public class SpringJpaConfig {
}
