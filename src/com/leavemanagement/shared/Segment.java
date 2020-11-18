package com.leavemanagement.shared;

public enum Segment {
	ANNUALPLAN(1,"Annual Plan"),AUDITMANAGEMENT(2,"Audit Management")
	,BREAKS(3,"Breaks"),CORPORATEGOVERNANCE(4,"Corporate Governance"),EMPLOYINDUCTION(5,"Employee Induction"),FINANCE(6,"Finance"),PERSONALMGMNT(7,"Personal Management")
	,PERSONALDVLPMNT(8,"Personal Development"),SYSTEMUPKEEP(9,"System Upkeep"),UNASSIGN(10,"Unassigned");
	
	private int value; 
	private String name; 

	  private Segment(int value, String name) {
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
