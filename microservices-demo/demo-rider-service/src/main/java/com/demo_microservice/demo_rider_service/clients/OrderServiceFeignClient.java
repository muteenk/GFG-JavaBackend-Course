package com.demo_microservice.demo_rider_service.clients;

import com.demo_microservice.demo_rider_service.config.OrderServiceFeignConfig;
import com.demo_microservice.demo_rider_service.entities.DeliveryOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", configuration = OrderServiceFeignConfig.class)
public interface OrderServiceFeignClient {
    @GetMapping("/orders/{orderId}")
    DeliveryOrder getOrder(@PathVariable Long orderId);

    @GetMapping("/orders/getPort")
    String getPort();
}
