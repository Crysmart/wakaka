package com.wakaka.res.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器配置
 * 请求资源时需通过headers添加Authorization参数值为： token_type access_token
 * Authorization = bearer afb524ae-88a8-472c-b862-1203e4c3c092 可行
 * Authorization = bearerafb524ae-88a8-472c-b862-1203e4c3c092  可行
 * {
 *     "access_token": "afb524ae-88a8-472c-b862-1203e4c3c092",
 *     "token_type": "bearer",
 *     "refresh_token": "920d1b7d-d270-4b4d-b377-4598f7966cca",
 *     "expires_in": 7199,
 *     "scope": "all"
 * }
 * @author Crysmart
 * @date 2020/12/18 14:06
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "res1";

//    @Autowired
//    ResourceServerTokenServices resourceServerTokenServices;
    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources
                //资源id
                .resourceId(RESOURCE_ID)
                //令牌验证服务
                .tokenStore(tokenStore)
                //.tokenServices(resourceServerTokenServices)
                //?
                .stateless(true);
    }

    /**
     * http安全配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //所有请求都要拦截/**，并且令牌scope为all时通过
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                //基于token，session不用记录了，STATELESS：不创建token，也不使用
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
