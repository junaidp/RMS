package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.AddUserPresenter.Display;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class AddUserView extends MaterialColumn implements Display {

	private MaterialButton btnSend = new MaterialButton("Save");
	MaterialTextBox txtUser = new MaterialTextBox();
	PasswordTextBox txtPassword = new PasswordTextBox();
	PasswordTextBox txtConfrimPassword = new PasswordTextBox();
	MaterialTextBox txtEmail = new MaterialTextBox();
	MaterialTextBox txtExamLeaves = new MaterialTextBox();
	MaterialListBox listUser = new MaterialListBox();

	MaterialListBox listDesignation = new MaterialListBox();
	MaterialListBox listReportingTo = new MaterialListBox();
	DateBox joiningDate = new DateBox();
	MaterialTextBox contactNumber = new MaterialTextBox();
	MaterialTextBox chargeRate = new MaterialTextBox();
	MaterialTextBox bankAccountNumber = new MaterialTextBox();
	private MaterialButton btnRemove = new MaterialButton("delete user");
	private MaterialButton btnUpdate = new MaterialButton("update");

	public AddUserView() {
		MaterialColumn vpnl = new MaterialColumn();
		add(vpnl);
		// chargeRate.setEnabled(false);
		Label lblHeading = new Label("Add a new user");
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth() - 100 + "px");
		listUser.addItem("Select User");
		MaterialRow hp = new MaterialRow();
		vpnl.add(hp);
		hp.setWidth("100%");
		hp.add(lblHeading);

		FlexTable flex = new FlexTable();
		Label lblJoiningDate = new Label("Joingin Date");
		Label lblcontactNumber = new Label("Emergency Contact Number");
		Label lblChargeRate = new Label("Charge Rate");
		Label lblBankAccountNumber = new Label("Bank Account Number");
		Label lblDesignation = new Label("Designation");
		Label lblUser = new Label("UserName");
		Label lblPassword = new Label("Password");
		Label lblConfirmPassword = new Label("Confirm Password");
		Label lblEmail = new Label("Email");
		Label lblExamLeaves = new Label("Exam Leaves");
		Label lblEditUser = new Label("Edit User");
		MaterialButton btnBack = new MaterialButton("Back");
		Label lblReportingTo = new Label("Reporting To");

		flex.setWidget(0, 0, lblUser);
		flex.setWidget(0, 1, txtUser);
		flex.setWidget(1, 0, lblPassword);
		flex.setWidget(1, 1, txtPassword);
		flex.setWidget(2, 0, lblConfirmPassword);
		flex.setWidget(2, 1, txtConfrimPassword);
		flex.setWidget(3, 0, lblEmail);
		flex.setWidget(3, 1, txtEmail);
		flex.setWidget(4, 0, lblExamLeaves);
		flex.setWidget(4, 1, txtExamLeaves);
		flex.setWidget(5, 0, lblJoiningDate);
		flex.setWidget(5, 1, joiningDate);
		flex.setWidget(6, 0, lblcontactNumber);
		flex.setWidget(6, 1, contactNumber);
		flex.setWidget(7, 0, lblBankAccountNumber);
		flex.setWidget(7, 1, bankAccountNumber);
		flex.setWidget(8, 0, lblDesignation);
		flex.setWidget(8, 1, listDesignation);
		flex.setWidget(9, 0, lblReportingTo);
		flex.setWidget(9, 1, listReportingTo);
		// flex.setWidget(10, 0, lblChargeRate);
		// flex.setWidget(10, 1, chargeRate);
		// flex.setWidget(10, 2, new Label("Numeric value only"));
		flex.setWidget(0, 2, lblEditUser);
		flex.setWidget(0, 3, listUser);
		flex.setWidget(1, 3, btnBack);

		vpnl.add(flex);
		MaterialRow hpnButton = new MaterialRow();
		MaterialRow hpnSpace = new MaterialRow();

		MaterialColumn mcSend = new MaterialColumn();
		MaterialColumn mcUPDATE = new MaterialColumn();
		MaterialColumn mcRemore = new MaterialColumn();
		mcSend.add(btnSend);

		mcUPDATE.add(btnUpdate);
		mcRemore.add(btnRemove);
		hpnButton.add(mcSend);
		hpnButton.add(mcUPDATE);
		hpnButton.add(mcRemore);
		vpnl.add(hpnButton);
		layout();
		// vpnl.setSpacing(4);
		btnBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.back();

			}
		});

	}

	public void layout() {

		txtUser.setWidth("200px");
		txtPassword.setWidth("200px");
		txtConfrimPassword.setWidth("200px");
		txtEmail.setWidth("200px");
		listUser.setWidth("200px");
		listDesignation.setWidth("200px");
		listReportingTo.setWidth("200px");
		joiningDate.setWidth("200px");
		contactNumber.setWidth("200px");
		chargeRate.setWidth("200px");
		bankAccountNumber.setWidth("200px");

		txtExamLeaves.setWidth("200px");
	}
	


	public MaterialButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(MaterialButton btnSend) {
		this.btnSend = btnSend;
	}

	public MaterialTextBox getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(MaterialTextBox txtUser) {
		this.txtUser = txtUser;
	}

	public PasswordTextBox getTxtPassword() {
		return txtPassword;
	}

	public MaterialTextBox getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(MaterialTextBox txtEmail) {
		this.txtEmail = txtEmail;
	}

	public PasswordTextBox getTxtConfrimPassword() {
		return txtConfrimPassword;
	}

	public void setTxtConfrimPassword(PasswordTextBox txtConfrimPassword) {
		this.txtConfrimPassword = txtConfrimPassword;
	}

	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}

	public MaterialTextBox getTxtExamLeaves() {
		return txtExamLeaves;
	}

	public void setTxtExamLeaves(MaterialTextBox txtExamLeaves) {
		this.txtExamLeaves = txtExamLeaves;
	}

	public MaterialListBox getListUser() {
		return listUser;
	}

	public void setListUser(MaterialListBox listUser) {
		this.listUser = listUser;
	}

	public MaterialButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(MaterialButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public MaterialListBox getListDesignation() {
		return listDesignation;
	}

	public void setListDesignation(MaterialListBox listDesignation) {
		this.listDesignation = listDesignation;
	}

	public DateBox getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(DateBox joiningDate) {
		this.joiningDate = joiningDate;
	}

	public MaterialTextBox getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(MaterialTextBox contactNumber) {
		this.contactNumber = contactNumber;
	}

	public MaterialTextBox getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(MaterialTextBox bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public MaterialListBox getListReportingTo() {
		return listReportingTo;
	}

	public void setListReportingTo(MaterialListBox listReportingTo) {
		this.listReportingTo = listReportingTo;
	}

	public MaterialButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(MaterialButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public MaterialTextBox getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(MaterialTextBox chargeRate) {
		this.chargeRate = chargeRate;
	}

}
