package com.foodwaste.foodwastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "collection_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_post_id")
    private FoodPost foodPost;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private User organization;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestedAt;

    public enum RequestStatus {
        PENDING,
        APPROVED,
        REJECTED,
        COMPLETED
    }
}