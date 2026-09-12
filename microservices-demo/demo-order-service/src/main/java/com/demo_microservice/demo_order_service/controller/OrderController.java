package com.demo_microservice.demo_order_service.controller;

import com.demo_microservice.demo_order_service.entities.DeliveryOrder;
import com.demo_microservice.demo_order_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Value("${server.port}") private int port;

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<DeliveryOrder> createOrder(@RequestBody DeliveryOrder deliveryOrder) {
        DeliveryOrder createdDeliveryOrder = orderService.createOrder(deliveryOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryOrder> getOrder(@PathVariable Long orderId) {
        DeliveryOrder deliveryOrder = orderService.getOrderById(orderId);
        return ResponseEntity.ok(deliveryOrder);
    }

    @GetMapping("/getPort")
    public ResponseEntity<String> getPort() {
        return ResponseEntity.ok("" + port);
    }

}
