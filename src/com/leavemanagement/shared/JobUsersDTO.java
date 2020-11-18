package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class JobUsersDTO implements Serializable{
	
	Job job = new Job();
	ArrayList<User> users = new ArrayList<User>();
	
	public JobUsersDTO(){
		
		
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
