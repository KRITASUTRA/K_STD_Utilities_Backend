package com.Utilities_Backend.repository;

//import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.Utilities_Backend.entity.userSTD;

@Repository
//public interface userSTDRepository extends JpaRepository<userSTD, UUID>{
//
//}
//public interface userSTDRepository extends JpaRepository<userSTD, Long> {
//    List<userSTD> findBySubDivisionName(String subDivisionName);
//}
public interface userSTDRepository extends JpaRepository<userSTD, String> {
	 List<userSTD> findBySubDivisionNameAndRSTName(String subDivisionName, String rstName);
	
    List<userSTD> findBySubDivisionName(String subDivisionName);
    List<userSTD> findByRSTName(String rstName);
    
    @Query("SELECT u FROM userSTD u WHERE u.FeederName = ?1")
    List<userSTD> findByFeederName(String FeederName);

   
    

}
