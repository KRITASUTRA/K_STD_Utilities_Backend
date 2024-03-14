package com.Utilities_Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

//import javax.tools.DocumentationTool.Location;

@Entity
@Table(name = "DT_AssetHistory")
public class DT_assetdamageHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "DT_ID")
    private DT_master dtMaster; 
    
    @ManyToOne
    @JoinColumn(name = "location") // Corrected column name
    private DT_master location; // Assuming Location is another entity in your system
    
    @Getter @Setter 
    private String Asset_code;
    
    @Getter @Setter
    private Date Damage_on;
    
    @Getter @Setter 
    private String SD_code;
 
    @Getter @Setter
    private String WK_code;
    
    @Getter @Setter 
    private boolean Is_repaired;
    
    @Getter @Setter 
    private Date Repaired_on;
    
    @Getter @Setter 
    private String Repairied_by;
    
    @Getter @Setter 
    private String Replacement_code;
    
    @Getter @Setter 
    private String Status;
    
    @Getter @Setter 
    private Date Updated_on;
    
    @Getter @Setter 
    private String Updated_by;
}
