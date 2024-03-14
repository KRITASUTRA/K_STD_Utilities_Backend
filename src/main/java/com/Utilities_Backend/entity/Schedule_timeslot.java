package com.Utilities_Backend.entity;


//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.UUID;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Schedule_timeslot")
public class Schedule_timeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String timeSlots;
    @Getter
    @Setter
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "schedule_outage_id")
    private Schedule_Outage scheduleOutage;
    
    @ManyToOne
    @JoinColumn(name = "subDivisionId" ,referencedColumnName ="subDivisionId" )
    private userSTD UserSTD;



    // Constructors, getters, and setters
}

