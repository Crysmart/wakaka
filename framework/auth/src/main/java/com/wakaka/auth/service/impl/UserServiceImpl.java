package com.wakaka.auth.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author Crysmart
 * @date 2020/12/17 16:09
 */
@Service
public class UserServiceImpl implements UserDetailsService {


    /**
     * 查找用户，加入用户池登录成功
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        String user = "admin";
        String password = "123";
        boolean b = ObjectUtils.isEmpty(user);
        if (b){
            //如果用户不存在则抛出null，交由security provider进行异常抛出
            return null;
        }
        //查询出创建用户加载security中
        UserDetails userDetails = User.withUsername(user).password(password)
                //访问资源权限
                .authorities("1").build();
        return userDetails;
    }
}