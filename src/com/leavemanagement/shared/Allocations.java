package com.leavemanagement.shared;

public enum Allocations {
	CHARGEABLE(1,"Chargeable"),NONCHARGEABLE(2, "Non Chargeable");
	
	private int value; 
	private String name; 

	  private Allocations(int value, String name) {
	    this.value = value;
	    this.name = name;
	  }

	  public int getValue() {
	    return value;
	  }
	  
	  public String getName() {
		    return name;
		  }
	
}
