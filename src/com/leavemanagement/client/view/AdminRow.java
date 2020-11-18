package com.leavemanagement.client.view;

import javax.persistence.Column;

import gwt.material.design.client.ui.MaterialButton;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialTextBox;

public class AdminRow extends MaterialRow{
	
	private Label lblName = new Label("");
	private Label lblType = new Label("");
	private Label lblFrom = new Label("");
	private Label lblTo = new Label("");
	private Label lblDays = new Label("");
	private MaterialButton btnApprove = new MaterialButton("Approve");
	private MaterialButton btnDecline = new MaterialButton("Decline");
	private MaterialTextBox txtRemarks =  new MaterialTextBox();
	private Label lblStatus = new Label("");
	private Label lblRemarks = new Label();
	MaterialRow hpnlButton = new MaterialRow();
	public AdminRow(){
		setStyleName("form-row");
		lblName.setWidth("120px");
		lblType.setWidth("120px");
		lblFrom.setWidth("140px");
		lblTo.setWidth("140px");
		lblDays.setWidth("120px");
		
		MaterialColumn colBtnApprove = new MaterialColumn();
		colBtnApprove.add(btnApprove);
		hpnlButton.add(colBtnApprove);
		MaterialColumn colBtnDecline = new MaterialColumn();
		colBtnDecline.add(btnDecline);
		hpnlButton.add(colBtnDecline);
		MaterialColumn colTxtRemarks = new MaterialColumn();
		colTxtRemarks.add(txtRemarks);
		hpnlButton.add(colTxtRemarks);
		MaterialColumn colLblName = new MaterialColumn();
		colLblName.add(lblName);
		add(colLblName);
		MaterialColumn colLblType = new MaterialColumn();
		colLblType.add(lblType);
		add(colLblType);
		MaterialColumn colLblFrom = new MaterialColumn();
		colLblFrom.add(lblFrom);
		add(colLblFrom);
		MaterialColumn colLblTo = new MaterialColumn();
		colLblTo.add(lblTo);
		add(colLblTo);
		MaterialColumn colLblDays = new MaterialColumn();
		colLblDays.add(lblDays);
		add(colLblDays);
		MaterialColumn colHpn1Button = new MaterialColumn();
		colHpn1Button.add(hpnlButton);
		add(colHpn1Button);
		MaterialColumn colLblStatus = new MaterialColumn();
		colLblStatus.add(lblStatus);
		add(colLblStatus);
		MaterialColumn colLblRemarks = new MaterialColumn();
		colLblRemarks.add(lblRemarks);
		add(colLblRemarks);
		
		hpnlButton.setVisible(false);
//		lblStatus.setVisible(false);
		txtRemarks.setVisible(false);
	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}

	public Label getLblType() {
		return lblType;
	}

	public void setLblType(Label lblType) {
		this.lblType = lblType;
	}

	public Label getLblFrom() {
		return lblFrom;
	}

	public void setLblFrom(Label lblFrom) {
		this.lblFrom = lblFrom;
	}

	public Label getLblTo() {
		return lblTo;
	}

	public void setLblTo(Label lblTo) {
		this.lblTo = lblTo;
	}

	public Label getLblDays() {
		return lblDays;
	}

	public void setLblDays(Label lblDays) {
		this.lblDays = lblDays;
	}

	
	public MaterialButton getBtnApprove() {
		return btnApprove;
	}

	public void setBtnApprove(MaterialButton btnApprove) {
		this.btnApprove = btnApprove;
	}

	public MaterialButton getBtnDecline() {
		return btnDecline;
	}

	public void setBtnDecline(MaterialButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public MaterialTextBox getTxtRemarks() {
		return txtRemarks;
	}

	public void setTxtRemarks(MaterialTextBox txtRemarks) {
		this.txtRemarks = txtRemarks;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}

	public MaterialRow getHpnlButton() {
		return hpnlButton;
	}

	public void setHpnlButton(MaterialRow hpnlButton) {
		this.hpnlButton = hpnlButton;
	}

	public Label getLblRemarks() {
		return lblRemarks;
	}

	public void setLblRemarks(Label lblRemarks) {
		this.lblRemarks = lblRemarks;
	}

}
