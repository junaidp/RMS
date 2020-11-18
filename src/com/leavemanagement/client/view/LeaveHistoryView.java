package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.client.presenter.LeaveHistoryPresenter.Display;


public class LeaveHistoryView extends MaterialColumn implements IsWidget,Display {
	
	private MaterialColumn vpnlContainer = new MaterialColumn();
	//private Anchor logOff = new Anchor("Log off");
	private MaterialButton btnBack = new MaterialButton("Back");
	
	public LeaveHistoryView(){
		Label lblHeading = new Label("Leave History");
		
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
//		add(lblHeading);
		
		MaterialRow hpnlWelcome = new MaterialRow();
		hpnlWelcome.setWidth("100%");
		MaterialRow hpnlSpace = new MaterialRow();
		hpnlSpace.setWidth("80%");
		//hpnlWelcome.add(hpnlSpace);
		//hpnlWelcome.add(logOff);
		MaterialRow hpnlHeader = new MaterialRow();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
//		Label lblHeader = new Label("Leave Management System");
//		lblHeader.setStyleName("headerSignin");
//		hpnlHeader.setStyleName("headerSignin");
		//hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		
		add(hpnlHeader);
		add(vpnlContainer);
		
//		logOff.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//				History.newItem("login");
//			}});
	}


	public MaterialColumn getVpnlContainer() {
		return vpnlContainer;
	}


	public void setVpnlContainer(MaterialColumn vpnlContainer) {
		this.vpnlContainer = vpnlContainer;
	}


	public MaterialButton getBtnBack() {
		return btnBack;
	}


	public void setBtnBack(MaterialButton btnBack) {
		this.btnBack = btnBack;
	}


	
	
}
