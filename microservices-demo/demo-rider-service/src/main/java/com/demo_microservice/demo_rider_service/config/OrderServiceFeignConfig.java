package com.demo_microservice.demo_rider_service.config;

import com.demo_microservice.demo_rider_service.clients.decoders.OrderServiceErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new OrderServiceErrorDecoder();
    }

}
