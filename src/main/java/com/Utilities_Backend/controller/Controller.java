package com.Utilities_Backend.controller;

import com.Utilities_Backend.entity.feeder;
import com.Utilities_Backend.entity.receivingStation;
import com.Utilities_Backend.repository.feederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Utilities_Backend.repository.receivingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class Controller {
	 @Autowired
	    private  receivingRepository receiveRepository;

	@Autowired
	private feederRepository feederRepo;

	@PostMapping("/rstdata")
	public ResponseEntity<?> saveReceivingStation(@RequestBody receivingStation receiveST) {
		try {
//			String RSID = receivingRepository.getRSID();
			String stationName = receiveST.getStationName();
			String reason = receiveST.getReason();
			String lastUpdate = receiveST.getLastUpdate();
			String offTime = receiveST.getOffTime();
			String onTime = receiveST.getOnTime();

			boolean status = receiveST.isStatus();

// Create a new UserRequest entity
			receivingStation newRST = new receivingStation();
//			newRST.setFirstName(RSID);
			newRST.setStationName(stationName);
			newRST.setReason(reason);
			newRST.setLastUpdate(lastUpdate);
			newRST.setOffTime(offTime);
			newRST.setOnTime(onTime);
			newRST.setStatus(status);
			newRST.setRSId(UUID.randomUUID());

			// Save the UserRequest to the database
			receivingStation savedUserRequest = receiveRepository.save(newRST);
			return ResponseEntity.ok().body(savedUserRequest);
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}



	@PostMapping("/feeder")
	public ResponseEntity<?> saveFeeder(@RequestBody feeder Fd) {
		try {
        //   ObjectMapper objectMapper = new ObjectMapper();
       //  String json = objectMapper.writeValueAsString(Fd);
       //  System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);

			String FeederName = Fd.getFeederName();
			String Reason = Fd.getReason();
			String LastUpdate = Fd.getLastUpdate();
			String OffTime= Fd.getOffTime();
			String OnTime = Fd.getOnTime();
			UUID RSId = Fd.getReceivingStation().getRSId();

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



	  
}
