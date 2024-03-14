package com.Utilities_Backend.entity;

import java.util.UUID;

import jakarta.persistence.Column;
//import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STDUser")
public class userSTD {
    @Id
    @GeneratedValue(generator = "UUID") // Use a UUID generator
    private UUID ID;

    @Getter @Setter
    @Column(name = "\"user\"")
    private String user;
    @Getter @Setter
    private String subDivisionName;
    @Getter @Setter
    private String RSTName;
    @Getter @Setter
    private String FeederName;
    @Getter @Setter
    private String split;
    @Getter
	@Setter
	private String subDivisionId;
    @Getter
	@Setter
	private String RSTId;
	@Getter
	@Setter
	private String FeederId;
	    @Getter @Setter
	    private String gridname;
	    @Getter @Setter
	    private String rstcapacity;
	    @Getter @Setter
	    private String Feed_Area;
	    @Getter
		@Setter
		private String Feeder_Peak_load;
	    @Getter
		@Setter
		private String Sub_Transmission_Division;
		@Getter
		@Setter
		private String Feeder_Number;
	
}

