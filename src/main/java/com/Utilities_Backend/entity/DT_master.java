package com.Utilities_Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DT_MASTER")
public class DT_master {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_sequence")
    @SequenceGenerator(name = "DT_sequence", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
	    private Long DT_ID;
	 @Getter
	    @Setter
	    private String DT_Name;
	    @Getter
	    @Setter
	    private String DT_code;
	    @Getter
	    @Setter
	    private String Office_code;
	    @Getter
	    @Setter
	    private String Asset_code;
	    @Getter
	    @Setter
	    private String Location;
	    @Getter
	    @Setter
	    private String Capacity;
	    @Getter
	    @Setter
	    private String Make_ID;
	    @Getter
	    @Setter
	    private String Manufacture_year;
	    @Getter
	    @Setter
	    private String Punch_ID;
	    @Getter
	    @Setter
	    private String Installed_ON;
	    @Getter
	    @Setter
	    private String Installed_by;
	

}
