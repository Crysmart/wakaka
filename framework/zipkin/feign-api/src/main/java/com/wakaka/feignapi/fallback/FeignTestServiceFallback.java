package com.wakaka.feignapi.fallback;

import com.wakaka.feignapi.FeignTestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignTestServiceFallback implements FallbackFactory<FeignTestService> {
    public FeignTestService create(Throwable throwable) {
        return new FeignTestService() {
            public String function() {
                return "fail";
            }
        };
    }
}
