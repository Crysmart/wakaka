package com.wakaka.feignapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("zipkin-server2")
public interface FeignTestService {

    @RequestMapping(value = "/server2", method = RequestMethod.GET)
    String function();

}
