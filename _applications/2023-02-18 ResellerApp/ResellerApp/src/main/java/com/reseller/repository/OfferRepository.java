package com.reseller.repository;

import com.reseller.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findByOwnerId(Long currentId);

    List<OfferEntity> findByBuyerId(Long currentId);

    List<OfferEntity> findByOwnerIdNotAndBuyerIdEquals(Long ownerId, Long buyerId);
}