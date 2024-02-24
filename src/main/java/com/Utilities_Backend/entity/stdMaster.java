package com.Utilities_Backend.entity;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.UUID;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "StdMaster")
public class stdMaster {
	@Id
	@GeneratedValue(generator = "UUID") // Use a UUID generator
	private UUID ID;

	@Getter
	@Setter
	private String subDivisionName;
	@Getter
	@Setter
	private String subDivisionId;
	@Getter
	@Setter
	private String RSTName;
	@Getter
	@Setter
	private String RSTId;
	@Getter
	@Setter
	private String FeederName;
	@Getter
	@Setter
	private String FeederId;
	@Getter
	@Setter
	private String DTName;
	@Getter
	@Setter
	private String DTId;
	
}
