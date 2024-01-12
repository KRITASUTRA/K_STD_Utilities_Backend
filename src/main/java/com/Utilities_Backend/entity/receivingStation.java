package com.Utilities_Backend.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ReceivingStation")
public class receivingStation {

		@Id
		@GeneratedValue(generator = "UUID") // Use a UUID generator
		private UUID RSID;

		@Getter
		@Setter
		private String stationName;
		@Getter
		@Setter
		private String reason;
		@Getter
		@Setter
		private String lastUpdate;
		@Getter
		@Setter
		private String offTime;
		@Getter
		@Setter
		private String onTime;
	@Getter
	@Setter
		@Column(nullable = false, columnDefinition = "NUMBER(1,0) default 1")
		private boolean status;
	



//		@Column(name = "user_id", updatable = false, nullable = false)
//		private UUID user_id;

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "create_date", nullable = false, updatable = false)
		private Date createDate;

		@PrePersist
		protected void onCreate() {
			createDate = new Date();
		}

		public UUID getRSId() {
			return RSID;
		}

		public void setRSId(UUID RSID) {
			this.RSID = RSID;
		}
}

//package com.Utilities_Backend.entity;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "ReceivingStation")
//public class receivingStation {
//
//    @Id
//    @GeneratedValue(generator = "UUID")
//    private UUID RSID;
//
//    @Getter
//    @Setter
//    private String stationName;
//
//    @Getter
//    @Setter
//    private String reason;
//
// 
//
//    @Getter
//    @Setter
//    @Column(nullable = false, columnDefinition = "NUMBER(1,0) default 1")
//    private boolean status;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "create_date", nullable = false, updatable = false)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "last_update")
//    private Date lastUpdate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "off_time")
//    private Date offTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "on_time")
//    private Date onTime;
//
//    @PrePersist
//    @PreUpdate
//    protected void onUpdate() {
//        lastUpdate = new Date();
//
//        // Set offTime and onTime based on your logic, for example:
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        offTime = // Set your offTime logic here;
//        onTime = // Set your onTime logic here;
//
//        // Set the corresponding string values if needed
//        lastUpdateDate = sdf.format(lastUpdate);
//        offTimeDate = sdf.format(offTime);
//        onTimeDate = sdf.format(onTime);
//    }
//
//    public UUID getRSID() {
//        return RSID;
//    }
//
//    public void setRSID(UUID RSID) {
//        this.RSID = RSID;
//    }
//
//    public String getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public void setLastUpdate(String lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
//
//    public String getOffTime() {
//        return offTime;
//    }
//
//    public void setOffTime(String offTime) {
//        this.offTime = offTime;
//    }
//
//    public String getOnTime() {
//        return onTime;
//    }
//
//    public void setOnTime(String onTime) {
//        this.onTime = onTime;
//    }
//
//    // Other getters and setters...
//}


