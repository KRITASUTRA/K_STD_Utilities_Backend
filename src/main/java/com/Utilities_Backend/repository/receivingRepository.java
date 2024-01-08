package com.Utilities_Backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.receivingStation;


@Repository
public interface  receivingRepository extends JpaRepository <receivingStation, UUID>{
    Optional<receivingStation> findById(UUID RSId);
}
