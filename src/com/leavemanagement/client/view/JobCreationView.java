package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Allocations;
import com.leavemanagement.shared.Branches;
import com.leavemanagement.shared.Countries;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobActivityEntity;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.JobEmployees;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Location;
import com.leavemanagement.shared.Nature;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.Segment;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.User;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class JobCreationView extends MaterialColumn {

	private MaterialListBox listLineOfService = new MaterialListBox();
	private MaterialListBox listDomain = new MaterialListBox();
	// private MaterialListBox listSubLineofService = new MaterialListBox();
	private MaterialListBox listLocation = new MaterialListBox();
	private MaterialTextBox txtJobName = new MaterialTextBox();
	private MaterialListBox listBoxCompanyName = new MaterialListBox();
	private MaterialListBox listCountry = new MaterialListBox();
	// private MaterialListBox listSupervisor = new MaterialListBox();
	// private MaterialListBox listPrincipalConsultant = new MaterialListBox();
	// private MaterialTextBox txtClient = new MaterialTextBox();
	// private MaterialTextBox txtClientFee = new MaterialTextBox();
	private MaterialButton btnSubmit = new MaterialButton("Submit/Update");
	// private MaterialButton btnPhase = new MaterialButton("Add Phase");
	// private MaterialTextBox textSupervisorHours = new MaterialTextBox();
	private MaterialListBox listBoxAllocation = new MaterialListBox();
	private MaterialListBox listBoxNature = new MaterialListBox();
	private MaterialListBox listBoxSegment = new MaterialListBox();
	private MaterialListBox listBoxEmployee = new MaterialListBox();
	// private MaterialTextBox textPrinicialConsultantHours = new
	// MaterialTextBox();
	private ArrayList<Phases> phases = new ArrayList<Phases>();
	JobsListView jobsListView;
	// private MaterialListBox listEmployee1 = new MaterialListBox();
	private Job selectedJob;
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private Label lblCountryName = new Label("Country Name");
	private MaterialColumn vpnlEmployees = new MaterialColumn();
	private ArrayList<User> employeesList;
	private CostWidget costWidget = new CostWidget();
	// private JobActivities jobActivityView = new JobActivities();
	private StackPanel panel = new StackPanel();
	private MaterialColumn containerActivities = new MaterialColumn();
	private MaterialTextBox txtBoxTotalHours = new MaterialTextBox();
	private Anchor imgRefresh = new Anchor("calculate total");
	MaterialComboBox<User> listEmployee1 = new MaterialComboBox<User>();

	public JobCreationView(Job job, User loggedInUser, final Runnable runnable) {
		jobsListView = new JobsListView(loggedInUser);
		this.selectedJob = job;
		listEmployee1.setPlaceholder("Select Employee");
		listEmployee1.setMultiple(true);
		txtBoxTotalHours.setEnabled(false);
		for (Allocations allocations : Allocations.values()) {
			listBoxAllocation.addItem(allocations.getValue() + "", allocations.getName());

		}
		for (Location location : Location.values()) {
			listLocation.addItem(location.getValue() + "", location.getName());
		}
		for (Nature natures : Nature.values()) {
			listBoxNature.addItem(natures.getValue() + "", natures.getName());
		}

		for (Segment segments : Segment.values()) {
			listBoxSegment.addItem(segments.getValue() + "", segments.getName());
		}

		for (Branches branches : Branches.values()) {
			listBoxCompanyName.addItem(branches.getValue() + "", branches.getName());
		}
		// listBoxNature.addItem("Adhoc");
		// listBoxNature.addItem("Audit Solution");
		// listBoxNature.addItem("Business Meetups");
		// listBoxNature.addItem("Evaluation");
		// listBoxNature.addItem("IT Configuration & Upgrade");
		// listBoxNature.addItem("Leaves");
		// listBoxNature.addItem("Lunch");
		// listBoxNature.addItem("N/A");
		// listBoxNature.addItem("Office & Supplies Assignment");
		// listBoxNature.addItem("Orientation & Awareness");
		// listBoxNature.addItem("Others");
		// listBoxNature.addItem("Planned");
		// listBoxNature.addItem("Training & Development");
		// listBoxNature.addItem("Unassigned");
		//
		//
		// listBoxSegment.addItem("Annual Plan");
		// listBoxSegment.addItem("Audit Management");
		// listBoxSegment.addItem("Breaks");
		// listBoxSegment.addItem("Corporate Governance");
		// listBoxSegment.addItem("Employee Induction");
		// listBoxSegment.addItem("Finance");
		// listBoxSegment.addItem("Personnel Development");
		// listBoxSegment.addItem("Personnel Management");
		// listBoxSegment.addItem("System Upkeep");
		// listBoxSegment.addItem("Unassigned");

		FlexTable flex = new FlexTable();
		Label lblCompanyName = new Label("Company Name");
		Label lblSegment = new Label("Segment");
		Label lblNature = new Label("Nature");
		Label llblEmployee = new Label("Employee");
		Label lblLineOfService = new Label("Line of Service");
		Label lblDomain = new Label("Domain");
		Label lblClientFee = new Label("Client Fee");
		Label lblSublineOfService = new Label("Subline of Service");
		Label lblJobName = new Label("Job Name");
		Label lblLocation = new Label("Location");
		Label lblSupervisors = new Label("Supervisor");
		Label lblPrinicipalConsultant = new Label("Principal consultant");
		Label lblAllocation = new Label("Allocation");
		Label lblClientName = new Label("Client Name");
		Label lblEmployee = new Label("Assign to");
		Label lblEmployeeNew = new Label("Employee");
		Image btnAddEmployee = new Image("add.png");
		btnAddEmployee.addStyleName("hover");

		btnAddEmployee.setTitle("Add Employee");
		// flex.setWidget(1, 0, lblLineOfService);
		// flex.setWidget(1, 1, listLineOfService);
		// flex.setWidget(2, 0, lblDomain);
		// flex.setWidget(2, 1, listDomain);
		// flex.setWidget(3, 0, lblSublineOfService);
		// flex.setWidget(3, 1, listSubLineofService);
		// flex.setWidget(4, 0, lblJobName);
		// flex.setWidget(4, 1, txtJobName);
		// flex.setWidget(5, 0, lblLocation);
		// flex.setWidget(5, 1, listLocation);
		// flex.setWidget(6, 0, lblCountryName);
		// flex.setWidget(6, 1, listCountry);
		// flex.setWidget(7, 0, lblClientName);
		// flex.setWidget(7, 1, txtClient);
		// flex.setWidget(7, 2, btnPhase);
		// flex.setWidget(8, 0, lblClientFee);
		// flex.setWidget(8, 1, txtClientFee);
		// // flex.setWidget(9, 0, lblEmployee);
		// // flex.setWidget(9, 1, listEmployee1);
		// flex.setWidget(8, 2, btnAddEmployee);
		// flex.setWidget(10, 1, vpnlEmployees);

		// adding new allignments
		flex.setWidget(1, 0, lblCompanyName);
		flex.setWidget(1, 1, listBoxCompanyName);
		flex.setWidget(2, 0, lblLocation);
		flex.setWidget(2, 1, listLocation);
		flex.setWidget(3, 0, lblJobName);
		flex.setWidget(3, 1, txtJobName);
		flex.setWidget(4, 0, lblAllocation);
		flex.setWidget(4, 1, listBoxAllocation);
		flex.setWidget(5, 0, lblDomain);
		flex.setWidget(5, 1, listDomain);
		flex.setWidget(6, 0, lblLineOfService);
		flex.setWidget(6, 1, listLineOfService);

		// flex.setWidget(7, 0, lblSegment);
		// flex.setWidget(7, 1, listBoxSegment);
		// flex.setWidget(7, 2, btnPhase);
		// flex.setWidget(8, 0, lblNature);
		// flex.setWidget(8, 1, listBoxNature);
		// flex.setWidget(8, 2, btnAddEmployee);
		// flex.setWidget(10, 1, vpnlEmployees);
		flex.setWidget(7, 0, lblEmployeeNew);
		flex.setWidget(7, 1, listEmployee1);
		// listEmployee1.setMultipleSelect(true);
		btnAddEmployee.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final AssignedToWidget assignedToWidget = new AssignedToWidget(employeesList);
				vpnlEmployees.add(assignedToWidget);
				assignedToWidget.getBtnRemove().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						vpnlEmployees.remove(assignedToWidget);

					}
				});

			}
		});
		/// end

		// flex.setWidget(11, 0, lblSupervisors);
		// flex.setWidget(11, 1, listSupervisor);
		// flex.setWidget(11, 2, new Label("Hours: "));
		// flex.setWidget(11, 3, textSupervisorHours);
		// flex.setWidget(12, 0, lblPrinicipalConsultant);
		// flex.setWidget(12, 1, listPrincipalConsultant);
		// flex.setWidget(12, 2, new Label("Hours: "));
		// flex.setWidget(12, 3, textPrinicialConsultantHours);

		// textSupervisorHours.setText("0");
		// textPrinicialConsultantHours.setText("0");
		// txtClientFee.setText("0");
		MaterialRow hTotal = new MaterialRow();
		MaterialColumn mcR = new MaterialColumn();
		MaterialColumn mcH = new MaterialColumn();
		mcR.add(imgRefresh);
		mcH.add(txtBoxTotalHours);
		hTotal.add(mcR);
		hTotal.add(mcH);

		// flex.setWidget(13, 1, containerActivities);
		// flex.setWidget(14, 1, hTotal);
		flex.setWidget(15, 1, btnSubmit);
		// textPrinicialConsultantHours.setWidth("30px");
		// textSupervisorHours.setWidth("30px");
		lblLineOfService.setWidth("200px");
		lblCompanyName.setWidth("200px");
		lblDomain.setWidth("200px");
		lblSublineOfService.setWidth("200px");
		lblJobName.setWidth("200px");
		lblLocation.setWidth("200px");
		lblCountryName.setWidth("200px");
		lblAllocation.setWidth("200px");
		lblSegment.setWidth("200px");
		lblClientName.setWidth("200px");
		lblSupervisors.setWidth("200px");
		lblPrinicipalConsultant.setWidth("200px");
		listLineOfService.setWidth("200px");
		lblEmployee.setWidth("200px");
		listDomain.setWidth("200px");
		// listSubLineofService.setWidth("200px");
		// listSupervisor.setWidth("200px");
		// listPrincipalConsultant.setWidth("200px");
		listBoxAllocation.setWidth("200px");
		listBoxNature.setWidth("200px");
		listBoxSegment.setWidth("200px");
		txtJobName.setWidth("200px");
		listBoxCompanyName.setWidth("200px");
		listLocation.setWidth("200px");
		listCountry.setWidth("200px");
		// txtClient.setWidth("200px");
		// txtClientFee.setWidth("200px");

		lblCountryName.setVisible(false);
		listCountry.setVisible(false);

		MaterialRow hpnl = new MaterialRow();
		hpnl.add(flex);

		MaterialColumn vpnlJobCreation = new MaterialColumn();

		hpnl.add(costWidget);

		// add(hpnl);
		vpnlJobCreation.add(hpnl);
		fetchEmployees();

		// add(jobsListView);

		if (loggedInUser.getRoleId().getRoleId() == 5) {
			panel.add(vpnlJobCreation, "Job Creation");
		}
		panel.add(jobsListView, "Jobs List");
		panel.setWidth("800px");
		add(panel);

		costWidget.getImgRefresh().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				float totalCost = 0;
				for (int i = 0; i < vpnlEmployees.getWidgetCount(); i++) {
					AssignedToWidget assignedToWidget = (AssignedToWidget) vpnlEmployees.getWidget(i);
					for (int j = 0; j < employeesList.size(); j++) {
						if (employeesList.get(j).getUserId() == Integer.parseInt(assignedToWidget.getListAssign()
								.getValue(assignedToWidget.getListAssign().getSelectedIndex()))) {
							float employeeCost = employeesList.get(j).getRoleId().getChargeRate()
									* Integer.parseInt(assignedToWidget.getTxtDays().getText());
							totalCost = totalCost + employeeCost;
							break;
						}

					}
				}
				try {
					// float supervisorChargeRate =0;
					// for(int j=0;j<employeesList.size(); j++){
					// if(employeesList.get(j).getUserId() ==
					// Integer.parseInt(listSupervisor.getValue(listSupervisor.getSelectedIndex()))){
					// supervisorChargeRate =
					// employeesList.get(j).getRoleId().getChargeRate() ;
					// }}
					// float SupervisorCost =
					// Integer.parseInt(textSupervisorHours.getText()) *
					// supervisorChargeRate;
					// totalCost = totalCost + SupervisorCost;
					// totalCost =
					// totalCost+Integer.parseInt(textPrinicialConsultantHours.getText())
					// * 7500;
					//
					// costWidget.getCost().setText("Time Cost: "+ totalCost);
					// float recRate =
					// Integer.parseInt(txtClientFee.getText())/totalCost;
					// String recoveryRat ="";
					// recoveryRat = recRate+"";
					// if(recoveryRat.length()>4){
					// recoveryRat = recoveryRat.substring(0,4);
					// }

					// costWidget.getRecoveryRate().setText("Recovery Rate: " +
					// recoveryRat);
				} catch (Exception ex) {
					Window.alert(
							"Enter valid numeric value in Supervisor , princiap Consultant Hours and Client Fee Field");
					return;
				}
			}
		});

		listDomain.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// fetchSubLineofServices();
				fetchLineOFService();

			}
		});
		//
		listLocation.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (listLocation.getSelectedIndex() == 1) {
					lblCountryName.setVisible(true);
					listCountry.setVisible(true);
				} else {
					lblCountryName.setVisible(false);
					listCountry.setVisible(false);
				}
			}
		});
		// listDomain.addValueChangeHandler(new
		// ValueChangeHandler<String>() {
		//
		// @Override
		// public void onValueChange(ValueChangeEvent<String> event) {
		// fetchDomains();
		// }
		// });

		// @Override
		// public void onChange(ChangeEvent event) {
		// if(listLocation.getSelectedIndex()==1){
		// lblCountryName.setVisible(true);
		// listCountry.setVisible(true);
		// }else{
		// lblCountryName.setVisible(false);
		// listCountry.setVisible(false);
		// }
		// }});
		//
		// listLineOfService.addChangeHandler(new ChangeHandler() {
		//
		// @Override
		// public void onChange(ChangeEvent event) {
		// fetchDomains();
		// }
		// });
		//
		// listDomain.addChangeHandler(new ChangeHandler() {
		//
		// @Override
		// public void onChange(ChangeEvent event) {
		// fetchSubLineofServices();
		// }
		// });
		imgRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculateTotalHours();
			}

		});

		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// if(selectedJob==null && phases.size()<1){
				// Window.alert("Please add at least 1 phase for this job");
				// }else{
				// Job job = null;
				// if(selectedJob==null){
				// job = new Job();
				// }else{
				// job = selectedJob;
				// }
				// removed 1 bracket from down

				Job job = null;
				if (selectedJob == null) {
					job = new Job();
				} else {
					job = selectedJob;
				}

				// job.setClient(txtClient.getText());
				Domains domain = new Domains();
				domain.setDomainId(Integer.parseInt(listDomain.getValue(listDomain.getSelectedIndex())));
				LineofService lineofService = new LineofService();
				lineofService.setLineofServiceId(
						Integer.parseInt(listLineOfService.getValue(listLineOfService.getSelectedIndex())));
				// SubLineofService subLineofService = new SubLineofService();
				// subLineofService.setSubLineofServiceId(Integer.parseInt(listSubLineofService.getValue(listSubLineofService.getSelectedIndex())));
				Countries country = new Countries();
				country.setCountryId(Integer.parseInt(listCountry.getValue(listCountry.getSelectedIndex())));
				job.setDomainId(domain);
				job.setCountryId(country);
				job.setLineofServiceId(lineofService);
				// job.setSubLineofServiceId(subLineofService);
				job.setJobName(txtJobName.getText());
				job.setCompany(Integer.parseInt(listBoxCompanyName.getSelectedValue()));
				job.setAllocation(Integer.parseInt(listBoxAllocation.getSelectedValue()));
				job.setSegment(Integer.parseInt(listBoxSegment.getSelectedValue()));
				job.setNature(Integer.parseInt(listBoxNature.getSelectedValue()));
				job.setLocation(Integer.parseInt(listLocation.getSelectedValue()));

				job.setUsersList(listEmployee1.getSelectedValues());

				// job.setJobPhases(phases);
				// job.setClient(txtClient.getText());
				// job.setSupervisorHours(Integer.parseInt(textSupervisorHours.getText()));
				// job.setPrincipalConsultantHours(Integer.parseInt(textPrinicialConsultantHours.getText()));

				// try{
				// //job.setClientFee(Integer.parseInt(txtClientFee.getText()));
				// }catch(Exception ex){
				// Window.alert("Please enter valid numeric value in Client
				// Fee");
				// return;
				// }
				// User user = new User();
				// user.setUserId(Integer.parseInt(listEmployee1.getValue(listEmployee1.getSelectedIndex())));
				// job.setUserId(user);

				// User supervisor = new User();
				// supervisor.setUserId(Integer.parseInt(listSupervisor.getValue(listSupervisor.getSelectedIndex())));
				// job.setSupervisorId(supervisor);

				// User principalConsultant = new User();
				// principalConsultant.setUserId(Integer.parseInt(listPrincipalConsultant.getValue(listPrincipalConsultant.getSelectedIndex())));
				// job.setPrincipalConsultantId(principalConsultant);

				ArrayList<JobEmployees> jobEmployeesList = new ArrayList<JobEmployees>();

				// 2018 work
				ArrayList<JobActivityEntity> jobActivitEntities = new ArrayList<JobActivityEntity>();
				for (int j = 0; j < containerActivities.getWidgetCount(); j++) {
					JobActivities jobActivityView = (JobActivities) containerActivities.getWidget(j);
					JobActivityEntity jobActivityEntity = new JobActivityEntity();
					jobActivityView.setDataToEntity(jobActivityEntity);
					jobActivitEntities.add(jobActivityEntity);

					// job.setJobActivities(jobActivityView.getJobActivities());/////
					// here
				}
				job.setJobActivities(jobActivitEntities);

				for (int i = 0; i < vpnlEmployees.getWidgetCount(); i++) {
					AssignedToWidget assignedToWidget = (AssignedToWidget) vpnlEmployees.getWidget(i);
					///////
					JobEmployees jobEmployees = new JobEmployees();
					User employee = new User();
					employee.setUserId(Integer.parseInt(assignedToWidget.getListAssign()
							.getValue(assignedToWidget.getListAssign().getSelectedIndex())));
					employee.setName(assignedToWidget.getListAssign()
							.getItemText(assignedToWidget.getListAssign().getSelectedIndex()));
					jobEmployees.setEmployeeId(employee);

					jobEmployees.setJobEmployeeId(assignedToWidget.getJobEmployeeId());
					try {
						jobEmployees.setNoOfDays(Integer.parseInt(assignedToWidget.getTxtDays().getText()));
					} catch (Exception ex) {
						jobEmployees.setNoOfDays(0);
						// Window.alert("Please enter numeric value for No of
						// Days");
						// return;
					}
					///////
					jobEmployeesList.add(jobEmployees);
					if (jobEmployeesList.size() < 1) {
						Window.alert("Please Assign the job to atleast 1 employee");
						return;
					}
					/////
					/*
					 * JobEmployees jobSupervisor = new JobEmployees();
					 * //jobSupervisor.setEmployeeId(supervisor); try{ //
					 * jobSupervisor.setNoOfDays(Integer.parseInt(
					 * textSupervisorHours.getText())); }catch(Exception ex){
					 * Window.alert("Please enter numeric value for No of Days"
					 * ); return; } JobEmployees jobPrinicipalConsultant = new
					 * JobEmployees();
					 * jobPrinicipalConsultant.setEmployeeId(principalConsultant
					 * ); try{ //
					 * jobPrinicipalConsultant.setNoOfDays(Integer.parseInt(
					 * textPrinicialConsultantHours.getText()));
					 * }catch(Exception ex){
					 * Window.alert("Please enter numeric value for No of Days"
					 * ); return; } jobEmployeesList.add(jobSupervisor);
					 * jobEmployeesList.add(jobPrinicipalConsultant);
					 */

					// listEmployee1.addClickHandler(new ClickHandler() {
					//
					// @Override
					// public void onClick(ClickEvent event) {
					// // TODO Auto-generated method stub
					// int h = listBoxEmployee.getSelectedIndex();
					// Window.alert("i="+h);
					// }
					// });
					//
					// job.setJobEmployeesList(listEmployee1.getSelectedIndex());
					// job.setJobEmployeesList(jobEmployeesList);

				}

				final LoadingPopup loadingPopup = new LoadingPopup();
				loadingPopup.display();

				rpcService.saveJob(job, new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						MaterialToast.fireToast(result);

						if (loadingPopup != null) {
							loadingPopup.remove();
						}
						if (runnable != null) {
							runnable.run();
						}

						jobsListView.fetchJobs();
						populateJobCreationView();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fail saveJob" + caught.getLocalizedMessage());
						if (loadingPopup != null) {
							loadingPopup.remove();
						}
					}
				});
			}
		});
	}
	// btnPhase.addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// final JobPhaseView jobPhaseView = new JobPhaseView();
	// final MaterialWindow phaseWindow = new MaterialWindow();
	// phaseWindow.add(jobPhaseView);
	// phaseWindow.open();
	// phaseWindow.setTitle("Create Phase");
	// //final PopupsView popup = new PopupsView(jobPhaseView);
	// jobPhaseView.getBtnCancel().addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// phaseWindow.close();
	// }});
	//
	// jobPhaseView.getBtnSubmit().addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// Phases phase = new Phases();
	// phase.setPhaseName(jobPhaseView.getTxtPhase().getText());
	// phase.setStartDate(jobPhaseView.getStartDate().getValue());
	// phase.setDeliveryDate(jobPhaseView.getDeliveryDate().getValue());
	// phase.setSubmissionDate(jobPhaseView.getSubmissionDate().getValue());
	// phases.add(phase);
	// phaseWindow.close();
	// phaseWindow.close();
	// }});
	//
	// }});
	// }

	private void populateJobCreationView() {
		listBoxAllocation.setSelectedIndex(0);
		listLocation.setSelectedIndex(0);
		listBoxSegment.setSelectedIndex(0);
		listBoxCompanyName.setSelectedIndex(0);

		listBoxNature.setSelectedIndex(0);
		listDomain.setSelectedIndex(0);
		listLineOfService.clear();

		txtJobName.clear();
		// fetchDomains();
	}

	public void calculateTotalHours() {
		int hours = 0;
		for (int j = 0; j < containerActivities.getWidgetCount(); j++) {
			JobActivities jobActivityView = (JobActivities) containerActivities.getWidget(j);
			hours = hours + jobActivityView.totalhours;
		}
		txtBoxTotalHours.setText(hours + "");
	}

	private void fetchDomains() {
		int lineofServiceId = Integer.parseInt(listLineOfService.getValue(listLineOfService.getSelectedIndex()));
		rpcService.fetchDomains(lineofServiceId, new AsyncCallback<ArrayList<Domains>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch domain");
			}

			@Override
			public void onSuccess(ArrayList<Domains> result) {
				listDomain.clear();

				for (int i = 1; i < result.size(); i++) {

					listDomain.addItem(result.get(i).getDomainId() + "", result.get(i).getName());
				}
				// fetchSubLineofServices();
			}
		});
	}

	private void fetchLineOFService() {
		int domainId = Integer.parseInt(listDomain.getValue(listDomain.getSelectedIndex()));
		rpcService.fetchLineOfService(domainId, new AsyncCallback<ArrayList<LineofService>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch LineOfService");
			}

			@Override
			public void onSuccess(ArrayList<LineofService> result) {
				listLineOfService.clear();
				for (int i = 0; i < result.size(); i++) {
					listLineOfService.addItem(result.get(i).getLineofServiceId() + "", result.get(i).getName());
				}

			}
		});
	}

	// private void fetchSubLineofServices(){
	// int domainId =
	// Integer.parseInt(listDomain.getValue(listDomain.getSelectedIndex()));
	//
	// rpcService.fetchSublineofServices(domainId, new
	// AsyncCallback<ArrayList<SubLineofService>>(){
	//
	// @Override
	// public void onFailure(Throwable caught) {
	// // TODO Auto-generated method stub
	//
	//
	// }
	//
	// @Override
	// public void onSuccess(ArrayList<SubLineofService> result) {
	// listSubLineofService.clear();
	// for(int i=0; i< result.size(); i++){
	// listSubLineofService.addItem(
	// result.get(i).getSubLineofServiceId()+"",result.get(i).getName());
	// }
	// }});
	// }

	private void fetchEmployees() {
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				employeesList = result;

				///////// Add Employee
				final AssignedToWidget assignedToWidget = new AssignedToWidget(employeesList);
				// vpnlEmployees.add(assignedToWidget);
				assignedToWidget.getBtnRemove().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						vpnlEmployees.remove(assignedToWidget);

					}
				});

				for (int i = 0; i < result.size(); i++) {

					// listEmployee1.addItem(result.get(i).getName(),
					// result.get(i));

					// listEmployee1.addItem(result.get(i).getName(),
					// result.get(i).getUserId()+"");
					// listSupervisor.addItem(
					// result.get(i).getUserId()+"",result.get(i).getName());
					if (result.get(i).getRoleId().getRoleId() == 5) {
						// listPrincipalConsultant.addItem(
						// result.get(i).getUserId()+"",result.get(i).getName());

					}
				}
			}
		});

	}

	public void setJobAttributes(JobAttributesDTO jobAttributesDTO) {
		ArrayList<LineofService> lineofServices = new ArrayList<LineofService>();
		ArrayList<SubLineofService> sublineofServices = new ArrayList<SubLineofService>();
		ArrayList<Domains> domains = new ArrayList<Domains>();
		ArrayList<Countries> countries = new ArrayList<Countries>();
		ArrayList<User> users = new ArrayList<User>();

		countries = jobAttributesDTO.getCountries();
		lineofServices = jobAttributesDTO.getLineofService();
		sublineofServices = jobAttributesDTO.getSubLineofService();
		domains = jobAttributesDTO.getDomains();
		countries = jobAttributesDTO.getCountries();
		users = jobAttributesDTO.getUsers();

		listCountry.clear();
		listLineOfService.clear();
		listEmployee1.clear();
		listDomain.clear();

		for (int i = 0; i < countries.size(); i++) {
			listCountry.addItem(countries.get(i).getCountryId() + "", countries.get(i).getName());
		}
		for (int i = 0; i < lineofServices.size(); i++) {
			// listLineOfService.addItem(lineofServices.get(i).getLineofServiceId()
			// + "", lineofServices.get(i).getName());
		}
		// for(int i=0; i< sublineofServices.size(); i++){
		// listSubLineofService.addItem(
		// sublineofServices.get(i).getSubLineofServiceId()+"",sublineofServices.get(i).getName());
		// }
		listDomain.addItem("0", "Select Domain");
		for (int i = 0; i < domains.size(); i++) {

			listDomain.addItem(domains.get(i).getDomainId() + "", domains.get(i).getName());
		}

		for (int i = 0; i < users.size(); i++) {
			listEmployee1.addItem(users.get(i).getName(), users.get(i));

		}

	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public MaterialListBox getListLineOfService() {
		return listLineOfService;
	}

	public void setListLineOfService(MaterialListBox listLineOfService) {
		this.listLineOfService = listLineOfService;
	}

	public MaterialListBox getListDomain() {
		return listDomain;
	}

	public void setListDomain(MaterialListBox listDomain) {
		this.listDomain = listDomain;
	}

	// public MaterialListBox getListSubLineofService() {
	// return listSubLineofService;
	// }
	//
	// public void setListSubLineofService(MaterialListBox listSubLineofService)
	// {
	// this.listSubLineofService = listSubLineofService;
	// }

	public MaterialListBox getListLocation() {
		return listLocation;
	}

	public void setListLocation(MaterialListBox listLocation) {
		this.listLocation = listLocation;
	}

	public MaterialTextBox getTxtJobName() {
		return txtJobName;
	}

	public void setTxtJobName(MaterialTextBox txtJobName) {
		this.txtJobName = txtJobName;
	}

	public MaterialListBox getListCountry() {
		return listCountry;
	}

	public void setListCountry(MaterialListBox listCountry) {
		this.listCountry = listCountry;
	}

	// public MaterialTextBox getTxtClient() {
	// return txtClient;
	// }
	//
	// public void setTxtClient(MaterialTextBox txtClient) {
	// this.txtClient = txtClient;
	// }

	public ArrayList<Phases> getPhases() {
		return phases;
	}

	public void setPhases(ArrayList<Phases> phases) {
		this.phases = phases;
	}

	public JobsListView getJobsListView() {
		return jobsListView;
	}

	public void setJobsListView(JobsListView jobsListView) {
		this.jobsListView = jobsListView;
	}

	public Job getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(Job selectedJob) {
		this.selectedJob = selectedJob;
	}

	// public MaterialListBox getListSupervisor() {
	// return listSupervisor;
	// }
	//
	// public void setListSupervisor(MaterialListBox listSupervisor) {
	// this.listSupervisor = listSupervisor;
	// }
	//
	// public MaterialListBox getListPrincipalConsultant() {
	// return listPrincipalConsultant;
	// }
	//
	// public void setListPrincipalConsultant(MaterialListBox
	// listPrincipalConsultant) {
	// this.listPrincipalConsultant = listPrincipalConsultant;
	// }

	public MaterialColumn getVpnlEmployees() {
		return vpnlEmployees;
	}

	public void setVpnlEmployees(MaterialColumn vpnlEmployees) {
		this.vpnlEmployees = vpnlEmployees;
	}

	// public MaterialTextBox getTxtClientFee() {
	// return txtClientFee;
	// }
	//
	// public void setTxtClientFee(MaterialTextBox txtClientFee) {
	// this.txtClientFee = txtClientFee;
	// }
	//
	// public MaterialTextBox getTextSupervisorHours() {
	// return textSupervisorHours;
	// }
	//
	// public void setTextSupervisorHours(MaterialTextBox textSupervisorHours) {
	// this.textSupervisorHours = textSupervisorHours;
	// }
	//
	// public MaterialTextBox getTextPrinicialConsultantHours() {
	// return textPrinicialConsultantHours;
	// }
	//
	// public void setTextPrinicialConsultantHours(MaterialTextBox
	// textPrinicialConsultantHours) {
	// this.textPrinicialConsultantHours = textPrinicialConsultantHours;
	// }

	public void populate(Job selectedJob) {

		txtJobName.setText(selectedJob.getJobName());
		listBoxAllocation.setSelectedValue(selectedJob.getAllocation() + "");
		listBoxCompanyName.setSelectedValue(selectedJob.getCompany() + "");
		listBoxSegment.setSelectedValue(selectedJob.getSegment() + "");
		listBoxNature.setSelectedValue(selectedJob.getNature() + "");
		listLocation.setSelectedValue(selectedJob.getLocation() + "");

		for (int i = 0; i < containerActivities.getWidgetCount(); i++) {
			JobActivities jobActivityView = (JobActivities) containerActivities.getWidget(i);

			jobActivityView.poupulateSavedActivites(selectedJob.getJobActivities());
		}
		// jobActivityView.poupulateSavedActivites(selectedJob.getJobActivities());

		// TODO: set all the data here from saved job like above 2 lines.. (this
		// will show corret saved data when any job will be clicked from job
		// list)
		// activities is already populating in below line, dont work on them
		// jobActivityView.poupulateSavedActivites(selectedJob.getJobActivities());

	}

	private void fetchDomains(int lineofservice) {
		rpcService.fetchDomains(lineofservice, new AsyncCallback<ArrayList<Domains>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch domain");
			}

			@Override
			public void onSuccess(ArrayList<Domains> result) {
				listDomain.clear();
				for (int i = 0; i < result.size(); i++) {
					listDomain.addItem(result.get(i).getDomainId() + "", result.get(i).getName());
				}
				// fetchSubLineofServices();
			}
		});
	}

	public StackPanel getPanel() {
		return panel;
	}

	public MaterialColumn getContainerActivities() {
		return containerActivities;
	}

	public MaterialTextBox getTxtBoxTotalHours() {
		return txtBoxTotalHours;
	}

	public MaterialComboBox<User> getListEmployee1() {
		return listEmployee1;
	}

	public void setListEmployee1(MaterialComboBox<User> listEmployee1) {
		this.listEmployee1 = listEmployee1;
	}

}
