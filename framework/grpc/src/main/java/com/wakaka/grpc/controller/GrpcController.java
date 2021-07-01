package com.wakaka.grpc.controller;

import com.wakaka.grpc.rpcClient.GrpcGreeterClientImpl;
import com.wakaka.grpc.rpcClient.GrpcGreeterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcController {

    @Autowired
    GrpcGreeterClientImpl grpcGreeterClient;

    @GetMapping("grpc")
    public String fc(String name){
        return grpcGreeterClient.receiveGreeting(name);
    }
}
