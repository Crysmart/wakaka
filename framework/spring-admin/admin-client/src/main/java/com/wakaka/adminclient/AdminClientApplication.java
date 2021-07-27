package com.wakaka.adminclient;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}

	/**
	 * prometheus 采集注册
	 * @param applicationName
	 * @return
	 */
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
		return registry -> registry.config().commonTags("application", applicationName);
	}

}
