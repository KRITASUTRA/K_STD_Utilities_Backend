package com.Utilities_Backend.controller;

import com.Utilities_Backend.entity.DT_AssetInduction;
import com.Utilities_Backend.entity.DT_master;
import com.Utilities_Backend.entity.Schedule_Outage;
import com.Utilities_Backend.entity.Schedule_timeslot;
import com.Utilities_Backend.entity.feeder;
import com.Utilities_Backend.entity.pumpEnergy;
import com.Utilities_Backend.entity.receivingStation;
import com.Utilities_Backend.entity.userSTD;
import com.Utilities_Backend.repository.DTMasterRepository;
import com.Utilities_Backend.repository.DT_assetInductionRepository;
import com.Utilities_Backend.repository.feederRepository;
import com.Utilities_Backend.repository.pumpEnergyRepository;
import com.Utilities_Backend.repository.DT_assetdamageRepository;
//import com.Utilities_Backend.repository.TimeslotRepository;
import com.Utilities_Backend.repository.Schedule_outageRepository;
import com.Utilities_Backend.repository.TimeslotRepository;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.Utilities_Backend.repository.userSTDRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestParam;
//import org.hibernate.boot.jaxb.JaxbLogger_.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;




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
	
	@Autowired
	private userSTDRepository userSTDRepo;
	
	@Autowired
	private DTMasterRepository dtMasterRepo;
	
	@Autowired
	private  DT_assetdamageRepository DT_assetdamageRepo;
	
	@Autowired
	private  DT_assetInductionRepository DT_assetInductionRepo;
	
	@Autowired
	private TimeslotRepository  TimeslotRepo;
	
	@Autowired
	private	Schedule_outageRepository Schedule_outageRepo;

   
//    public Controller(DT_assetdamageRepository assetdamageRepository) {
//        this.assetdamageRepository = assetdamageRepository;
//    }
//	
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


@PostMapping("/users")
public ResponseEntity<?> createUser(@RequestBody userSTD newUser) {
   
	try {
		 // Extracting values from the newUser object
        String user = newUser.getUser();
        String subDivisionName = newUser.getSubDivisionName();
        String RSTName = newUser.getRSTName();
        String FeederName = newUser.getFeederName();
        String split = newUser.getSplit();
        String subDivisionId = newUser.getSubDivisionId();
        String RSTId = newUser.getRSTId();
        String FeederId = newUser.getFeederId();
        String gridname = newUser.getGridname();
        String rstcapacity = newUser.getRstcapacity();
        String Feed_Area = newUser.getFeed_Area();
        String Feeder_Peak_load = newUser.getFeeder_Peak_load();
        String Sub_Transmission_Division = newUser.getSub_Transmission_Division();
        String Feeder_Number = newUser.getFeeder_Number();

        // Create a new UserSTD entity
        userSTD UserSTD = new userSTD();
        UserSTD.setUser(user);
        UserSTD.setSubDivisionName(subDivisionName);
        UserSTD.setRSTName(RSTName);
        UserSTD.setFeederName(FeederName);
        UserSTD.setSplit(split);
        UserSTD.setSubDivisionId(subDivisionId);
        UserSTD.setRSTId(RSTId);
        UserSTD.setFeederId(FeederId);
        UserSTD.setGridname(gridname);
        UserSTD.setRstcapacity(rstcapacity);
        UserSTD.setFeed_Area(Feed_Area);
        UserSTD.setFeeder_Peak_load(Feeder_Peak_load);
        UserSTD.setSub_Transmission_Division(Sub_Transmission_Division);
        UserSTD.setFeeder_Number(Feeder_Number);

		// Save the UserRequest to the database
		userSTD saved = userSTDRepo.save(UserSTD);
		return ResponseEntity.ok().body(saved);
	} catch (Exception e) {
		e.printStackTrace();
		// Handle exceptions
		return ResponseEntity.status(500).body("Internal Server Error");
	}
}
@GetMapping("/subdivisions")
public ResponseEntity<List<?>> getSubDivisions() {
    try {
        // Retrieve distinct subDivisionName values from the database
        List<?> subDivisions = userSTDRepo.findAll()
            .stream()
            .map(userSTD::getSubDivisionName)
            .distinct()
            .collect(Collectors.toList());
            
        return ResponseEntity.ok().body(subDivisions);
    } catch (Exception e) {
		e.printStackTrace();
		// Handle exceptions
		return ResponseEntity.status(500).body(null);
	}
}

