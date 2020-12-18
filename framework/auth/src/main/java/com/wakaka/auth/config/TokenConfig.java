package com.wakaka.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * token令牌配置文件
 * @author Crysmart
 * @date 2020/12/17 18:42
 */
@Configuration
public class TokenConfig {


    /**
     * 配置token
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    /**
     * 设置授权码模式的授权码如何存取，暂时采用内存方式
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }
}
