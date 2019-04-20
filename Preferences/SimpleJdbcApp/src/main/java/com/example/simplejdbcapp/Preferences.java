package com.example.simplejdbcapp;

public class Preferences {
	 	String h08_10;
	    String h10_12;
	    String h12_14;
	    String h14_16;
	    String h16_18;
	    String h18_20;
	    
	    public Preferences(String h1, String h2, String h3, String h4, String h5, String h6) {
	        this.h08_10 = h1;
	        this.h10_12 = h2;
	        this.h12_14 = h3;
	        this.h14_16 = h4;
	        this.h16_18 = h5;
	        this.h18_20 = h6;
	    }
	    public String get08_10() {
	    	return h08_10;
	    }
	    
	    public String get10_12() {
	    	return h10_12;
	    }
	    
	    public String get12_14() {
	    	return h12_14;
	    }
	    
	    public String get14_16() {
	    	return h14_16;
	    }
	    
	    public String get16_18() {
	    	return h16_18;
	    }
	    
	    public String get18_20() {
	    	return h18_20;
	    }
	    
	    @Override
	    public String toString() {
	        return "{" +	                
	                "08_10: " + h08_10 +
	                ", 10_12: " + h10_12 +
	                ", 12_14: " + h12_14 +
	                ", 14_16: " + h14_16 +
	                ", 16_18: " + h16_18 +
	                ", 18_20: " + h18_20 +
	                "}";
	    }
}
