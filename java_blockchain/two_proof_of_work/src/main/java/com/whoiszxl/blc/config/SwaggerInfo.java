package com.whoiszxl.blc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

	private String groupName ="controller";

    private String basePackage;

    private String antPath;

    private String title = "Java公鏈";

    private String description = "blockchain 文档";

    private String license = "Apache License Version 2.0";
}
