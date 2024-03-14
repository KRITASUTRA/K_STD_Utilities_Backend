package com.Utilities_Backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.DT_assetdamageHistory;



@Repository

public interface DT_assetdamageRepository extends JpaRepository<DT_assetdamageHistory, Long> {
}

