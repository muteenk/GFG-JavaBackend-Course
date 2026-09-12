package com.demo_microservice.demo_order_service.services;

import com.demo_microservice.demo_order_service.entities.DeliveryOrder;
import com.demo_microservice.demo_order_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public DeliveryOrder createOrder(DeliveryOrder deliveryOrder) {
        return orderRepository.save(deliveryOrder);
    }

    public DeliveryOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order Not Found !")
        );
    }
}
