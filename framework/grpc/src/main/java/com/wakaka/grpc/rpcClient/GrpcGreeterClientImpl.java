package com.wakaka.grpc.rpcClient;

import com.wakaka.grpc.api.GreeterGrpc;
import com.wakaka.grpc.api.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcGreeterClientImpl {

    @GrpcClient("grpc-test")
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return blockingStub.sayHello(request).getMessage();
    }
}
