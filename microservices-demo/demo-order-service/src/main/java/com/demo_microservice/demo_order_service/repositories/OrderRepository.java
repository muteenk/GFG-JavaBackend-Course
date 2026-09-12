package com.demo_microservice.demo_order_service.repositories;

import com.demo_microservice.demo_order_service.entities.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {
}
