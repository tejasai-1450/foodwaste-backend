package com.foodwaste.foodwastebackend.repository;


import com.foodwaste.foodwastebackend.entity.CollectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRequestRepository
        extends JpaRepository<CollectionRequest, Long> {

    List<CollectionRequest> findByOrganizationId(Long organizationId);
}