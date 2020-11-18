package com.leavemanagement.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.MainPresenter.Display;

import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;

public class MainView extends MaterialColumn implements IsWidget, Display {

	private MaterialColumn vp;
	private MaterialColumn vpnlAvailableLeaves = new MaterialColumn();
	private MaterialListBox listLeaves = new MaterialListBox();
	private DateBox from = new DateBox();
	private DateBox to = new DateBox();
	private MaterialLabel lblNoOfDays = new MaterialLabel("");
	private MaterialRichEditor reason = new MaterialRichEditor();
	private MaterialButton btnSubmit = new MaterialButton("Submit");
	private Anchor logOff = new Anchor("Log off");
	private MaterialLabel loggedInUserName = new MaterialLabel();
	private Anchor leaveHistory = new Anchor("Leave History");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor adminView = new Anchor("Admin");

	public MainView() {

		this.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		MaterialRow hpnlWelcome = new MaterialRow();
		hpnlWelcome.setWidth("100%");
		MaterialRow hpnlSpace = new MaterialRow();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);

		loggedInUserName.setFontSize(1.2, Unit.EM);

		loggedInUserName.addStyleName("blueBold");
		hpnlWelcome.add(loggedInUserName);
		MaterialRow hpnlHeader = new MaterialRow();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		MaterialLabel lblHeader = new MaterialLabel("Leave Management System");
		lblHeader.addStyleName("headerSignin");
		hpnlWelcome.addStyleName("headerSignin");
		// hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		MaterialLabel lblAvailableLeaves = new MaterialLabel("Available Leaves");
		lblAvailableLeaves.setFontSize(1.2, Unit.EM);
		MaterialColumn vpnlLeave = new MaterialColumn();
		vpnlLeave.setWidth("100%");
		// MaterialRow hpnlSpaceLeave = new MaterialRow();
		// hpnlSpaceLeave.setWidth("800px");
		// hpnlLeave.add(hpnlSpaceLeave);
		// vpnlLeave.add(changePassword);
		// vpnlLeave.add(leaveHistory);
		// vpnlLeave.add(adminView);
		// vpnlLeave.add(logOff);
		add(hpnlHeader);
		add(vpnlLeave);

		add(vpnlAvailableLeaves);

		MaterialLabel lblRequest = new MaterialLabel("Leave Request");

		lblRequest.addStyleName("blueBold");
		lblAvailableLeaves.addStyleName("blueBold");
		lblRequest.setFontSize(1.2, Unit.EM);

		add(lblRequest);
		add(listLeaves);
		MaterialRow hpnlDates = new MaterialRow();
		MaterialColumn vpnlFrom = new MaterialColumn();
		MaterialColumn vpnlTo = new MaterialColumn();
		MaterialLabel From = new MaterialLabel("From");
		vpnlFrom.add(From);
		vpnlFrom.add(from);
		vpnlTo.add(new MaterialLabel("To"));
		vpnlTo.add(to);
		hpnlDates.add(vpnlFrom);
		hpnlDates.add(vpnlTo);
		add(hpnlDates);
		add(lblNoOfDays);

		MaterialLabel lblReason = new MaterialLabel("Reason");
		lblReason.setFontSize(1.2, Unit.EM);
		lblReason.addStyleName("blueBold");
		add(lblReason);
		add(reason);

		btnSubmit.getElement().getStyle().setMarginLeft(875, Unit.PX);
		add(btnSubmit);
		reason.setSize("400px", "150px");

	}

	// public Widget asWidget() {
	// return btnSend;
	//
	// }

	public MaterialColumn getVpnlAvailableLeaves() {
		return vpnlAvailableLeaves;
	}

	public void setVpnlAvailableLeaves(MaterialColumn vpnlAvailableLeaves) {
		this.vpnlAvailableLeaves = vpnlAvailableLeaves;
	}

	public MaterialListBox getListLeaves() {
		return listLeaves;
	}

	public void setListLeaves(MaterialListBox listLeaves) {
		this.listLeaves = listLeaves;
	}

	public DateBox getFrom() {
		return from;
	}

	public void setFrom(DateBox from) {
		this.from = from;
	}

	public DateBox getTo() {
		return to;
	}

	public void setTo(DateBox to) {
		this.to = to;
	}

	public MaterialLabel getLblNoOfDays() {
		return lblNoOfDays;
	}

	public void setLblNoOfDays(MaterialLabel lblNoOfDays) {
		this.lblNoOfDays = lblNoOfDays;
	}

	public MaterialRichEditor getReason() {
		return reason;
	}

	public void setReason(MaterialRichEditor reason) {
		this.reason = reason;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public Anchor getLogOff() {
		return logOff;
	}

	public void setLogOff(Anchor logOff) {
		this.logOff = logOff;
	}

	public MaterialLabel getLoggedInUserName() {
		return loggedInUserName;
	}

	public void setLoggedInUserName(MaterialLabel loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	public Anchor getLeaveHistory() {
		return leaveHistory;
	}

	public void setLeaveHistory(Anchor leaveHistory) {
		this.leaveHistory = leaveHistory;
	}

	public Anchor getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Anchor changePassword) {
		this.changePassword = changePassword;
	}

	public Anchor getAdminView() {
		return adminView;
	}

	public void setAdminView(Anchor adminView) {
		this.adminView = adminView;
	}

}
