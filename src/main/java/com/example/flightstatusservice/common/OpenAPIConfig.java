package com.example.flightstatusservice.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI flightStatusOpenApi() {
		var info = new Info().title("Flight Status API")
				.description("OpenApi (Swagger) documentation auto generated from code").version("1.0");

		return new OpenAPI().info(info);
	}

}

