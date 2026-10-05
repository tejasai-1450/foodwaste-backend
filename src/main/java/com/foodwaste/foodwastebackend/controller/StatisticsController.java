package com.foodwaste.foodwastebackend.controller;


import com.foodwaste.foodwastebackend.entity.FoodPost;
import com.foodwaste.foodwastebackend.repository.FoodPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "https://foodwaste-frontend-xwi3.onrender.com")
public class StatisticsController {

    private final FoodPostRepository foodPostRepository;

    public StatisticsController(
            FoodPostRepository foodPostRepository) {

        this.foodPostRepository = foodPostRepository;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {

        List<FoodPost> posts = foodPostRepository.findAll();

        long totalPosts = posts.size();

        long availablePosts = posts.stream()
                .filter(p -> p.getStatus() == FoodPost.Status.AVAILABLE)
                .count();

        long collectedPosts = posts.stream()
                .filter(p -> p.getStatus() == FoodPost.Status.COLLECTED)
                .count();

        Map<String, Object> statistics = new HashMap<>();

        statistics.put("totalPosts", totalPosts);
        statistics.put("availablePosts", availablePosts);
        statistics.put("collectedPosts", collectedPosts);

        return statistics;
    }
}