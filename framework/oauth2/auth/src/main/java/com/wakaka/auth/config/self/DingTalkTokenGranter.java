package com.wakaka.auth.config.self;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Crysmart
 * @date 2021/1/1 16:45
 */
public class DingTalkTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "ding_talk";

    /**
     * 加载本类配置
     * @param tokenServices
     * @param clientDetailsService
     * @param requestFactory
     */
    public DingTalkTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    /**
     * 抽象父类，加载本类配置
     * @param tokenServices
     * @param clientDetailsService
     * @param requestFactory
     * @param grantType
     */
    protected DingTalkTokenGranter(AuthorizationServerTokenServices tokenServices,
                                   ClientDetailsService clientDetailsService,
                                   OAuth2RequestFactory requestFactory,
                                   String grantType
                                   ) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        // Protect from downstream leaks of password
        String dingUserid = parameters.get("dingUserid");
        //Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);

        Authentication userAuth = new DingTalkAuthenticationToken(dingUserid,parameters, Arrays.asList(new SimpleGrantedAuthority("aaa")));
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            //userAuth = new DingTalkAuthenticationToken(dingUserid,parameters);
        }
        catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate dingUserid: " + dingUserid);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
