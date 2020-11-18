package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;

public class JobEditView extends MaterialColumn {
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private MaterialButton btnClose = new MaterialButton("Close");
	JobCreationView jobCreationView;
	private MaterialColumn vpnlMain = new MaterialColumn();
	User loggedInUser;
	private ArrayList<User> employeesList;

	public JobEditView(Job selectedJob, User loggedInUser, Runnable runnable) {
		this.loggedInUser = loggedInUser;
		// ScrollPanel scroll = new ScrollPanel();
		// scroll.setHeight("800px");
		add(vpnlMain);
		// scroll.setWidget(vpnlMain);

		jobCreationView = new JobCreationView(selectedJob, loggedInUser, runnable);
		jobCreationView.getPanel().remove(1);

		jobCreationView.populate(selectedJob);

		// jobCreationView.getTxtClient().setText(selectedJob.getClient());
		// jobCreationView.getTxtClientFee().setText(selectedJob.getClientFee()+"");
		// jobCreationView.getTextPrinicialConsultantHours().setText(selectedJob.getPrincipalConsultantHours()+"");
		// jobCreationView.getTextSupervisorHours().setText(selectedJob.getSupervisorHours()+"");
		jobCreationView.getJobsListView().setVisible(false);
		vpnlMain.add(jobCreationView);
		fetchEmployees(selectedJob);

		//
		// for(int i=0; i< selectedJob.getJobPhases().size(); i++){
		// final Phases phase = selectedJob.getJobPhases().get(i);
		// final JobPhaseView jobPhaseView = new JobPhaseView();
		// vpnlMain.add(jobPhaseView);
		// jobPhaseView.getBtnDelete().setVisible(true);
		// jobPhaseView.getBtnCancel().setVisible(false);
		//
		// jobPhaseView.getTxtPhase().setText(phase.getPhaseName());
		// jobPhaseView.getStartDate().setValue(phase.getStartDate());
		// jobPhaseView.getSubmissionDate().setValue(phase.getSubmissionDate());
		// jobPhaseView.getDeliveryDate().setValue(phase.getDeliveryDate());
		//
		// jobPhaseView.getBtnSubmit().addClickHandler(new ClickHandler(){
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// phase.setDeliveryDate(jobPhaseView.getDeliveryDate().getValue());
		// phase.setPhaseName(jobPhaseView.getTxtPhase().getText());
		// phase.setStartDate(jobPhaseView.getStartDate().getValue());
		// phase.setSubmissionDate(jobPhaseView.getSubmissionDate().getValue());
		// updatePhase(phase);
		// }
		//
		// });
		//
		// jobPhaseView.getBtnDelete().addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// deletePhase(phase, jobPhaseView);
		// }
		//
		// });
		//
		// }
		// vpnlMain.add(btnClose);
	}

