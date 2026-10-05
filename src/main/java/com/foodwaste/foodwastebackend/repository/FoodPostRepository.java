package com.foodwaste.foodwastebackend.repository;


import com.foodwaste.foodwastebackend.entity.FoodPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodPostRepository
        extends JpaRepository<FoodPost, Long> {

    List<FoodPost> findByStatus(FoodPost.Status status);
}