package com.demo_microservice.demo_rider_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder {
    private Long id;

    private String title;

    private Integer quantity;

    private LocalDateTime orderPlacedAt;
}