	private void fetchEmployees(final Job selectedJob) {
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				employeesList = result;
				fetchJobAttributes(selectedJob);
			}
		});
	}

	private void selectFields(final Job selectedJob) {

		rpcService.fetchLineOfService(selectedJob.getDomainId().getDomainId(),
				new AsyncCallback<ArrayList<LineofService>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ArrayList<LineofService> result) {
						jobCreationView.getListLineOfService().clear();
						for (int i = 0; i < result.size(); i++) {
							jobCreationView.getListLineOfService().addItem(result.get(i).getLineofServiceId() + "",
									result.get(i).getName());
						}

						for (int i = 0; i < jobCreationView.getListLineOfService().getItemCount(); i++) {
							if (jobCreationView.getListLineOfService().getValue(i)
									.equals(selectedJob.getLineofServiceId().getLineofServiceId() + "")) {
								jobCreationView.getListLineOfService().setSelectedIndex(i);
								break;
							}
						}
					}
				});

		for (int i = 0; i < jobCreationView.getListCountry().getItemCount(); i++) {
			if (jobCreationView.getListCountry().getValue(i).equals(selectedJob.getCountryId().getCountryId() + "")) {
				jobCreationView.getListCountry().setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < jobCreationView.getListDomain().getItemCount(); i++) {
			if (jobCreationView.getListDomain().getValue(i).equals(selectedJob.getDomainId().getDomainId() + "")) {
				jobCreationView.getListDomain().setSelectedIndex(i);

				break;
			}
		}

		// Setting selected users to combo
		ArrayList<User> usersList = new ArrayList<>();
		for (int j = 0; j < jobCreationView.listEmployee1.getValues().size(); j++) {
			User value = jobCreationView.listEmployee1.getValues().get(j);
			int id = value.getUserId();

			for (int i = 0; i < selectedJob.getJobEmployeesList().size(); i++) {
				User user = selectedJob.getJobEmployeesList().get(i).getEmployeeId();
				if (id == user.getUserId()) {
					usersList.add(value);
				}
			}
		}

		jobCreationView.listEmployee1.setValues(usersList);

		for (int i = 0; i < jobCreationView.getContainerActivities().getWidgetCount(); i++) {

			JobActivities jobActivites = (JobActivities) jobCreationView.getContainerActivities().getWidget(i);
			jobActivites.poupulateSavedActivites(selectedJob.getJobActivities());
		}
		jobCreationView.calculateTotalHours();

		// for(int i=0;
		// i<jobCreationView.getListSubLineofService().getItemCount(); i++ ){
		// if(jobCreationView.getListSubLineofService().getValue(i).equals(selectedJob.getSubLineofServiceId().getSubLineofServiceId()+"")){
		// jobCreationView.getListSubLineofService().setSelectedIndex(i);
		// break;
		// }
		// }

		setAssignedToList(selectedJob);

		// for(int i=0; i<jobCreationView.getListSupervisor().getItemCount();
		// i++ ){
		// if(jobCreationView.getListSupervisor().getValue(i).equals(selectedJob.getSupervisorId().getUserId()+"")){
		// jobCreationView.getListSupervisor().setSelectedIndex(i);
		// break;
		// }
		// }
		//
		// for(int i=0;
		// i<jobCreationView.getListPrincipalConsultant().getItemCount(); i++ ){
		// if(jobCreationView.getListPrincipalConsultant().getValue(i).equals(selectedJob.getPrincipalConsultantId().getUserId()+"")){
		// jobCreationView.getListPrincipalConsultant().setSelectedIndex(i);
		// break;
		// }
		// }
	}

	private void setAssignedToList(Job selectedJob) {
		jobCreationView.getVpnlEmployees().clear();
		for (int i = 0; i < selectedJob.getJobEmployeesList().size(); i++) {

			final AssignedToWidget assignToWidget = new AssignedToWidget(employeesList);
			for (int j = 0; j < employeesList.size(); j++) {
				assignToWidget.getListAssign().addItem(employeesList.get(j).getUserId() + "",
						employeesList.get(j).getName());
			}

			for (int k = 0; k < assignToWidget.getListAssign().getItemCount(); k++) {
				if (assignToWidget.getListAssign().getValue(k)
						.equals(selectedJob.getJobEmployeesList().get(i).getEmployeeId().getUserId() + "")) {
					assignToWidget.getListAssign().setSelectedIndex(k);
					break;
				}
			}

			assignToWidget.getTxtDays().setText(selectedJob.getJobEmployeesList().get(i).getNoOfDays() + "");
			assignToWidget.setJobEmployeeId(selectedJob.getJobEmployeesList().get(i).getJobEmployeeId());
			jobCreationView.getVpnlEmployees().add(assignToWidget);

			assignToWidget.getBtnRemove().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					boolean confirmed = Window.confirm("Selected Employee will be removed from this Job !");
					if (confirmed) {
						jobCreationView.getVpnlEmployees().remove(assignToWidget);
						removeJobEmployee(assignToWidget.getJobEmployeeId());
					}
				}

			});
		}
	}

	private void removeJobEmployee(int jobEmployeeId) {
		rpcService.deleteJobEmployee(jobEmployeeId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fil deleting jobEmployee");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("employee removed from the job");
			}
		});
	}

	private void deletePhase(final Phases phase, final JobPhaseView jobPhaseView) {
		rpcService.deletePhase(phase, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("delete phase Failed");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("phase deleted");
				remove(jobPhaseView);
			}

		});
	}

	private void updatePhase(final Phases phase) {
		rpcService.updatePhase(phase, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Window.alert("phase updated");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("phase updation failed");
			}
		});
	}

	private void fetchJobAttributes(final Job selectedJob) {
		rpcService.fetchJobAttributes(new AsyncCallback<JobAttributesDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchJobAttributes fail:" + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(JobAttributesDTO result) {
				jobCreationView.setJobAttributes(result);
				selectFields(selectedJob);

			}
		});

	}

	public MaterialButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(MaterialButton btnClose) {
		this.btnClose = btnClose;
	}

}
