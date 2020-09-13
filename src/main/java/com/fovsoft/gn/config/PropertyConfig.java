package com.fovsoft.gn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:common.properties", encoding="UTF-8")
public class PropertyConfig {
}
