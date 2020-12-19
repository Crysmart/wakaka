package com.wakaka.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务器
 *
 *
 * 授权码模式通常第三方使用，最复杂
 * 授权服务，授权码模式
 *
 * 获取授权码地址
 * response_type=code，授权码模式默认为code
 * http://localhost:8080/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=https://www.baidu.com
 *
 * 获取令牌
 * http://localhost:8080/oauth/token?client_id=c1&client_secret=secret&grant_type=authorization_code&code=?&redirect_uri=https://www.baidu.com
 * {
 *     "access_token": "c652b68a-3705-49e3-bad0-7683c9e8c2d5",//令牌
 *     "token_type": "bearer",
 *     "refresh_token": "2c934e02-70c1-44f5-8432-4d7996b6a5d6",//刷新令牌，当令牌无效通过此令牌重新刷新令牌
 *     "expires_in": 7199,
 *     "scope": "all"
 * }
 *
 * 校验令牌
 * http://localhost:8080/oauth/check_token?token=?
 *
 *
 *
 * 账号密码模式通常第一方使用，充分信任client，通过账密生成access_token
 * 客户端模式，对客户端完全信任，不需要账号密码直接获取access_token
 * 简化模式，单页面应用，通过url传递access_token
 *
 *
 *
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
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtAccessTokenConverter accessTokenConverter;
    @Autowired
    DataSource dataSource;


    /**
     * 配置客户端详细信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        //String secret = passwordEncoder.encode("secret");
        clients.withClientDetails(clientDetailsService());
//        clients.inMemory()
//                //client_id
//                .withClient("c1")
//                //客户端密钥
//                .secret(secret)
//                //资源列表
//                .resourceIds("res1")
//                //该client允许的授权类型
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
//                //允许的授权范围
//                .scopes("all")
//                //是否弹出确认授权页面
//                .autoApprove(false)
//                //验证回调地址
//                .redirectUris("https://www.baidu.com");
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
        services.setClientDetailsService(clientDetailsService());
        //刷新token
        services.setSupportRefreshToken(true);
        //设置token存储，目前演示是存储在内存中,如果tokenStore被修改则为修改后的对象
        services.setTokenStore(tokenStore);

        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

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

    /**
     * 从数据库读取客户端信息
     * @return
     */
    public ClientDetailsService clientDetailsService(){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }
}
