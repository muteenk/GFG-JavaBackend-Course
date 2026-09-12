package com.demo_microservice.demo_rider_service.controllers;

import com.demo_microservice.demo_rider_service.entities.DeliveryOrder;
import com.demo_microservice.demo_rider_service.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<DeliveryOrder> getOrderById(@PathVariable Long orderId) {
        DeliveryOrder deliveryOrder = riderService.getOrderForRider(orderId);
        return ResponseEntity.ok(deliveryOrder);
    }

    @GetMapping("/checkPort")
    public ResponseEntity<String> checkPort(){
        return ResponseEntity.ok(riderService.checkPort());
    }

}
