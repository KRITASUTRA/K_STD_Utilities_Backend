package com.Utilities_Backend.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Feeder")
public class feeder {

  @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE) // Use a UUID generator
  private String FID;

    @Getter
    @Setter
    private String feederName;
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

@Getter
@Setter
@ManyToOne
@JoinColumn(name="RSID" , referencedColumnName = "RSID")
private receivingStation receivingStation;



//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "create_date", nullable = false, updatable = false)
//    private Date createDate;

//    @PrePersist
//    protected void onCreate() {
//        createDate = new Date();
//    }



}


