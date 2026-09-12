package com.demo_microservice.demo_rider_service.clients.decoders;

import feign.Response;
import feign.codec.ErrorDecoder;

public class OrderServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 500:
                return new RuntimeException("Server Error");
            default:
                return defaultDecoder.decode(methodKey, response);

        }
    }
}
