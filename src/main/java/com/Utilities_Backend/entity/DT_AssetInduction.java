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
	@Table(name = "DT_Asset_Induction")
	public class DT_AssetInduction {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_sequence")
	    @SequenceGenerator(name = "DT_sequence", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
		    private Long Asset_Code;
//		@Id
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_sequence")
//	    @SequenceGenerator(name = "DT_sequence", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
//	    private Long id;
//	    
//	    private static int assetCodeCounter = 64; // Starting code as per your requirement
//	   
//	    private Long Asset_Code;
	    // Other fields
	    
	    // Getter and Setter methods
	    	   
		 @Getter
		  @Setter
		  private String DT_Name;
		 @Getter
		    @Setter
		    private String DT_code;
		 @Getter
		 @Setter
		    private String Capicty;
		    @Getter
		    @Setter
		    private String YearOfMake;
		    @Getter
		    @Setter
		    private String TransformerMake;
		    @Getter
		    @Setter
		    private String Remark;
		    @Getter
		    @Setter
		    private String Location_ID;
		    @Getter
		    @Setter
		    private String Location_name;
		    @Getter
		    @Setter
		    private String Capacity;
		    
//		    public Long getAsset_Code() {
//		        return Asset_Code;
//		    }
//		    
//		    public void setAsset_Code(Long assetCode) {
//		        Asset_Code = assetCode;
//		    }
//		    
//		    public Long generateAssetCode() {
//		        return Long.valueOf(++assetCodeCounter);
//		    }

		    
}

	    
