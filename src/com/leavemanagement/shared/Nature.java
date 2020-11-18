package com.leavemanagement.shared;

public enum Nature {
	ADHOC(1,"Adhoc"),AUDITSOLUTION(2, "Audit Solution"),BUSINESSMEETUPS(3, "Business Meetups"),EVALUATION(4,"Evaluation"),ICONFIGURAIION(5,"IT Configuration & Update")
	,LEAVE(6,"Leave"),LUNCH(7,"Lunch"),NA(8,"N/A"),OFFICESUPPLIES(9,"Office & Supplies Assignment"),ORIENTATIONAWARENESS(10,"Orientation & Awarenes"),OTHERS(11,"Others")
	,PLANNED(12,"Planned"),TRAININGDEVELOPMENT(13,"Training & Development"),UNASSIGN(14,"Unassigned");
	
	private int value; 
	private String name; 

	  private Nature(int value, String name) {
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
