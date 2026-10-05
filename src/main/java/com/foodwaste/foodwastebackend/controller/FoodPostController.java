package com.foodwaste.foodwastebackend.controller;


import com.foodwaste.foodwastebackend.entity.FoodPost;
import com.foodwaste.foodwastebackend.service.FoodPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-posts")
@CrossOrigin(origins = "https://foodwaste-frontend-xwi3.onrender.com")
public class FoodPostController {

    private final FoodPostService foodPostService;

    public FoodPostController(FoodPostService foodPostService) {
        this.foodPostService = foodPostService;
    }

    @PostMapping("/provider/{providerId}")
    public FoodPost addFoodPost(
            @PathVariable Long providerId,
            @RequestBody FoodPost foodPost) {

        return foodPostService.addFoodPost(foodPost, providerId);
    }

    @GetMapping
    public List<FoodPost> getAllFoodPosts() {
        return foodPostService.getAllFoodPosts();
    }

    @GetMapping("/available")
    public List<FoodPost> getAvailableFoodPosts() {
        return foodPostService.getAvailableFoodPosts();
    }

    @GetMapping("/{id}")
    public FoodPost getFoodPostById(
            @PathVariable Long id) {

        return foodPostService.getFoodPostById(id);
    }

    @PutMapping("/{id}")
    public FoodPost updateFoodPost(
            @PathVariable Long id,
            @RequestBody FoodPost foodPost) {

        return foodPostService.updateFoodPost(id, foodPost);
    }

    @DeleteMapping("/{id}")
    public String deleteFoodPost(
            @PathVariable Long id) {

        foodPostService.deleteFoodPost(id);

        return "Food post deleted successfully";
    }
}