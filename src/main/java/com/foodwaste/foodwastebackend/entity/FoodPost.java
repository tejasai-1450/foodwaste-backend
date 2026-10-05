package com.foodwaste.foodwastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;

    private Double quantity;

    private String location;

    private LocalDateTime expiryTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    public enum Status {
        AVAILABLE,
        REQUESTED,
        COLLECTED,
        EXPIRED
    }
}