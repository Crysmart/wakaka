package com.wakaka.springsecurity.security;

import com.wakaka.springsecurity.domain.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Crysmart
 * @date 2021/3/8 17:56
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        final LoginUser loginUser = new LoginUser();
        return loginUser;
    }
}
