package com.wakaka.res.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务令牌解析配置
 * @author Crysmart
 * @date 2020/12/18 14:15
 */
@Configuration
public class ResServerConfig {

//    /**
//     * 通过远程进入校验服务器执行校验
//     * @return
//     */
//    @Bean
//    public ResourceServerTokenServices tokenService(){
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        services.setClientId("c1");
//        services.setClientSecret("secret");
//        return services;
//    }


}
