package com.leavemanagement.shared;

public enum Location {
	LOCAL(1,"Local"),OVERSEAS(2, "Overseas");
	
	private int value; 
	private String name; 

	  private Location(int value, String name) {
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
