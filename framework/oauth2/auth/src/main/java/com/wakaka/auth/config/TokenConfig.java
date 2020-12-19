package com.wakaka.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * token令牌配置文件
 * @author Crysmart
 * @date 2020/12/17 18:42
 */
@Configuration
public class TokenConfig {

    private static final String SIGNING_KEY = "test";

    /**
     * 内存存储token方案
     * 配置token
     * @return
     */
//    @Bean
//    public TokenStore tokenStore(){
//        return new InMemoryTokenStore();
//    }

    /**
     * JWT令牌存储方案
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //对称密钥，资源方，授权方需相同才能解
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }


//    /**
//     * 设置授权码模式的授权码如何存取，暂时采用内存方式
//     * @return
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new InMemoryAuthorizationCodeServices();
//    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
