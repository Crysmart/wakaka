package com.wakaka.grpc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrpcApplication {
	public static void main(String[] args) {
		//SpringApplication.run(GrpcApplication.class, args);
		int a = 1000&1100;
		String s = Integer.toBinaryString(a);
		System.out.println(s);
		int i = Integer.parseInt("1001000");
		System.out.println(i);
	}

}
