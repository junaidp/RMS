package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name="activity")
public class Activity   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activityId")
	private int activityId;
	
	@Column(name="activityName")
	private String activityName;
	
	@JoinColumn(name = "lineofserviceId")
	@ManyToOne(fetch = FetchType.LAZY)
	private LineofService lineofServiceId;
	
	
	@Transient ArrayList<TimeSheet> timeSheets;
	
	
	public Activity(){}



	public int getActivityId() {
		return activityId;
	}



	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}



	public String getActivityName() {
		return activityName;
	}



	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}



	public LineofService getLineofServiceId() {
		return lineofServiceId;
	}



	public void setLineofServiceId(LineofService lineofServiceId) {
		this.lineofServiceId = lineofServiceId;
	}



	public ArrayList<TimeSheet> getTimeSheets() {
		return timeSheets;
	}



	public void setTimeSheets(ArrayList<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}


}
