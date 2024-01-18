//package com.Utilities_Backend.repository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
////import com.Utilities_Backend.entity.feeder;
//import com.Utilities_Backend.entity.pumpEnergy;
//
//@Repository
//public interface pumpEnergyRepository extends JpaRepository<pumpEnergy, Long> {
//
//}


package com.Utilities_Backend.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Utilities_Backend.entity.pumpEnergy;

@Repository
public interface pumpEnergyRepository extends JpaRepository<pumpEnergy, UUID> {


}
