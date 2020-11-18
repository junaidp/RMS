package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialColumn;

public class CostWidget extends MaterialColumn {
	
	private Label cost = new Label("");
	private Label recoveryRate = new Label("");
	private Image imgRefresh = new Image("refresh.png");
	public CostWidget(){
		add(imgRefresh);
//		Label lbl = new Label("Time Cost");
//		add(lbl);
//		lbl.setStyleName("blue");
		add(cost);
//		Label lblRecoevery = new Label("Recovery rate");
//		add(lblRecoevery);
		add(recoveryRate);
	}

	public Label getCost() {
		return cost;
	}

	public void setCost(Label cost) {
		this.cost = cost;
	}

	public Image getImgRefresh() {
		return imgRefresh;
	}

	public void setImgRefresh(Image imgRefresh) {
		this.imgRefresh = imgRefresh;
	}

	public Label getRecoveryRate() {
		return recoveryRate;
	}

	public void setRecoveryRate(Label recoveryRate) {
		this.recoveryRate = recoveryRate;
	}

}
