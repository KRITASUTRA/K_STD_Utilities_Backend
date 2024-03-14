package com.Utilities_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.Utilities_Backend.entity.DT_master;


@Repository
public interface DTMasterRepository extends JpaRepository<DT_master, Long> {


}


