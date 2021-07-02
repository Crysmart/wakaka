package com.wakaka.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrpcApplication {
	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);
	}

}
