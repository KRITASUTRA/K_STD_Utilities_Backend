package com.Utilities_Backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.Schedule_Outage;

@Repository
//import org.springframework.data.jpa.repository.JpaRepository;

public interface Schedule_outageRepository extends JpaRepository<Schedule_Outage, Long> {
    // You can add custom query methods here if needed
	
}
