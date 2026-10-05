package com.foodwaste.foodwastebackend.service;


import com.foodwaste.foodwastebackend.entity.FoodPost;
import com.foodwaste.foodwastebackend.entity.User;
import com.foodwaste.foodwastebackend.repository.FoodPostRepository;
import com.foodwaste.foodwastebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodPostService {

    private final FoodPostRepository foodPostRepository;
    private final UserRepository userRepository;

    public FoodPostService(
            FoodPostRepository foodPostRepository,
            UserRepository userRepository) {

        this.foodPostRepository = foodPostRepository;
        this.userRepository = userRepository;
    }

    public FoodPost addFoodPost(
            FoodPost foodPost,
            Long providerId) {

        User provider = userRepository.findById(providerId)
                .orElseThrow(() ->
                        new RuntimeException("Provider not found"));

        foodPost.setProvider(provider);
        foodPost.setStatus(FoodPost.Status.AVAILABLE);

        return foodPostRepository.save(foodPost);
    }

    public List<FoodPost> getAllFoodPosts() {
        return foodPostRepository.findAll();
    }

    public List<FoodPost> getAvailableFoodPosts() {
        return foodPostRepository.findByStatus(
                FoodPost.Status.AVAILABLE);
    }

    public FoodPost getFoodPostById(Long id) {
        return foodPostRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Food post not found"));
    }

    public FoodPost updateFoodPost(
            Long id,
            FoodPost updatedFoodPost) {

        FoodPost existingFoodPost = getFoodPostById(id);

        existingFoodPost.setFoodName(
                updatedFoodPost.getFoodName());

        existingFoodPost.setQuantity(
                updatedFoodPost.getQuantity());

        existingFoodPost.setLocation(
                updatedFoodPost.getLocation());

        existingFoodPost.setExpiryTime(
                updatedFoodPost.getExpiryTime());

        return foodPostRepository.save(existingFoodPost);
    }

    public void deleteFoodPost(Long id) {
        foodPostRepository.deleteById(id);
    }
}