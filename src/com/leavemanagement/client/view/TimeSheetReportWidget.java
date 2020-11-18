package com.leavemanagement.client.view;

import java.util.ArrayList;

import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;

import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.shared.UserReportDTO;

public class TimeSheetReportWidget extends MaterialCard{
	
	MaterialCardTitle lblHeading = new MaterialCardTitle();
	MaterialCardContent con = new MaterialCardContent();
	private MaterialColumn vpnlContainer = new MaterialColumn();
	private MaterialRow hpnl = new MaterialRow();
	private Label totalHours = new Label();
	private Label recoveryRate = new Label();
	private Label timeCost = new Label();
	private Label fee = new Label();
	
	private float totaltimeCost=0;
	public TimeSheetReportWidget(ArrayList<UserReportDTO> usersList){
		
		add(lblHeading);
		MaterialRow hpnlHeadings = new MaterialRow();
		Label lblUserName = new Label("UserName");
		Label lblHours = new Label("Hours");
		Label lblTimeCost = new Label("Time Cost");
		MaterialColumn colLblUserName = new MaterialColumn();
		colLblUserName.add(lblUserName);
		hpnlHeadings.add(colLblUserName);
		MaterialColumn colLblHours = new MaterialColumn();
        colLblHours.add(lblHours);
		hpnlHeadings.add(colLblHours);
		MaterialColumn colLblTimeCost = new MaterialColumn();
        colLblTimeCost.add(lblTimeCost);
		hpnlHeadings.add(colLblTimeCost);
		lblUserName.setWidth("100px");
		lblHours.setWidth("100px");
		lblTimeCost.setWidth("100px");
		lblUserName.setStyleName("bold");
		lblHours.setStyleName("bold");
		lblTimeCost.setStyleName("bold");
		
		con.add(hpnlHeadings);
		
		lblHeading.addStyleName("blueBold");
		totalHours.setWidth("100px");
		recoveryRate.setWidth("100px");
		fee.setWidth("100px");
//		float totalHours =0;
		
		for(int i=0;i< usersList.size(); i++){
			UserRepotWidget userReportWidget = new UserRepotWidget();
			userReportWidget.getActualHours().setText(usersList.get(i).getActualHours()+"");
			userReportWidget.getActualTimeCost().setText(usersList.get(i).getTimeCost()+"");
			userReportWidget.getUserName().setText(usersList.get(i).getUserName());
			totaltimeCost = totaltimeCost+ Float.parseFloat(userReportWidget.getActualTimeCost().getText());
//			totalHours = totalHours + usersList.get(i).getTimeCost();
//			totalTimeCost = totalTimeCost + usersList.get(i).getTimeCost();
			
			vpnlContainer.add(userReportWidget);
		}
		
		Label lblTotal = new Label("Total");
		Label lblFee = new Label("Fee");
		lblTotal.setStyleName("bold");
		Label lblRecoveryRate = new Label("RecoveryRate");
		lblRecoveryRate.setStyleName("bold");
		lblFee.setStyleName("bold");
		lblTotal.setWidth("100px");
		lblRecoveryRate.setWidth("100px");
		MaterialColumn colLblTotal = new MaterialColumn();
		colLblTotal.add(lblTotal);
		hpnl.add(colLblTotal);
		MaterialColumn colTotalHours = new MaterialColumn();
		colTotalHours.add(totalHours);
		hpnl.add(colTotalHours);
		MaterialColumn colLTotaltimecost = new MaterialColumn();
		colLTotaltimecost.add( new Label(totaltimeCost+""));
		hpnl.add(colLTotaltimecost);
		
		MaterialRow hpnlFee = new MaterialRow();
		MaterialColumn colLblFee = new MaterialColumn();
		colLblFee.add(lblFee);
		hpnlFee.add(colLblFee);
		lblFee.setStyleName("bold");
		MaterialColumn colFee = new MaterialColumn();	
		colFee.add(fee);
		hpnlFee.add(colFee);
		
		lblFee.setWidth("100px");
		
		MaterialRow hpnlRecoveryRate = new MaterialRow();
		MaterialColumn colLblRecoveryRate = new MaterialColumn();
		colLblRecoveryRate.add(lblRecoveryRate);
		hpnlRecoveryRate.add(colLblRecoveryRate);
		lblRecoveryRate.setStyleName("bold");
		MaterialColumn colRecoveryRate = new MaterialColumn();
		colRecoveryRate.add(recoveryRate);
		hpnlRecoveryRate.add(colRecoveryRate);
		
		MaterialColumn mc = new MaterialColumn();
		mc.add(vpnlContainer);
		mc.add(hpnl);
		con.add(mc);
		con.add(hpnlFee);
		con.add(hpnlRecoveryRate);
		add(con);
	}
	public Label getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Label totalHours) {
		this.totalHours = totalHours;
	}
	
	public Label getFee() {
		return fee;
	}
	public void setFee(Label fee) {
		this.fee = fee;
	}
	public Label getRecoveryRate() {
		return recoveryRate;
	}
	public void setRecoveryRate(Label recoveryRate) {
		this.recoveryRate = recoveryRate;
	}
	public Label getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(Label timeCost) {
		this.timeCost = timeCost;
	}
	public float getTotaltimeCost() {
		return totaltimeCost;
	}
	public void setTotaltimeCost(float totaltimeCost) {
		this.totaltimeCost = totaltimeCost;
	}
	

}
