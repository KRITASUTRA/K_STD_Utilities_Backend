package com.Utilities_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.DT_AssetInduction;



@Repository

public interface DT_assetInductionRepository extends JpaRepository<DT_AssetInduction, Long> {
}


