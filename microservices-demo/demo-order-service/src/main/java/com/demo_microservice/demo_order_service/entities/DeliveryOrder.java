package com.demo_microservice.demo_order_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false
    )
    private String title;

    @Column(
            nullable = false
    )
    private Integer quantity;

    @Column(
            name = "order_placed_at",
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime orderPlacedAt;
}
