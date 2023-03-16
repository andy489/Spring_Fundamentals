package com.battleships.repository;

import com.battleships.domain.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findByName(String name);

    Optional<List<ShipEntity>> findAllByOwnerId(Long id);
    Optional<List<ShipEntity>> findAllByOwnerIdNot(Long id);

    List<ShipEntity> findAllByOrderByNameAscHealthAscPowerAsc();
}