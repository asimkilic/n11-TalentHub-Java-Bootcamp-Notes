package com.asimkilic.springboot.springboottraining.jwt.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name="bearerAuth",
        type= SecuritySchemeType.HTTP,
        bearerFormat="JWT",
        scheme = "bearer"
)
@Configuration
public class SwaggerConfig {

}
