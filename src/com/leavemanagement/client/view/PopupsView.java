package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Image;
import gwt.material.design.client.ui.MaterialColumn;
import com.google.gwt.user.client.ui.Widget;

public class PopupsView {
	
	DecoratedPopupPanel popup ;
	MaterialColumn vpnlMain;
	private MaterialRow hpnlSPace;
	
	public MaterialRow getHpnlSPace() {
		return hpnlSPace;
	}

	public void setHpnlSPace(MaterialRow hpnlSPace) {
		this.hpnlSPace = hpnlSPace;
	}

	Image close = new Image("close.jpg");
	
	public PopupsView(Widget widget) {
		MaterialRow hpnlClose = new MaterialRow();
		hpnlSPace = new MaterialRow();
		hpnlSPace.setWidth("800px");
		hpnlClose.add(hpnlSPace);
		hpnlClose.add(close);
		close.setStyleName("pointerStyle");
		popup = new DecoratedPopupPanel();
		vpnlMain = new MaterialColumn();
		vpnlMain.add(hpnlClose);
		vpnlMain.add(widget);
		vpnlMain.setSize("800px","425px");
		//vpnlMain.
		popup.setWidget(vpnlMain);
		popup.setStyleName("whitebg");
		popup.setGlassEnabled(true);
		popup.center();
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				popup.removeFromParent();
			}});
	}

	public DecoratedPopupPanel getPopup() {
		return popup;
	}


	public void setPopup(DecoratedPopupPanel popup) {
		this.popup = popup;
	}


	public Image getClose() {
		return close;
	}


	public void setClose(Image close) {
		this.close = close;
	}

	public MaterialColumn getVpnlMain() {
		return vpnlMain;
	}

	public void setVpnlMain(MaterialColumn vpnlMain) {
		this.vpnlMain = vpnlMain;
	}

	
	


}
