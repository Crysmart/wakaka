package com.platform.flowable.commons.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wang.hm
 * @date 2020/3/10 10:04
 */
public interface ITokenService {
    /**
     * 创建token
     * @return
     */
    String createToken();
    /**
     * 检验token
     * @return
     */
    boolean checkToken(HttpServletRequest request);
}
