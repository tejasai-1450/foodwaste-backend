package com.foodwaste.foodwastebackend.service;

import com.foodwaste.foodwastebackend.entity.CollectionRequest;
import com.foodwaste.foodwastebackend.entity.FoodPost;
import com.foodwaste.foodwastebackend.entity.User;
import com.foodwaste.foodwastebackend.repository.CollectionRequestRepository;
import com.foodwaste.foodwastebackend.repository.FoodPostRepository;
import com.foodwaste.foodwastebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CollectionRequestService {

    private final CollectionRequestRepository requestRepository;
    private final FoodPostRepository foodPostRepository;
    private final UserRepository userRepository;

    public CollectionRequestService(
            CollectionRequestRepository requestRepository,
            FoodPostRepository foodPostRepository,
            UserRepository userRepository) {

        this.requestRepository = requestRepository;
        this.foodPostRepository = foodPostRepository;
        this.userRepository = userRepository;
    }

    public CollectionRequest createRequest(
            Long foodPostId,
            Long organizationId) {

        FoodPost foodPost = foodPostRepository.findById(foodPostId)
                .orElseThrow(() ->
                        new RuntimeException("Food post not found"));

        User organization = userRepository.findById(organizationId)
                .orElseThrow(() ->
                        new RuntimeException("Organization not found"));

        if (foodPost.getStatus() != FoodPost.Status.AVAILABLE) {
            throw new RuntimeException("Food is not available");
        }

        CollectionRequest request = new CollectionRequest();

        request.setFoodPost(foodPost);
        request.setOrganization(organization);
        request.setStatus(
                CollectionRequest.RequestStatus.PENDING);
        request.setRequestedAt(LocalDateTime.now());

        foodPost.setStatus(FoodPost.Status.REQUESTED);
        foodPostRepository.save(foodPost);

        return requestRepository.save(request);
    }

    public List<CollectionRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public CollectionRequest updateStatus(
            Long id,
            CollectionRequest.RequestStatus status) {

        CollectionRequest request = requestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));

        request.setStatus(status);

        if (status == CollectionRequest.RequestStatus.COMPLETED) {
            FoodPost foodPost = request.getFoodPost();
            foodPost.setStatus(FoodPost.Status.COLLECTED);
            foodPostRepository.save(foodPost);
        }

        return requestRepository.save(request);
    }
}