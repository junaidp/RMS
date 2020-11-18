package com.leavemanagement.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="attributerating")
public class AttributeRating   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attributeIdRatingId")
	private int attributeIdRatingId;
	
	@Column(name="attributeId")
	private int attributeId;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="jobId")
	private int jobId;
	
	@Column(name="level")
	private int level;
	
	@Column(name="score")
	private int score;

	
	
	public AttributeRating(){}



	public int getAttributeIdRatingId() {
		return attributeIdRatingId;
	}



	public void setAttributeIdRatingId(int attributeIdRatingId) {
		this.attributeIdRatingId = attributeIdRatingId;
	}



	public int getAttributeId() {
		return attributeId;
	}



	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getRating() {
		return rating;
	}



	public void setRating(String rating) {
		this.rating = rating;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public int getJobId() {
		return jobId;
	}



	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}




}

