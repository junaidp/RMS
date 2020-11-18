package com.leavemanagement.client.view;

import java.util.Date;

import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialColumn;
import com.google.gwt.user.datepicker.client.DateBox;

public class JobPhaseView extends MaterialColumn {
	
	private MaterialTextBox txtPhase = new MaterialTextBox();
	private DateBox startDate = new DateBox();
	private DateBox submissionDate = new DateBox();
	private DateBox deliveryDate = new DateBox();
	private MaterialButton btnCancel = new MaterialButton("Cancel");
	private MaterialButton btnSubmit = new MaterialButton("Submit/Update Phase");
	private MaterialButton btnDelete = new MaterialButton("Delete Phase");
	
	public JobPhaseView(){
		btnDelete.setVisible(false);
		Label lblPhase = new Label("Phase");
		Label lblStartDate = new Label("Start Date");
		Label lblDeliveryDate = new Label("Client Delivery Date");
		Label lblSubmissionDate = new Label("Submission Date");
		FlexTable flex = new FlexTable();
		flex.setWidget(1, 0, lblPhase);
		flex.setWidget(1, 1, txtPhase);
		flex.setWidget(2, 0, lblStartDate);
		flex.setWidget(2, 1, startDate);
		flex.setWidget(3, 0, lblDeliveryDate);
		flex.setWidget(3, 1, deliveryDate);
		flex.setWidget(4, 0, lblSubmissionDate);
		flex.setWidget(4, 1, submissionDate);
		flex.setWidget(5, 0, btnCancel);
		flex.setWidget(5, 1, btnSubmit);
		flex.setWidget(5, 2, btnDelete);
		
		lblPhase.setWidth("200px");
		lblSubmissionDate.setWidth("200px");
		lblStartDate.setWidth("200px");
		lblDeliveryDate.setWidth("200px");
		txtPhase.setWidth("200px");
		startDate.setWidth("200px");
		deliveryDate.setWidth("200px");
		submissionDate.setWidth("200px");
		add(flex);
		
	}

	public MaterialTextBox getTxtPhase() {
		return txtPhase;
	}

	public void setTxtPhase(MaterialTextBox txtPhase) {
		this.txtPhase = txtPhase;
	}

	public DateBox getStartDate() {
		return startDate;
	}

	public void setStartDate(DateBox startDate) {
		this.startDate = startDate;
	}

	public DateBox getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(DateBox deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public MaterialButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(MaterialButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public DateBox getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(DateBox submissionDate) {
		this.submissionDate = submissionDate;
	}

	public MaterialButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(MaterialButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	
}
