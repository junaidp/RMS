package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class AllJobsReportDTO implements Serializable {

	private String jobName;
	private String companyName;
	private float hoursWorked;
	private float budgetedHours;
	private float hoursVariance;
	private String Allocation;
	private String location;
	private String lineOfService;
	private String year;

	private String domain;
	private String month;
	private float totalHours;
	private String activity;
	private ArrayList<TimeSheet> listTimeSheet;
	private String user;

	public AllJobsReportDTO() {

	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public float getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(float hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public float getBudgetedHours() {
		return budgetedHours;
	}

	public void setBudgetedHours(float budgetedHours) {
		this.budgetedHours = budgetedHours;
	}

	public float getHoursVariance() {
		return hoursVariance;
	}

	public void setHoursVariance(float hoursVariance) {
		this.hoursVariance = hoursVariance;
	}

	public String getAllocation() {
		return Allocation;
	}

	public void setAllocation(String allocation) {
		Allocation = allocation;
	}

	public String getLineOfService() {
		return lineOfService;
	}

	public void setLineOfService(String lineOfService) {
		this.lineOfService = lineOfService;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ArrayList<TimeSheet> getListTimeSheet() {
		return listTimeSheet;
	}

	public void setListTimeSheet(ArrayList<TimeSheet> listTimeSheet) {
		this.listTimeSheet = listTimeSheet;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
