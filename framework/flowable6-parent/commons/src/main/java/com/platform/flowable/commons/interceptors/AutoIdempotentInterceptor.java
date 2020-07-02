package com.platform.flowable.commons.interceptors;

import com.alibaba.fastjson.JSON;
import com.platform.flowable.commons.annotation.AutoIdempotent;
import com.platform.flowable.commons.service.ITokenService;
import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.result.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author wang.hm
 * @date 2020/3/10 10:45
 */
@Configuration
public class AutoIdempotentInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoIdempotentInterceptor.class);
    private final ITokenService iTokenService;

    @Autowired
    public AutoIdempotentInterceptor(ITokenService iTokenService) {
        this.iTokenService = iTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        //如果不是方法则不拦截
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AutoIdempotent methodAnnotation = handlerMethod.getMethodAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            PrintWriter writer = response.getWriter();
            try {
                boolean b = iTokenService.checkToken(request);
                LOGGER.info("验证token:{}",b);
                return b;
            }catch (Exception e){
                String jsonStr = JSON.toJSONString(new WebResponse(ResponseCode.ERROR.code(), e.getMessage(), null));
                writer.write(jsonStr);
                LOGGER.error("拦截出错:{}",e.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        return true;
    }
}
