package com.platform.flowable.commons.service.impl;

import com.platform.flowable.commons.service.ITokenService;
import com.szb.platform.cache.api.ICacheService;
import com.szb.platform.cache.api.version.CacheVersion;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wang.hm
 * @date 2020/3/10 10:04
 */
@Service
public class TokenServiceImpl implements ITokenService {

   /* @Reference(version = CacheVersion.VERSION_1_0_0)
    private ICacheService iCacheService;*/

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public String createToken() {
        UUID token = UUID.randomUUID();
        if (!StringUtils.isEmpty(token.toString())) {
            //iCacheService.setCache(token.toString(), token.toString());
            //iCacheService.expire(token.toString(), 1, TimeUnit.HOURS);
            return token.toString();
        }
        return null;
    }

    /**
     * 检验token
     *
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        /*String cache = iCacheService.getCache(token);
        if (StringUtils.isEmpty(cache)) {
            return false;
        }
        iCacheService.setCache(token, g);*/
        return true;
    }
}
