package com.Utilities_Backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.Schedule_timeslot;

@Repository
public interface TimeslotRepository extends JpaRepository<Schedule_timeslot, Long> {
    // Add custom query methods if needed
}