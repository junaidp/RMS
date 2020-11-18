package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Image;
import gwt.material.design.client.ui.MaterialColumn;

public class LoadingPopup {
	private DecoratedPopupPanel popupLoading;

	
	public  void display() {
		popupLoading = new DecoratedPopupPanel ();
		popupLoading.setSize("100%", "100%");
		MaterialColumn vpnlLoad = new MaterialColumn();
		vpnlLoad.setSize("20px", "20px");
		vpnlLoad.add(new Image("loading.gif"));
//		popupLoading.setWidget(vpnlLoad);
		popupLoading.setWidget(new Image("loading.gif"));
		popupLoading.setStyleName("whiteBackground");
//		popupLoading.setGlassEnabled(true);
		popupLoading.center();
	}


	public void remove(){
		popupLoading.removeFromParent();
	}
	

}

