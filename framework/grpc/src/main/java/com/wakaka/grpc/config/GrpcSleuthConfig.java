package com.wakaka.grpc.config;

import brave.Tracing;
import brave.grpc.GrpcTracing;
import io.grpc.ClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GlobalClientInterceptorConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

/**
 * 定义grpc链路拦截
 */
@Configuration
public class GrpcSleuthConfig {

    private static final Logger logger = LoggerFactory.getLogger(GrpcSleuthConfig.class);
    @Bean
    public GrpcTracing grpcTracing(Tracing tracing) {
        return GrpcTracing.create(tracing);
    }

    //We also create a client-side interceptor and put that in the context, this interceptor can then be injected into gRPC clients and
    //then applied to the managed channel.
    @Bean
    ClientInterceptor grpcClientSleuthInterceptor(GrpcTracing grpcTracing) {
        return grpcTracing.newClientInterceptor();
    }

    // Use this for debugging (or if there is no Zipkin server running on port 9411)
    @Bean
//    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public Reporter<Span> spanReporter() {
        return new Reporter<Span>() {
            @Override
            public void report(Span span) {
                logger.info("{}",span);
            }
        };
    }

    @Bean
    public GlobalClientInterceptorConfigurer globalInterceptorConfigurerAdapter(ClientInterceptor grpcClientSleuthInterceptor) {
        return registry -> registry.addClientInterceptors(grpcClientSleuthInterceptor);
    }

}