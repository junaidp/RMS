package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.JobActivityEntity;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class JobActivities extends MaterialRow {
	int totalhours = 0;
	ArrayList<JobActivityEntity> jobActivities = new ArrayList<JobActivityEntity>();
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	// private MaterialTextBox txtBoxTotalHours = new MaterialTextBox();
	private MaterialTextBox txtBoxPlanning = new MaterialTextBox();
	private MaterialTextBox txtBoxReporting = new MaterialTextBox();
	private MaterialTextBox txtBoxExecution = new MaterialTextBox();
	private MaterialTextBox txtBoxFollowUp = new MaterialTextBox();
	private MaterialTextBox textBoxUsers = new MaterialTextBox();
	private MaterialTextBox txtBoxTotalHours = new MaterialTextBox();
	ArrayList<JobActivityEntity> savedActivites;

	public JobActivities() {

		MaterialLabel lblDesignation = new MaterialLabel("User");
		MaterialLabel lblPlanning = new MaterialLabel("Planning");
		MaterialLabel lblReporting = new MaterialLabel("Reporting");
		MaterialLabel lblExecution = new MaterialLabel("Execution");
		MaterialLabel lblFollowUp = new MaterialLabel("Follow Up");
		MaterialLabel lblTotalHours = new MaterialLabel("Total Hours");

		// populateLIstBoxDesignation();
		// MaterialButton btnSave = new MaterialButton("Save");
		txtBoxTotalHours.setEnabled(false);

		// Styling
		lblDesignation.addStyleName("blueBold");
		lblPlanning.addStyleName("blueBold");
		lblExecution.addStyleName("blueBold");
		lblReporting.addStyleName("blueBold");
		lblFollowUp.addStyleName("blueBold");
		lblTotalHours.addStyleName("blueBold");

		setTextBoxes(textBoxUsers);

		FlexTable flex = new FlexTable();

		flex.setWidget(1, 0, lblDesignation);
		flex.setWidget(2, 0, textBoxUsers);

		flex.setWidget(1, 1, lblPlanning);
		flex.setWidget(2, 1, txtBoxPlanning);

		flex.setWidget(1, 2, lblReporting);
		flex.setWidget(2, 2, txtBoxReporting);

		flex.setWidget(1, 3, lblExecution);
		flex.setWidget(2, 3, txtBoxExecution);

		flex.setWidget(1, 4, lblFollowUp);
		flex.setWidget(2, 4, txtBoxFollowUp);

		// flex.setWidget(3, 2, btnSave);

		flex.setWidget(1, 5, lblTotalHours);
		flex.setWidget(2, 5, txtBoxTotalHours);

		add(flex);

		calculateHours();
		// setHandlers(listBoxUsers, btnSave);

	}

	private void setTextBoxes(final MaterialTextBox textBoxUsers) {
		txtBoxExecution.setWidth("100px");
		textBoxUsers.setWidth("100px");
		txtBoxFollowUp.setWidth("100px");
		txtBoxPlanning.setWidth("100px");
		txtBoxReporting.setWidth("100px");
		// txtBoxTotalHours.setWidth("100px");
		// End styling

		txtBoxFollowUp.setAllowBlank(false);
		txtBoxPlanning.setAllowBlank(false);
		txtBoxReporting.setAllowBlank(false);
		txtBoxExecution.setAllowBlank(false);
		// txtBoxTotalHours.setEnabled(false);

		txtBoxFollowUp.setPlaceholder("Enter hours");
		txtBoxPlanning.setPlaceholder("Enter hours");
		txtBoxReporting.setPlaceholder("Enter hours");
		txtBoxExecution.setPlaceholder("Enter hours");

		txtBoxFollowUp.setText("0");
		txtBoxPlanning.setText("0");
		txtBoxReporting.setText("0");
		txtBoxExecution.setText("0");

	}

	private void emptyTextBoxes() {
		txtBoxFollowUp.clear();
		txtBoxPlanning.clear();
		txtBoxReporting.clear();
		txtBoxExecution.clear();

	}

	private void setHandlers(MaterialButton btnSave) {

		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				JobActivityEntity jobActivityEntity = new JobActivityEntity();
				jobActivityEntity.setPlanning(Integer.parseInt(txtBoxPlanning.getText()));
				jobActivityEntity.setReporting(Integer.parseInt(txtBoxReporting.getText()));
				jobActivityEntity.setExecution(Integer.parseInt(txtBoxExecution.getText()));
				jobActivityEntity.setFollowup(Integer.parseInt(txtBoxFollowUp.getText()));
				User user = new User();
				user.setUserId(Integer.parseInt(textBoxUsers.getText()));
				jobActivityEntity.setUserId(user);
				jobActivityEntity.setTotalHours(totalhours);
				jobActivities.add(jobActivityEntity);
				emptyTextBoxes();
				// MaterialToast.fireToast("Hours saved for : "+
				// listBoxDesignation.getSelectedItemText());
				try {
					// listBoxDesignation.setSelectedIndex(listBoxDesignation.getSelectedIndex()+1);
				} catch (Exception ex) {
					// no more data in listbox
				}

			}
		});
	}

	private void calculateHours() {

		txtBoxPlanning.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				calculateHours(txtBoxPlanning);
			}
		});

		txtBoxExecution.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				calculateHours(txtBoxExecution);
			}
		});

		txtBoxReporting.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				calculateHours(txtBoxReporting);
			}
		});

		txtBoxFollowUp.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				calculateHours(txtBoxFollowUp);
			}
		});
	}

	private void calculateHours(MaterialTextBox textBox) {
		try {
			if (textBox != null) {
				textBox.reset();
				Integer.parseInt(textBox.getText());
			}

			totalhours = Integer.parseInt(txtBoxPlanning.getText()) + Integer.parseInt(txtBoxExecution.getText())
					+ Integer.parseInt(txtBoxReporting.getText()) + Integer.parseInt(txtBoxFollowUp.getText());
			txtBoxTotalHours.setText(totalhours + "");

		} catch (Exception ex) {
			textBox.setError("Enter valid number");
		}

	}

	public ArrayList<JobActivityEntity> getJobActivities() {
		return jobActivities;
	}

	public void setJobActivities(ArrayList<JobActivityEntity> jobActivities) {
		this.jobActivities = jobActivities;
	}

	public void poupulateSavedActivites(ArrayList<JobActivityEntity> activites) {

		for (int i = 0; i < activites.size(); i++) {
			if (Integer.parseInt(textBoxUsers.getId()) == activites.get(i).getUserId().getUserId()) {
				txtBoxExecution.setText(activites.get(i).getExecution() + "");
				txtBoxFollowUp.setText(activites.get(i).getFollowup() + "");
				txtBoxPlanning.setText(activites.get(i).getPlanning() + "");
				txtBoxReporting.setText(activites.get(i).getReporting() + "");
			}
		}
		calculateHours(null);

	}

	public MaterialTextBox getTextBoxUsers() {
		return textBoxUsers;
	}

	public void setDataToEntity(JobActivityEntity jobActivityEntity) {
		User user = new User();
		user.setUserId(Integer.parseInt(textBoxUsers.getId() + ""));
		user.setName(textBoxUsers.getName());
		jobActivityEntity.setUserId(user);
		jobActivityEntity.setExecution(Integer.parseInt(txtBoxExecution.getText()));
		jobActivityEntity.setFollowup(Integer.parseInt(txtBoxFollowUp.getText()));
		jobActivityEntity.setPlanning(Integer.parseInt(txtBoxPlanning.getText()));
		jobActivityEntity.setReporting(Integer.parseInt(txtBoxReporting.getText()));
		jobActivityEntity.setTotalHours(totalhours);
	}

	public MaterialTextBox getTxtBoxTotalHours() {
		return txtBoxTotalHours;
	}

}
