package com.leavemanagement.shared;

public enum Branches {
	ALSUHAIMI(1,"AlSuhaimi Holding Company"),FUGRO(2, "Fugro Suhaimi Limited"),GLOBAL(3, "Global Suhaimi Company")
	,GARNET(4, "Garnet Arabia Company Limited"),PACKAGING(5, "Saudia Arabia Packaging Industry Company"),SAPIN(6, "Sapin United Arab Emirates LLC"), UNITED(7, "United Thara Company");
	
	private int value; 
	private String name; 

	  private Branches(int value, String name) {
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
