package com.wakaka.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 授权方的安全访问配置
 * @author Crysmart
 * @date 2020/12/17 15:13
 */
@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 定义用户信息服务
     * @return
     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("aadd").password("123").authorities("p1").build());
//        return manager;
//    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制 http
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //跨站请求
                //.csrf().disable()
                .authorizeRequests()
                //规则匹配，“/r/*”必须认证通过
                .antMatchers("*").authenticated()
                //除开“/r/*”都可以访问
                .anyRequest().permitAll()
                .and()
                //允许表单登录
                .formLogin()

                .and()
                .logout();
                //自定义登录成功地址
                //.successForwardUrl("/welcome-index");
    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
