package org.wakaka.nacos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Crysmart
 * @date 2020/10/3 20:59
 */
@FeignClient(name = "server-provider")
public interface ProviderClient {

    @GetMapping("/getServer")
    String getServer();
}
