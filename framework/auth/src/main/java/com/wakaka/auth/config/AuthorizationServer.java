package com.wakaka.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 授权服务
 * 获取授权码地址
 * http://localhost:8080/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=https://www.baidu.com
 * @author Crysmart
 * @date 2020/12/17 18:10
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    ClientDetailsService clientDetailsService;


    /**
     * 配置客户端详细信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        String secret = new BCryptPasswordEncoder().encode("secret");
        clients.inMemory()
                //client_id
                .withClient("c1")
                //客户端密钥
                .secret(secret)
                //资源列表
                .resourceIds("res1")
                //该client允许的授权类型
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                //允许的授权范围
                .scopes("all")
                //是否弹出确认授权页面
                .autoApprove(false)
                //验证回调地址
                .redirectUris("https://www.baidu.com");
    }

    /**
     * 访问端点、令牌生成
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){
        endpointsConfigurer
                //认证管理器
                .authenticationManager(authenticationManager)
                //授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 令牌管理服务
     * @return
     */
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices services = new DefaultTokenServices();
        //客户端详情服务，要知道是什么客户端访问
        services.setClientDetailsService(clientDetailsService);
        //刷新token
        services.setSupportRefreshToken(true);
        //设置token存储，目前演示是存储在内存中
        services.setTokenStore(tokenStore);
        //令牌时效：2小时（7200）
        services.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期：3天（259200）
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

    /**
     * 令牌端点的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer){
        securityConfigurer
                //oauth/token_key 可公开调用
                .tokenKeyAccess("permitAll()")
                //oauth/check_token  可公开调用
                .checkTokenAccess("permitAll()")
                //允许表单认证
                .allowFormAuthenticationForClients();
    }
}
