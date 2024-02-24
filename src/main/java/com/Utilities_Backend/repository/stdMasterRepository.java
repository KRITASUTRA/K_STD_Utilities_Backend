package com.Utilities_Backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.Utilities_Backend.entity.stdMaster;

@Repository
public interface stdMasterRepository extends JpaRepository<stdMaster, UUID>{

}
