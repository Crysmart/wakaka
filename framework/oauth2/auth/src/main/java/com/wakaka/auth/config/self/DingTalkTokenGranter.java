package com.wakaka.auth.config.self;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * @author Crysmart
 * @date 2021/1/1 16:45
 */
public class DingTalkTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "ding_talk";

    protected DingTalkTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }
}
