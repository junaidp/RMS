package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Branches;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.User;

import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.ui.MaterialColumn;

public class JobsListView extends MaterialColumn {
	Column<Job, String> jobName;
	Column<Job, String> jobDomain;
	Column<Job, String> jobCompany;
	Column<Job, String> jobPhase;
	Column<Job, String> jobLineOfService;
	Column<Job, String> jobSubLineofService;
	Column<Job, String> jobStartDate;
	Column<Job, String> jobDeliveryDate;
	Column<Job, String> deleteJob;
	Column<Job, String> reOpen;
	Column<Job, String> closeJob;
	String s;
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private CellTable<Job> table = new CellTable<Job>();
	private User loggedInUser = null;

	Image imgRefresh = new Image("refresh.png");

	public JobsListView(User loggedInUser) {
		this.loggedInUser = loggedInUser;
		table.setWidth("800px");
		setTable();
		fetchJobs();
		add(imgRefresh);
		// ScrollPanel scrollpanel = new ScrollPanel();
		// scrollpanel.add(table);
		// scrollpanel.setWidth("500px");
		// scrollpanel.setHeight("300px");
		// add(scrollpanel);
		add(table);

		table.setRowStyles(new RowStyles<Job>() {

			@Override
			public String getStyleNames(Job row, int rowIndex) {
				if (row.getStatus().equalsIgnoreCase("Closed")) {
					return "redBackground";
				}
				return s;

			}
		});

		table.getColumn(0).setCellStyleNames("editCell");
		table.addColumnStyleName(0, "editCell");

		imgRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fetchJobs();
			}
		});
	}

	public void fetchJobs() {
		GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
		rpcService.fetchJobs(loggedInUser, new AsyncCallback<ArrayList<Job>>() {

			@Override
			public void onSuccess(ArrayList<Job> jobs) {
				populateJobsList(jobs);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("fetch jobs list failed in joblist view !" + caught);
			}
		});
	}

	private void setTable() {
		table.setEmptyTableWidget(new HTML("No Record found"));
		table.setRowCount(0);
		table.setPageSize(200);

		jobName = new Column<Job, String>(new ClickableTextCell()) {
			@Override
			public String getValue(Job object) {
				return object.getJobName();
			}
		};
		jobName.setCellStyleNames("hover");
		table.addColumn(jobName, "Name");

		jobCompany = new Column<Job, String>(new TextCell()) {
			@Override

			public String getValue(Job object) {
				for (Branches status : Branches.values()) {
					if (status.getValue() == object.getCompany()) {

						s = status.getName();

					}

				}

				return s;
			}

		};
		table.addColumn(jobCompany, "Company");
		jobDomain = new Column<Job, String>(new TextCell()) {
			@Override
			public String getValue(Job object) {
				return object.getDomainId().getName();

			}
		};
		table.addColumn(jobDomain, "Domain");

		// jobPhase = new Column<Job, String>(new TextCell()) {
		// @Override
		// public String getValue(Job object) {
		// String phases="";
		// for(int i=0; i< object.getJobPhases().size(); i++){
		// if(object.getJobPhases().size()>1){
		// if(object.getJobPhases().get(i).getPhaseName().length()>10){
		// phases = phases+"
		// "+object.getJobPhases().get(i).getPhaseName().substring(0, 10)+" ,";
		// }else{
		// phases = phases+" "+object.getJobPhases().get(i).getPhaseName()+" ,";
		//
		// }
		// }else{
		// return object.getJobPhases().get(i).getPhaseName();
		// }
		// }
		// return phases;
		// }
		// };
		// table.addColumn(jobPhase,"Phases");

		jobLineOfService = new Column<Job, String>(new TextCell()) {
			@Override
			public String getValue(Job object) {
				return object.getLineofServiceId().getName();
			}
		};

		// table.addColumn(jobLineOfService,"Line of Service");

		// jobSubLineofService = new Column<Job, String>(new TextCell()) {
		// @Override
		// public String getValue(Job object) {
		// return object.getSubLineofServiceId().getName();
		// }
		// };
		// table.addColumn(jobSubLineofService,"Subline of Service");
		ButtonCell btnDel = new ButtonCell();

		deleteJob = new Column<Job, String>(btnDel) {
			@Override
			public String getValue(Job object) {
				return "Delete";
			}
		};
		ButtonCell btnReOpen = new ButtonCell();
		reOpen = new Column<Job, String>(btnReOpen) {
			@Override
			public String getValue(Job object) {
				if (object.getStatus().equalsIgnoreCase("Closed")) {
					// reOpen.setCellStyleNames("btnStyleReOpen");
					return "Re Open";

				} else

					return "";

			}
		};

		deleteJob.setCellStyleNames("btnStyleDelete");

		closeJob = new Column<Job, String>(new ButtonCell()) {
			@Override
			public String getValue(Job object) {
				if (object.getStatus().equalsIgnoreCase("Closed")) {
					return "Closed";
				} else
					return "Close";
			}
		};

		closeJob.setCellStyleNames("btnStyle");

		// jobStartDate = new Column<Job, String>(new TextCell()) {
		// @Override
		// public String getValue(Job job) {
		// DateTimeFormat fmt = DateTimeFormat.getFormat(" MMMM dd, yyyy");
		// // prints Monday, December 17, 2007 in the default locale
		// if(job.getJobPhases().size() > 0){
		// return fmt.format(job.getJobPhases().get(0).getStartDate());
		// }else{
		// return "No phase Added";
		// }
		//// return
		// job.getJobPhases().get(0).getStartDate().toString().substring(0,11);
		//
		// }
		// };

		// jobDeliveryDate = new Column<Job, String>(new TextCell()) {
		// @Override
		// public String getValue(Job job) {
		// int last = job.getJobPhases().size();
		// DateTimeFormat fmt = DateTimeFormat.getFormat(" MMMM dd, yyyy");
		// if(job.getJobPhases().size() > 0){
		// return fmt.format(job.getJobPhases().get(last-1).getDeliveryDate());
		// }else{
		// return "No phase Added";
		// }
		//
		// }
		// };

		// table.addColumn(jobStartDate,"Start Date");
		// table.addColumn(jobDeliveryDate,"Submission Date");

		if (loggedInUser.getRoleId().getRoleId() == 5) {

			table.addColumn(closeJob, "");
			table.addColumn(deleteJob, "");
			table.addColumn(reOpen, "");
		}

		jobName.setFieldUpdater(new FieldUpdater<Job, String>() {

			@Override
			public void update(int index, Job object, String value) {
				if (object.getStatus() != "Closed") {
					final MaterialWindow creationWindow = new MaterialWindow();

					JobEditView jobEditView = new JobEditView(object, loggedInUser, new Runnable() {

						@Override
						public void run() {
							creationWindow.close();
						}
					});
					// final PopupsView popup = new PopupsView(jobEditView);
					creationWindow.add(jobEditView);
					creationWindow.setMaximize(true);
					creationWindow.setTitle("Update Job");
					creationWindow.open();
					jobEditView.getBtnClose().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							// popup.getPopup().removeFromParent();
							creationWindow.close();
						}
					});
				}
			}

		});
		reOpen.setFieldUpdater(new FieldUpdater<Job, String>() {

			@Override
			public void update(int index, Job object, String value) {
				object.setStatus("Avtive");
				rpcService.saveJob(object, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
						fetchJobs();
					}
				});
			}
		});
		deleteJob.setFieldUpdater(new FieldUpdater<Job, String>() {

			@Override
			public void update(int index, Job job, String value) {
				boolean confirmed = Window.confirm("Selected Job will be Deleted !");
				if (confirmed) {
					deleteJob(job.getJobId());
				}
			}

		});

		closeJob.setFieldUpdater(new FieldUpdater<Job, String>() {

			@Override
			public void update(int index, Job job, String value) {
				boolean confirmed = Window.confirm("Selected Job will be Closed !");
				if (confirmed) {

					closeJob(job.getJobId());

				}
			}

		});
	}

	private void deleteJob(int jobId) {
		GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
		rpcService.deleteJob(jobId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("job delete Failed");
			}

			@Override
			public void onSuccess(String result) {
				fetchJobs();

			}
		});
	}

	private void closeJob(int jobId) {
		GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
		rpcService.closeJob(jobId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("job close Failed");
			}

			@Override
			public void onSuccess(String result) {
				fetchJobs();

			}
		});
	}

	public void populateJobsList(ArrayList<Job> jobs) {

		table.setRowData(0, jobs);
		table.setRowCount(jobs.size());

	}

}
