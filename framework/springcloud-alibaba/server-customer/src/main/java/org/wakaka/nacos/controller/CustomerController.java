package org.wakaka.nacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wakaka.nacos.client.ProviderClient;

/**
 * @author Crysmart
 * @date 2020/10/3 20:43
 */
@RestController
public class CustomerController {

    @Autowired
    ProviderClient providerClient;

    @GetMapping("/getFeign")
    public String getFeign(){
        return providerClient.getServer() + "gettetetet";
    }
}
