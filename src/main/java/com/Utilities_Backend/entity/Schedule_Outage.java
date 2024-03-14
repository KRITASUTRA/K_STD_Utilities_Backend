package com.Utilities_Backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

//import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Schedule_Outage")
public class Schedule_Outage { 

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ElementCollection
	    private List<String> daysOfWeek;

	    private int numberOfColumns;

	    @Getter
	    @Setter
	    @ManyToOne
	    @JoinColumn(name = "subDivisionId" ,referencedColumnName ="subDivisionId")
	    private userSTD UserSTD;
	    
	   
	    @Setter
	    @OneToMany(cascade = CascadeType.ALL)
	    @JoinColumn(name = "schedule_outage_id" )
	    private List<Schedule_timeslot> timeSlots;

		

	    public Schedule_Outage() {
	    }

	    public Schedule_Outage(List<String> daysOfWeek, int numberOfColumns, List<Schedule_timeslot> timeSlots) {
	        this.daysOfWeek = daysOfWeek;
	        this.numberOfColumns = numberOfColumns;
	        this.timeSlots = timeSlots;
//	        this.status = status;
	    }

		public void addSchedule_Outage(Schedule_Outage schedule_Outage) {
			// TODO Auto-generated method stub
			
		}

		public List<String> getDaysOfWeek() {
			// TODO Auto-generated method stub
			return daysOfWeek;
		}

		public int getNumberOfColumns() {
			// TODO Auto-generated method stub
			return numberOfColumns;
		}

		public List<Schedule_timeslot> getTimeSlots() {
			// TODO Auto-generated method stub
			return timeSlots;
		}
//		private boolean status;
	    // Getters and setters
	}

