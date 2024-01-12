package com.Utilities_Backend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PumpEnergy")
public class pumpEnergy {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String RST;
    @Getter
    @Setter
    private String subDivision;
    @Getter
    @Setter
    private String Feeder;
    @Getter
    @Setter
    private String KW;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    public void setCreateDate(Date date) {
        this.date = date;
    }

    @PrePersist
    protected void onCreate() {
        if (date == null) {
            date = new Date();
        }
    }
}