@GetMapping ("/Usersview")
public ResponseEntity<List<userSTD>> getAllUsers() {
    try {
        // Retrieve all users from the database
        List<userSTD> users = userSTDRepo.findAll();
        return ResponseEntity.ok().body(users);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body(null);
    }
}
@GetMapping("/RSTandFeeder")
public ResponseEntity<?> getRSTOrFeeders(@RequestParam(required = false) String RSTName) {
    try {
        if (RSTName != null) {
            // Retrieve feeders for the specified RSTName
            List<String> feeders = userSTDRepo.findByRSTName(RSTName)
                    .stream()
                    .map(userSTD::getFeederName)
                    .distinct()
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(feeders);
        } else {
            // Retrieve distinct RSTName values from the database
            List<String> RSTNames = userSTDRepo.findAll()
                    .stream()
                    .map(userSTD::getRSTName)
                    .distinct()
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(RSTNames);
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body("Internal Server Error");
    }
}
//@GetMapping("/feederDetails")
//public ResponseEntity<?> getFeederDetails(@RequestParam String FeederName) {
//    try {
//        // Retrieve details for the specified feederName
//        List<userSTD> feederDetails = userSTDRepo.findByFeederName(FeederName);
//        
//        if (feederDetails.isEmpty()) {
//            return ResponseEntity.notFound().build(); // Return 404 if no details found
//        }
//
//        // Extract the relevant details from the fetched data
//        List<Map<String, String>> detailsList = new ArrayList<>();
//        for (userSTD user : feederDetails) {
//            Map<String, String> details = new HashMap<>();
//            details.put("subDivisionName", user.getSubDivisionName());
////            details.put("RSTName", user.getRSTName());
////            details.put("FeederName", user.getFeederName());
//            details.put("split", user.getSplit());
//            details.put("gridname", user.getGridname());
//            details.put("rstcapacity", user.getRstcapacity());
//            details.put("Feed_Area", user.getFeed_Area());
//            details.put("Feeder_Peak_load", user.getFeeder_Peak_load());
//            details.put("Sub_Transmission_Division", user.getSub_Transmission_Division());
//            details.put("Feeder_Number", user.getFeeder_Number());
//            detailsList.add(details);
//        }
//
//        return ResponseEntity.ok().body(detailsList);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Handle exceptions
//        return ResponseEntity.status(500).body("Internal Server Error");
//    }
//}




       



//@GetMapping("/rstnames")
//public ResponseEntity<List<String>> getRSTNamesBySubDivisionName(@RequestParam String subdivision ) {
//    try {
//      
//        List<String> rstNames = userSTDRepo.findBySubDivisionName(subdivision)
//            .stream()
//            .map(userSTD::getRSTName)
//            .distinct()
//            .collect(Collectors.toList());
//            
//        return ResponseEntity.ok().body(rstNames);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Handle exceptions
//        return ResponseEntity.status(500).body(null);
//    }
//}
//private static final Logger logger = LoggerFactory.getLogger(userSTD.class);

//@GetMapping("/rstnames")
//public ResponseEntity<List<String>> getRSTNamesBySubDivisionName(@RequestParam String SubDivisionName) {
//    try {
//        logger.info("Received request to fetch RST names for subdivision: {}", SubDivisionName);
//
//        List<String> rstNames = userSTDRepo.findBySubDivisionName(SubDivisionName)
//            .stream() 
//            .map(userSTD::getRSTName)
//            .distinct()
//            .collect(Collectors.toList());
//
//        logger.info("Found {} unique RST names for subdivision: {}", rstNames.size(), SubDivisionName);
//
//        return ResponseEntity.ok().body(rstNames);
//    } catch (Exception e) {
//        logger.error("An error occurred while fetching RST names for subdivision: {}", SubDivisionName, e);
//        return ResponseEntity.status(500).body(null);
//    }
//}


//private static final Logger logger = LoggerFactory.getLogger(userSTD.class);
//
//@GetMapping("/rstnames")
//public ResponseEntity<List<String>> getFeedersBySubdivisionAndRSTName(
//        @RequestParam(required = false) String SubDivisionName,
//        @RequestParam(required = false) String RSTName) {
//    try {
//        logger.info("Received request to fetch feeders for subdivision: {} and RST name: {}", SubDivisionName, RSTName);
//
//        List<String> FeederName;
//
//        if (SubDivisionName != null && RSTName != null) {
//            FeederName = userSTDRepo.findBySubDivisionNameAndRSTName(SubDivisionName, RSTName)
//                            .stream()
//                            .map(userSTD::getFeederName)
//                            .distinct()
//                            .collect(Collectors.toList());
//        } else if (SubDivisionName != null) {
//            FeederName = userSTDRepo.findBySubDivisionName(SubDivisionName)
//                            .stream()
//                            .map(userSTD::getFeederName)
//                            .distinct()
//                            .collect(Collectors.toList());
//        } else if (RSTName != null) {
//            // Here you need to define a new repository method findByRSTName in your repository interface
//            FeederName = userSTDRepo.findByRSTName(RSTName)
//                            .stream()
//                            .map(userSTD::getFeederName)
//                            .distinct()
//                            .collect(Collectors.toList());
//        } else {
//            FeederName = userSTDRepo.findAll()
//                            .stream()
//                            .map(userSTD::getFeederName)
//                            .distinct()
//                            .collect(Collectors.toList());
//        }
//        logger.info("Found {} unique feeders for SubDivisionName: {} and RSTname: {}", FeederName.size(), SubDivisionName, RSTName);
//
//        return ResponseEntity.ok().body(FeederName);
//    } catch (Exception e) {
//        logger.error("An error occurred while fetching feeders for SubDivisionName: {} and RSTname: {}", SubDivisionName, RSTName, e);
//        return ResponseEntity.status(500).body(null);
//    }
//}

//@GetMapping("/RST")
//public ResponseEntity<Map<String, List<String>>> getRSTAndFeeders() {
//    try {
//        // Retrieve distinct RSTName values from the database
//        List<String> RSTNames = userSTDRepo.findAll()
//            .stream()
//            .map(userSTD::getRSTName)
//            .distinct()
//            .collect(Collectors.toList());
//
//        // Create a map to store RSTNames and their corresponding feeders
//        Map<String, List<String>> RSTAndFeeders = new HashMap<>();
//        
//        // Iterate over each RSTName
//        for (String RSTName : RSTNames) {
//            // Retrieve feeders for the selected RSTName
//            List<String> feeders = userSTDRepo.findByRSTName(RSTName)
//                .stream()
//                .map(userSTD::getFeederName)
//                .distinct()
//                .collect(Collectors.toList());
//            
//            // Add RSTName and its corresponding feeders to the map
//            RSTAndFeeders.put(RSTName, feeders);
//        }
//
//        return ResponseEntity.ok().body(RSTAndFeeders);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Handle exceptions
//        return ResponseEntity.status(500).body(null);
//    }
//}

@GetMapping("/RST")
public ResponseEntity<Map<String, List<Map<String, String>>>> getRSTAndFeedersWithCapacity() {
    try {
        // Retrieve distinct RSTName values from the database
        List<String> RSTNames = userSTDRepo.findAll()
            .stream()
            .map(userSTD::getRSTName)
            .distinct()
            .collect(Collectors.toList());

        // Create a map to store RSTNames and their corresponding feeders with capacity
        Map<String, List<Map<String, String>>> RSTAndFeedersWithCapacity = new HashMap<>();
        
        // Iterate over each RSTName
        for (String RSTName : RSTNames) {
            // Retrieve feeders for the selected RSTName
            List<userSTD> userSTDList = userSTDRepo.findByRSTName(RSTName);
            List<Map<String, String>> feedersWithCapacity = new ArrayList<>();
            for (userSTD userSTD : userSTDList) {
                Map<String, String> feederDetails = new HashMap<>();
                feederDetails.put("FeederName", userSTD.getFeederName());
                feederDetails.put("rstcapacity", userSTD.getRstcapacity()); 
                feederDetails.put("subDivisionName", userSTD.getSubDivisionName());
                feederDetails.put("subDivisionId", userSTD.getSubDivisionId());
                feederDetails.put("RSTName", userSTD.getRSTName());
                feederDetails.put("gridname", userSTD.getGridname());
                feederDetails.put("Feed_Area", userSTD.getFeed_Area());
                feederDetails.put("Feeder_Peak_load", userSTD.getFeeder_Peak_load());
                feederDetails.put("Sub_Transmission_Division", userSTD.getSub_Transmission_Division());
                feederDetails.put("Feeder_Number", userSTD.getFeeder_Number());// Assuming capacity is a field in userSTD entity
                // Add additional fields as needed
                feedersWithCapacity.add(feederDetails);
            }
            
            // Add RSTName and its corresponding feeders with capacity to the map
            RSTAndFeedersWithCapacity.put(RSTName, feedersWithCapacity);
        }

        return ResponseEntity.ok().body(RSTAndFeedersWithCapacity);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body(null);
    }
}


@GetMapping("/Subdivision")
public ResponseEntity<Map<String, List<Map<String, String>>>> getSubdivisionWithCapacity() {
    try {
        // Retrieve distinct RSTName values from the database
        List<String> subDivisionNames = userSTDRepo.findAll()
            .stream()
            .map(userSTD::getSubDivisionName)
            .distinct()
            .collect(Collectors.toList());

        // Create a map to store RSTNames and their corresponding feeders with capacity
        Map<String, List<Map<String, String>>> SubdivisionWithCapacity = new HashMap<>();
        
        // Iterate over each RSTName
        for (String RSTName : subDivisionNames) {
            // Retrieve feeders for the selected RSTName
            List<userSTD> userSTDList = userSTDRepo.findBySubDivisionName(RSTName);
            List<Map<String, String>> feedersWithCapacity = new ArrayList<>();
            for (userSTD userSTD : userSTDList) {
                Map<String, String> feederDetails = new HashMap<>();
                feederDetails.put("FeederName", userSTD.getFeederName());
                feederDetails.put("rstcapacity", userSTD.getRstcapacity()); 
                feederDetails.put("subDivisionName", userSTD.getSubDivisionName());
                feederDetails.put("subDivisionId", userSTD.getSubDivisionId());
                feederDetails.put("RSTName", userSTD.getRSTName());
                feederDetails.put("gridname", userSTD.getGridname());
                feederDetails.put("Feed_Area", userSTD.getFeed_Area());
                feederDetails.put("Feeder_Peak_load", userSTD.getFeeder_Peak_load());
                feederDetails.put("Sub_Transmission_Division", userSTD.getSub_Transmission_Division());
                feederDetails.put("Feeder_Number", userSTD.getFeeder_Number());// Assuming capacity is a field in userSTD entity
                // Add additional fields as needed
                feedersWithCapacity.add(feederDetails);
            }
            
            // Add RSTName and its corresponding feeders with capacity to the map
            SubdivisionWithCapacity.put(RSTName, feedersWithCapacity);
        }

        return ResponseEntity.ok().body(SubdivisionWithCapacity);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body(null);
    }
}

@GetMapping("/SubdivisionName")
public ResponseEntity<Map<String, List<Map<String, String>>>> getSubdivisionNameWithCapacity() {
    try {
        // Retrieve distinct SubDivisionName values from the database
        List<String> subDivisionNames = userSTDRepo.findAll()
            .stream()
            .map(userSTD::getSubDivisionName)
            .distinct()
            .collect(Collectors.toList());

        // Create a map to store SubDivisionNames and their corresponding feeders with capacity
        Map<String, List<Map<String, String>>> SubdivisionNameWithCapacity = new HashMap<>();
        
        // Iterate over each SubDivisionName
        for (String subDivisionName : subDivisionNames) {
            // Retrieve feeders for the selected SubDivisionName
            List<userSTD> userSTDList = userSTDRepo.findBySubDivisionName(subDivisionName);
            List<Map<String, String>> feedersWithCapacity = new ArrayList<>();
            for (userSTD userSTD : userSTDList) {
                Map<String, String> feederDetails = new HashMap<>();
                feederDetails.put("FeederName", userSTD.getFeederName());
                feederDetails.put("rstcapacity", userSTD.getRstcapacity()); 
                feederDetails.put("subDivisionName", userSTD.getSubDivisionName());
                feederDetails.put("subDivisionId", userSTD.getSubDivisionId());
                feederDetails.put("RSTName", userSTD.getRSTName());
                feederDetails.put("gridname", userSTD.getGridname());
                feederDetails.put("Feed_Area", userSTD.getFeed_Area());
                feederDetails.put("Feeder_Peak_load", userSTD.getFeeder_Peak_load());
                feederDetails.put("Sub_Transmission_Division", userSTD.getSub_Transmission_Division());
                feederDetails.put("Feeder_Number", userSTD.getFeeder_Number());// Assuming capacity is a field in userSTD entity
                // Add additional fields as needed
                feedersWithCapacity.add(feederDetails);
            }
            
            // Add SubDivisionName and its corresponding feeders with capacity to the map
            SubdivisionNameWithCapacity.put(subDivisionName, feedersWithCapacity);
        }

        return ResponseEntity.ok().body(SubdivisionNameWithCapacity);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body(null);
    }
}


private static final Logger logger = LoggerFactory.getLogger(userSTD.class);

@GetMapping("/rstnames")
public ResponseEntity<Map<String, List<String>>> getFeedersBySubdivisionAndRSTName(
        @RequestParam(required = false) String SubDivisionName,
        @RequestParam(required = false) String RSTName) {  
    try {
        logger.info("Received request to fetch feeders for subdivision: {} and RST name: {}", SubDivisionName, RSTName);

        Map<String, List<String>> result = new HashMap<>();

        if (SubDivisionName != null) {
            List<String> rstNames = userSTDRepo.findBySubDivisionName(SubDivisionName)
                    .stream()
                    .map(userSTD::getRSTName)
                    .distinct()
                    .collect(Collectors.toList());

            result.put("RSTNames", rstNames);

            if (RSTName != null) {
                List<String> feeders = userSTDRepo.findBySubDivisionNameAndRSTName(SubDivisionName, RSTName)
                        .stream()
                        .map(userSTD::getFeederName)
                        .distinct()
                        .collect(Collectors.toList());
                result.put("Feeders", feeders);
            }
        }

        logger.info("Found {} unique feeders for SubDivisionName: {} and RSTname: {}", result.get("Feeders").size(), SubDivisionName, RSTName);

        return ResponseEntity.ok().body(result);
    } catch (Exception e) {
        logger.error("An error occurred while fetching feeders for SubDivisionName: {} and RSTname: {}", SubDivisionName, RSTName, e);
        return ResponseEntity.status(500).body(null);
    }

    
}




@PostMapping("/dt_master")
public ResponseEntity<?> saveDTMaster(@RequestBody DT_master dtMaster) {
    try {
        // Extract data from dtMaster
        String dtName = dtMaster.getDT_Name();
        String dtCode = dtMaster.getDT_code();
        String officeCode = dtMaster.getOffice_code();
        String assetCode = dtMaster.getAsset_code();
        String location = dtMaster.getLocation();
        String capacity = dtMaster.getCapacity();
        String makeId = dtMaster.getMake_ID();
        String manufactureYear = dtMaster.getManufacture_year();
        String punchId = dtMaster.getPunch_ID();
        String installedOn = dtMaster.getInstalled_ON();
        String installedBy = dtMaster.getInstalled_by();

        // Create a new DT_Master entity
        DT_master newDTMaster = new DT_master();
        newDTMaster.setDT_Name(dtName);
        newDTMaster.setDT_code(dtCode);
        newDTMaster.setOffice_code(officeCode);
        newDTMaster.setAsset_code(assetCode);
        newDTMaster.setLocation(location);
        newDTMaster.setCapacity(capacity);
        newDTMaster.setMake_ID(makeId);
        newDTMaster.setManufacture_year(manufactureYear);
        newDTMaster.setPunch_ID(punchId);
        newDTMaster.setInstalled_ON(installedOn);
        newDTMaster.setInstalled_by(installedBy);

        // Save the DT_Master entity
        DT_master saved = dtMasterRepo.save(newDTMaster);
		return ResponseEntity.ok().body(saved);
	} catch (Exception e) {
    
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}

@PostMapping("/assetInduction")
public ResponseEntity<?> createAssetInduction(@RequestBody DT_AssetInduction newAssetInduction) {
    try {
//    	Long assetCode = newAssetInduction.generateAssetCode();
    	String dtName = newAssetInduction.getDT_Name();
        String dtCode = newAssetInduction.getDT_code();
        String capacity = newAssetInduction.getCapacity();
        String yearOfMake = newAssetInduction.getYearOfMake();
        String transformerMake = newAssetInduction.getTransformerMake();
        String remark = newAssetInduction.getRemark();
        String locationID = newAssetInduction.getLocation_ID();
        String locationName = newAssetInduction.getLocation_name();
        

        // Create a new DT_AssetInduction entity
        DT_AssetInduction assetInduction = new DT_AssetInduction();
//        assetInduction.setAsset_Code(assetCode);
        assetInduction.setDT_Name(dtName);
        assetInduction.setDT_code(dtCode);
        assetInduction.setCapacity(capacity);
        assetInduction.setYearOfMake(yearOfMake);
        assetInduction.setTransformerMake(transformerMake);
        assetInduction.setRemark(remark);
        assetInduction.setLocation_ID(locationID);
        assetInduction.setLocation_name(locationName);

        // Save the DT_AssetInduction to the database
        DT_AssetInduction saved = DT_assetInductionRepo.save(assetInduction);
        return ResponseEntity.ok().body(saved);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(500).body("Internal Server Error");
    }
}


//@PostMapping("/schedule-outage")
//public ResponseEntity<String> createScheduleOutage(@RequestBody Schedule_Outage Schedule_Outage) {
//    try {
//        // Convert DTO to entity objects
//        Schedule_Outage scheduleOutage = new Schedule_Outage();
//        scheduleOutage.setDaysOfWeek(Schedule_Outage.getDaysOfWeek());
//        scheduleOutage.setNumberOfColumns(Schedule_Outage.getNumberOfColumns());
//
//        List<Schedule_timeslot> timeSlots = new ArrayList<>();
//        for (Schedule_timeslot timeSlot : Schedule_Outage.getTimeSlots()) {
//            Schedule_timeslot scheduleTimeslot = new Schedule_timeslot();
//            scheduleTimeslot.setTimeSlot(timeSlot);
//            timeSlots.add(scheduleTimeslot);
//        }
//        scheduleOutage.setTimeSlots(timeSlots);
//
//        // Save the Schedule_Outage entity
//        scheduleOutage.save(scheduleOutage);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("Schedule Outage created successfully");
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Schedule Outage");
//    }
//}

@PostMapping("/schedule-outages")
public ResponseEntity<Schedule_Outage> createScheduleOutage(@RequestBody Schedule_Outage Schedule_Outage) {
    try {
        // Persist the Schedule_Outage entity
    	//System.out.println(Schedule_outageRepo);
    	String subDivisionId=Schedule_Outage.getUserSTD().getSubDivisionId();
    	 System.out.println("SubDivisionId: " + subDivisionId);
        Schedule_Outage savedScheduleOutage = Schedule_outageRepo.save(Schedule_Outage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduleOutage);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
@PostMapping("/schedule")
public ResponseEntity<String> addSchedule(@RequestBody Schedule_Outage Schedule_Outage) {
	Schedule_Outage.addSchedule_Outage(Schedule_Outage);
    return ResponseEntity.ok("Schedule added successfully");
}
//@PostMapping("/schedule-outagesss")
//public ResponseEntity<?> createScheduleOutage(@RequestBody Schedule_Outage scheduleOutagess) {
//    try {
//        List<String> daysOfWeek = scheduleOutagess.getDaysOfWeek();
//        int numberOfColumns = scheduleOutagess.getNumberOfColumns();
//        List<Schedule_timeslot> timeSlots = scheduleOutagess.getTimeSlots();
//
//        // Create a new Schedule_Outage entity
//        Schedule_Outage newScheduleOutage = new Schedule_Outage(daysOfWeek, numberOfColumns, timeSlots);
//
//        // Save the Schedule_Outage to the database
//        Schedule_Outage savedScheduleOutage = Schedule_outageRepo.save(newScheduleOutage);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduleOutage);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Handle exceptions
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}
//@PostMapping("/schedule-outages")
//public ResponseEntity<?> createScheduleOutage(@RequestBody Schedule_Outage scheduleOutageDTO) {
//    try {
//        // Convert DTO to entity
//        Schedule_Outage scheduleOutage = new Schedule_Outage();
//        scheduleOutage.setDaysOfWeek(scheduleOutageDTO.getDaysOfWeek());
//        scheduleOutage.setNumberOfColumns(scheduleOutageDTO.getNumberOfColumns());
//
//        List<Schedule_timeslot> timeSlots = new ArrayList<>();
//        for (String timeSlot : scheduleOutageDTO.getTimeSlots()) {
//            Schedule_timeslot timeslot = new Schedule_timeslot();
//            timeslot.setTimeSlot(timeSlot);
//            timeslot.setScheduleOutage(scheduleOutage); // Set the relationship
//            timeSlots.add(timeslot);
//        }
//        scheduleOutage.setTimeSlots(timeSlots);
//
//        // Save the Schedule_Outage entity along with its associated Schedule_timeslot entities
////        Schedule_Outage savedScheduleOutage = schedule_OutageRepo.save(scheduleOutage);
//        Schedule_Outage savedScheduleOutage = Schedule_outageRepo.save(scheduleOutage);
//
//        
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduleOutage);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Handle exceptions
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}


}











