package com.leavemanagement.shared;

import java.io.Serializable;

public class UserReportDTO implements Serializable{
	
	private String userName;
	private int budgetedHours;
	private float actualHours;
	private float timeCost;
	private int userId;
	
	public UserReportDTO(){
		
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBudgetedHours() {
		return budgetedHours;
	}

	public void setBudgetedHours(int budgetedHours) {
		this.budgetedHours = budgetedHours;
	}

	public float getActualHours() {
		return actualHours;
	}

	public void setActualHours(float actualHours) {
		this.actualHours = actualHours;
	}

	public float getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(float timeCost) {
		this.timeCost = timeCost;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
