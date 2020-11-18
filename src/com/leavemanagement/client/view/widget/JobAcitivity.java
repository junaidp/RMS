package com.leavemanagement.client.view.widget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLabel;

public class JobAcitivity extends HorizontalPanel {
	
	MaterialLabel lbl = new MaterialLabel();
	private MaterialCheckBox chk = new MaterialCheckBox();
	private int id;
	//Button btnAdd = new Button("+");
	//Button btnRemove = new Button("-");
	
	public JobAcitivity(String activity, int activityId){
		id = activityId;
		lbl.setText(activity);
		
		add(lbl);
		add(chk);
		
		
	}

	public MaterialLabel getLbl() {
		return lbl;
	}

	public void setLbl(MaterialLabel lbl) {
		this.lbl = lbl;
	}

	public MaterialCheckBox getChk() {
		return chk;
	}

	public void setChk(MaterialCheckBox chk) {
		this.chk = chk;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
