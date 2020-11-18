package com.leavemanagement.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.leavemanagement.shared.Activity;
import com.leavemanagement.shared.AttributeRating;
import com.leavemanagement.shared.Company;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.JobUsersDTO;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeaveTypes;
import com.leavemanagement.shared.LeavesDTO;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.TimeSheetReportDTO;
import com.leavemanagement.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void fetchLeavesRemaining(User loggedInUser, AsyncCallback<ArrayList<LeavesDTO>> callback)
			throws IllegalArgumentException;

	void signIn(String userid, String password, AsyncCallback<User> callback);

	void fetchLeaveTypes(AsyncCallback<ArrayList<LeaveTypes>> callback) throws IllegalArgumentException;

	void saveLeaveRequest(LeaveRecord leaveRecord, AsyncCallback<String> callback) throws IllegalArgumentException;

	void fetchDatesDifference(Date from, Date to, AsyncCallback<String> callback) throws IllegalArgumentException;

	void fetchLeavesRecord(int userId, AsyncCallback<ArrayList<LeaveRecord>> callback) throws IllegalArgumentException;

	void approveDeclineRequest(LeaveRecord leaveRecord, AsyncCallback<String> callback) throws Exception;

	void fetchOldPassword(int userId, AsyncCallback<String> callback) throws Exception;

	void updatePassword(User user, AsyncCallback<String> callback) throws Exception;

	void addUser(User user, AsyncCallback<String> callback) throws Exception;

	void addCompany(Company company, User user, AsyncCallback<String> callback) throws Exception;

	void fetchAllUsersExceptDirector(AsyncCallback<ArrayList<User>> callback);

	void fetchAllUsers(AsyncCallback<ArrayList<User>> callback);

	void fetchPendingLeavesRecord(AsyncCallback<ArrayList<LeaveRecord>> callback) throws IllegalArgumentException;

	void fetchPendingLeavesRecordOfLoggedInUser(int userId, AsyncCallback<ArrayList<LeaveRecord>> callback)
			throws IllegalArgumentException;

	void fetchLeavesRemainingForAllUsers(AsyncCallback<ArrayList<LeavesDTOForAllUsers>> callback)
			throws IllegalArgumentException;

	void fetchAllRoles(AsyncCallback<ArrayList<Roles>> callback);

	void deleteUser(int userId, AsyncCallback<String> callback);

	void fetchJobAttributes(AsyncCallback<JobAttributesDTO> callback);

	void saveJob(Job job, AsyncCallback<String> callback);

	void fetchJobs(User loggedInUser, AsyncCallback<ArrayList<Job>> callback);

	void updatePhase(Phases phase, AsyncCallback<String> callback);

	void deletePhase(Phases phase, AsyncCallback<String> callback);

	void fetchDomains(int lineofServiceId, AsyncCallback<ArrayList<Domains>> callback);

	void fetchDomains(AsyncCallback<ArrayList<Domains>> callback);

	void fetchSublineofServices(int domainId, AsyncCallback<ArrayList<SubLineofService>> callback);

	void deleteJobEmployee(int jobEmployeeId, AsyncCallback<String> callback);

	void deleteJob(int jobId, AsyncCallback<String> callback);

	void saveJobAttribute(JobAttributes jobAttributes, AsyncCallback<String> callback);

	void deleteJobAttribute(int jobAttributeId, AsyncCallback<String> callback);

	void saveTimeSheet(ArrayList<TimeSheet> timeSheetList, AsyncCallback<String> callback);

	void fetchTimeReport(int selectedJob, int selectedMonth, int selecteduser, int selectedJobType,
			AsyncCallback<ArrayList<TimeSheetReportDTO>> asyncCallback);

	void getLineOfServices(AsyncCallback<ArrayList<LineofService>> asyncCallback);

	void saveRating(AttributeRating attributeRating, AsyncCallback<String> asyncCallback);

	void fetchMonthAllowedhours(int month, AsyncCallback<Integer> asyncCallback);

	void fetchJobsForTimeSheet(User loggedInUser, boolean chargeable, int selectedMonth2, int selectedYear2,
			AsyncCallback<ArrayList<Job>> callback);

	void fetchUsersWithJobs(AsyncCallback<ArrayList<JobUsersDTO>> callback);

	void fetchjobUserRating(int useId, int jobId, AsyncCallback<ArrayList<AttributeRating>> callback);

	void fetchAllJobs(AsyncCallback<ArrayList<Job>> callback);

	void closeJob(int jobId, AsyncCallback<String> callback);

	// void fetchJobWiseReport(HashMap<String, String> map,
	// AsyncCallback<String> callback);

	void fetchJobWiseReport(HashMap<String, Integer> map, AsyncCallback<String> asyncCallback);

	void fetchAllReport(HashMap<String, Integer> map, AsyncCallback<String> asyncCallback);

	void logOut(AsyncCallback<String> asyncCallback);

	void fetchLoggedInUser(AsyncCallback<User> asyncCallback);

	void fetchLineOfService(int domainId, AsyncCallback<ArrayList<LineofService>> callback);

	void fetchActivityReport(int lineOfServiceId, AsyncCallback<ArrayList<Activity>> callback);

	void fetchSelectedJobForTimeSheet(User loggedInUser, boolean chargeable, int jobId,
			AsyncCallback<ArrayList<Job>> callback);

	void fetchJobsWithStatus(String status, AsyncCallback<ArrayList<Job>> callback);

	void fetchAllReportPDF(HashMap<String, Integer> map, AsyncCallback<String> callback);

}
