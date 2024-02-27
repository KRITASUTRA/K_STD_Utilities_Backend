//
//
//package com.Utilities_Backend.entity;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
////import java.util.Date;
////import java.util.UUID;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "pumpenergy")
//public class pumpEnergy {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feeder_sequence")
//    @SequenceGenerator(name = "feeder_sequence", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
//	    private Long Id;
//    @Getter
//    @Setter
//    private String RST;
//    @Getter
//    @Setter
//    private String subDivision;
//    @Getter
//    @Setter
//    private String feeder;
//    @Getter
//    @Setter
//    private String TotalSupply;
//    @Getter
//    @Setter
//    private String KW;
//    
////    @Setter
////    @Getter
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date energyDate;
//
//    public void setEnergyDate(String energyDate) {
//        // Assuming energyDateString is in a format that can be parsed
//        // Adjust the parsing logic based on the actual format
//        try {
//            this.energyDate = new SimpleDateFormat("yyyy-MM-dd").parse(energyDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            // Handle the parsing exception as needed
//        }
//    }
//    
//    public String getEnergyDate() {
//        // Assuming energy_date should be returned in a specific format (e.g., ISO 8601)
//        return new SimpleDateFormat("yyyy-MM-dd").format(this.energyDate);
//    }
//
//}

package com.Utilities_Backend.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pumpenergy")
public class pumpEnergy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feeder_sequence")
    @SequenceGenerator(name = "feeder_sequence", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
    private Long id;

    @Getter @Setter
    private String RST;

    @Getter @Setter
    private String subDivision;

    @Getter @Setter
    private String feeder;

    @Getter @Setter
    private String totalSupply;

    @Getter @Setter
    private String KW;

    @Temporal(TemporalType.DATE) // Store only the date portion
    private Date energyDate;

    // Setter for energyDate
    public void setEnergyDate(String energyDate) {
        // Assuming energyDateString is in a format that can be parsed
        // Adjust the parsing logic based on the actual format
        try {
            // Parsing the date string to Date object
            this.energyDate = new SimpleDateFormat("yyyy-MM-dd").parse(energyDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception as needed
        }
    }

    // Getter for energyDate
    public String getEnergyDate() {
        if (this.energyDate != null) {
            // Formatting Date object to desired string format
            return new SimpleDateFormat("yyyy-MM-dd").format(this.energyDate);
        } else {
            return null;
        }
    }
}

