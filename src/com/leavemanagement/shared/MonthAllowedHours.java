package com.leavemanagement.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="monthallowedhours")
public class MonthAllowedHours   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="monthAllowedHoursId")
	private int monthAllowedHoursId;
	
	@Column(name="hours")
	private int hours;
	
	@Column(name="month")
	private int month;
	
	
	public MonthAllowedHours(){}


	public int getMonthAllowedHoursId() {
		return monthAllowedHoursId;
	}


	public void setMonthAllowedHoursId(int monthAllowedHoursId) {
		this.monthAllowedHoursId = monthAllowedHoursId;
	}


	public int getHours() {
		return hours;
	}


	public void setHours(int hours) {
		this.hours = hours;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	
	
	
}
