package com.Utilities_Backend.controller;

import com.Utilities_Backend.entity.feeder;
import com.Utilities_Backend.entity.pumpEnergy;
import com.Utilities_Backend.entity.receivingStation;
import com.Utilities_Backend.repository.feederRepository;
import com.Utilities_Backend.repository.pumpEnergyRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Utilities_Backend.repository.receivingRepository;
import com.Utilities_Backend.repository.stdMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class Controller {
	 @Autowired
	    private  receivingRepository receiveRepository;

	@Autowired
	private feederRepository feederRepo;
	
	@Autowired
	private pumpEnergyRepository pumpEnergyRepo;
	
	@Autowired
	private stdMasterRepository stdMasterRepo;
//===========================================ReceivingStation==========================================================
	@PostMapping("/rstdata")
	public ResponseEntity<?> saveReceivingStation(@RequestBody receivingStation receiveST) {
		try {
			String stationName = receiveST.getStationName();
			String reason = receiveST.getReason();
			String lastUpdate = receiveST.getLastUpdate();
			String offTime = receiveST.getOffTime();
			String onTime = receiveST.getOnTime();

			boolean status = receiveST.getStatus();
			receiveST.updateOnOffTime();

// Create a new UserRequest entity
			receivingStation newRST = new receivingStation();
			newRST.setStationName(stationName);
			newRST.setReason(reason);
			newRST.setLastUpdate(lastUpdate);
			newRST.setOffTime(offTime);
			newRST.setOnTime(onTime);
			newRST.setStatus(status);
			newRST.setRSId(UUID.randomUUID());

			// Save the UserRequest to the database
			receivingStation savedUserRequest = receiveRepository.save(receiveST);
			return ResponseEntity.ok().body(savedUserRequest);
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	
	
//    // Read all ReceivingStations
//    @GetMapping("/view_rstdata")
//    public ResponseEntity<?> getAllReceivingStations() {
//        try {
//            // Retrieve all ReceivingStations from the database
//            List allRSTs = (List) receiveRepository.findAll();
//            return ResponseEntity.ok().body(allRSTs);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    // Read a specific ReceivingStation by RSID
//    @GetMapping("/rstdata/{rsid}")
//    public ResponseEntity<?> getReceivingStationById(@PathVariable UUID rsid) {
//        try {
//            // Retrieve the ReceivingStation by RSID
//            Optional<receivingStation> rstOptional = receiveRepository.findById(rsid);
//
//            if (rstOptional.isPresent()) {
//                // ReceivingStation found
//                receivingStation rst = rstOptional.get();
//                return ResponseEntity.ok().body(rst);
//            } else {
//                // ReceivingStation with given RSID not found
//                return ResponseEntity.status(404).body("ReceivingStation not found with RSID: " + rsid);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }

    // Update existing ReceivingStation by RSID
//    @PutMapping("/rstdata/{rsid}")
//    public ResponseEntity<?> updateReceivingStation(@PathVariable UUID rsid, @RequestBody receivingStation receiveST) {
//        try {
//            // Check if the ReceivingStation with given RSID exists
//            Optional<receivingStation> existingRSTOptional = receiveRepository.findById(rsid);
//
//            if (existingRSTOptional.isPresent()) {
//                // Get the existing ReceivingStation entity
//                receivingStation existingRST = existingRSTOptional.get();
//
//                // Update the fields with new values
//                existingRST.setStationName(receiveST.getStationName());
//                existingRST.setReason(receiveST.getReason());
//                existingRST.setLastUpdate(receiveST.getLastUpdate());
//                existingRST.setOffTime(receiveST.getOffTime());
//                existingRST.setOnTime(receiveST.getOnTime());
//                existingRST.setStatus(receiveST.isStatus());
//
//                // Save the updated ReceivingStation to the database
//                receivingStation updatedRST = receiveRepository.save(existingRST);
//
//                return ResponseEntity.ok().body(updatedRST);
//            } else {
//                // ReceivingStation with given RSID not found
//                return ResponseEntity.status(404).body("ReceivingStation not found with RSID: " + rsid);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }


	@PutMapping("/rstdata/{rsid}")
	public ResponseEntity<?> updateReceivingStation(@PathVariable UUID rsid, @RequestBody receivingStation receiveST) {
	    try {
	        // Check if the ReceivingStation with the given RSID exists
	        Optional<receivingStation> existingRSTOptional = receiveRepository.findById(rsid);

	        if (existingRSTOptional.isPresent()) {
	            // Get the existing ReceivingStation entity
	            receivingStation existingRST = existingRSTOptional.get();

	            // Update the fields with new values if provided, otherwise retain existing values
	            if (receiveST.getStationName() != null) {
	                existingRST.setStationName(receiveST.getStationName());
	            }

	            if (receiveST.getReason() != null) {
	                existingRST.setReason(receiveST.getReason());
	            }

	            existingRST.setLastUpdate(
	                    receiveST.getLastUpdate() != null ? receiveST.getLastUpdate() : existingRST.getLastUpdate());
	            existingRST.setOffTime(receiveST.getOffTime() != null ? receiveST.getOffTime() : existingRST.getOffTime());
	            existingRST.setOnTime(receiveST.getOnTime() != null ? receiveST.getOnTime() : existingRST.getOnTime());

	            // Update status only if it's not null
	            if (receiveST.getStatus() != null) {
	                existingRST.setStatus(receiveST.getStatus());
	            }

	            // Update onTime and offTime based on status
	            existingRST.updateOnOffTime();

	            // Save the updated ReceivingStation to the database
	            receivingStation updatedRST = receiveRepository.save(existingRST);

	            return ResponseEntity.ok().body(updatedRST);
	        } else {
	            // ReceivingStation with the given RSID not found
	            return ResponseEntity.status(404).body("ReceivingStation not found with RSID: " + rsid);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exceptions
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}




	//===========================================Feeder=================================================================
	@PostMapping("/feeder")
	public ResponseEntity<?> saveFeeder(@RequestBody feeder Fd) {
		try {
//		   ObjectMapper objectMapper = new ObjectMapper();
//         String json = objectMapper.writeValueAsString(Fd);
//         System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);

			String FeederName = Fd.getFeederName();
			String Reason = Fd.getReason();
			String LastUpdate = Fd.getLastUpdate();
			String OffTime= Fd.getOffTime();
			String OnTime = Fd.getOnTime();
			UUID RSId = Fd.getReceivingStation().getRSId();
			boolean status = Fd.isStatus();

			Optional<receivingStation> optionalreceivingStation = receiveRepository.findById(RSId);
			if (optionalreceivingStation.isPresent()) {
				receivingStation ReceivingStation = optionalreceivingStation.get();

				feeder newFeeder = new feeder();
				newFeeder.setFeederName(FeederName);
				newFeeder.setReason(Reason);
				newFeeder.setLastUpdate(LastUpdate);
				newFeeder.setOffTime(OffTime);
				newFeeder.setOnTime(OnTime);
				newFeeder.setReceivingStation(ReceivingStation);
				newFeeder.setStatus(status);
				newFeeder.updateFeederStatus();

				feeder savedFeeder = feederRepo.save(newFeeder);

				return ResponseEntity.ok().body(savedFeeder);
			} else {
				return ResponseEntity.status(404).body("User not found with userId: " + RSId);
			}
		} catch (Exception e) {
			
				e.printStackTrace();
				return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	//private boolean isUniqueConstraintViolation(Exception e) {
		// Check if the exception is due to a unique constraint violation
	//	return e instanceof org.springframework.dao.DataIntegrityViolationException;
	//}




@PostMapping("/pumpEnergy")
public ResponseEntity<?> savePumpEnergy(@RequestBody pumpEnergy energy) {
	try {
		String RST = energy.getRST();
		String subDivision = energy.getSubDivision();
		String feeder = energy.getFeeder();
		String Total_supply = energy.getTotalSupply();
		String KW = energy.getKW();
	String energyDate = energy.getEnergyDate();

//Create a new UserRequest entity
		pumpEnergy newEnergy = new pumpEnergy();
		newEnergy.setRST(RST);
		newEnergy.setSubDivision(subDivision);
		newEnergy.setFeeder(feeder);
		newEnergy.setTotalSupply(Total_supply);
		newEnergy.setKW(KW);
//		newEnergy.setEnergy_date(new Date());
		newEnergy.setEnergyDate(energyDate);

		// Save the UserRequest to the database
		pumpEnergy saved = pumpEnergyRepo.save(newEnergy);
		return ResponseEntity.ok().body(saved);
	} catch (Exception e) {
		e.printStackTrace();
		// Handle exceptions
		return ResponseEntity.status(500).body("Internal Server Error");
	}
}

@GetMapping("/getAllPumpEnergy")
public ResponseEntity<?> getAllPumpEnergy() {
    try {
        List<pumpEnergy> allEnergy = pumpEnergyRepo.findAll();
        
        if (!allEnergy.isEmpty()) {
            return ResponseEntity.ok().body(allEnergy);
        } else {
            return ResponseEntity.status(404).body("No pump energy data found");
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body("Internal Server Error");
    }
}




}




