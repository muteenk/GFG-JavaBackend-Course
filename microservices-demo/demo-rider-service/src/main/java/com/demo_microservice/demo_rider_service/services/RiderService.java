package com.demo_microservice.demo_rider_service.services;

import com.demo_microservice.demo_rider_service.clients.OrderServiceFeignClient;
import com.demo_microservice.demo_rider_service.entities.DeliveryOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.rmi.RemoteException;

@Service
@RequiredArgsConstructor
public class RiderService {

//    private final RestClient restClient;
        private final OrderServiceFeignClient orderClient;

    public DeliveryOrder getOrderForRider(Long orderId) {
//        return restClient.get()
//                .uri("/orders/{orderId}", orderId)
//                .retrieve()
//                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
//                    System.out.println("Nothing was found");
//                    throw new RemoteException("Nothing");
//                })
//                .body(DeliveryOrder.class);

        return orderClient.getOrder(orderId);
    }

    public String checkPort() {
        return orderClient.getPort();
    }
}
