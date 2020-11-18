package com.leavemanagement.shared;
import java.io.Serializable;
import java.util.Date;
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

@Table(name="jobactivity")
public class JobActivityEntity   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobactivityId")
	private int jobActivityId;
	
	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job jobId;

	
	@Column(name="planning")
	private int planning;

	@Column(name="execution")
	private int execution;
	
	@Column(name="reporting")
	private int reporting;
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User userId;
	
	
	@Column(name="followup")
	private int followup;
	
	@Column(name="totalhours")
	private int totalHours;

	public int getJobActivityId() {
		return jobActivityId;
	}

	public void setJobActivityId(int jobActivityId) {
		this.jobActivityId = jobActivityId;
	}

	public Job getJobId() {
		return jobId;
	}

	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}

	public int getPlanning() {
		return planning;
	}

	public void setPlanning(int planning) {
		this.planning = planning;
	}

	public int getExecution() {
		return execution;
	}

	public void setExecution(int execution) {
		this.execution = execution;
	}

	public int getReporting() {
		return reporting;
	}

	public void setReporting(int reporting) {
		this.reporting = reporting;
	}

	public int getFollowup() {
		return followup;
	}

	public void setFollowup(int followup) {
		this.followup = followup;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	
}
