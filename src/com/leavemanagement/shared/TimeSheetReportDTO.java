package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeSheetReportDTO implements Serializable {
	
	private String jobName;
	private ArrayList<UserReportDTO> usersList = new  ArrayList<UserReportDTO>();
	private String jobType;
	private float total;
	private int fee;
	private float actualRecoveryRate;
	private String budgetedRecoveryRate;
	private int jobId;
	private float timeCost;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	
	public String getBudgetedRecoveryRate() {
		return budgetedRecoveryRate;
	}
	public void setBudgetedRecoveryRate(String budgetedRecoveryRate) {
		this.budgetedRecoveryRate = budgetedRecoveryRate;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public ArrayList<UserReportDTO> getUsersList() {
		return usersList;
	}
	public void setUsersList(ArrayList<UserReportDTO> usersList) {
		this.usersList = usersList;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public float getActualRecoveryRate() {
		return actualRecoveryRate;
	}
	public void setActualRecoveryRate(float actualRecoveryRate) {
		this.actualRecoveryRate = actualRecoveryRate;
	}
	public float getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(float totalTimeCost) {
		this.timeCost = totalTimeCost;
	}

}
