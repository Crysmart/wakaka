package com.wakaka.auth.config.self;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Crysmart
 * @date 2021/1/7 11:41
 */
public class DingTalkAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final Object credentials;

    /**
     * 自己实例
     * @param dingUserid
     */
    public DingTalkAuthenticationToken(Object dingUserid,Object credentials) {
        super(null);
        this.principal = dingUserid;
        this.credentials = credentials;
        //覆盖抽象
        super.setAuthenticated(true);
    }

    /**
     * 实例化
     */
    public DingTalkAuthenticationToken(Object dingUserid,Object credentials,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = dingUserid;
        this.credentials = credentials;
        //覆盖抽象
        super.setAuthenticated(true);
    }

    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
