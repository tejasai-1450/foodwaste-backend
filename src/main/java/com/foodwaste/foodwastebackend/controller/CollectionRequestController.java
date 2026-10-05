package com.foodwaste.foodwastebackend.controller;


import com.foodwaste.foodwastebackend.entity.CollectionRequest;
import com.foodwaste.foodwastebackend.service.CollectionRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class CollectionRequestController {

    private final CollectionRequestService requestService;

    public CollectionRequestController(
            CollectionRequestService requestService) {

        this.requestService = requestService;
    }

    @PostMapping
    public CollectionRequest createRequest(
            @RequestParam Long foodPostId,
            @RequestParam Long organizationId) {

        return requestService.createRequest(
                foodPostId,
                organizationId);
    }

    @GetMapping
    public List<CollectionRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PutMapping("/{id}/status")
    public CollectionRequest updateStatus(
            @PathVariable Long id,
            @RequestParam CollectionRequest.RequestStatus status) {

        return requestService.updateStatus(id, status);
    }
}