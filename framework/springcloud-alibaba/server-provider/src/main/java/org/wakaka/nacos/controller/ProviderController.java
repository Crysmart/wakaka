package org.wakaka.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/10/3 20:16
 */
@RestController
public class ProviderController {

    @GetMapping("/getServer")
    public String getServer(){
        return this.toString();
    }

}
