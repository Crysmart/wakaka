package com.wakaka.springsecurity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author Crysmart
 * @date 2021/3/8 17:57
 */
public class LoginUser implements UserDetails {
    /**
     * 用户唯一标识
     */
    private String token = "token";

    /**
     * 登录时间
     */
    private Long loginTime = 123L;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr = "ipaddr";

    /**
     * 登录地点
     */
    private String loginLocation = "chengdu";

    /**
     * 浏览器类型
     */
    private String browser = "chrome";

    /**
     * 操作系统
     */
    private String os = "os";

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public LoginUser()
    {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return "密码";
    }

    @Override
    public String getUsername()
    {
        return "账号";
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
