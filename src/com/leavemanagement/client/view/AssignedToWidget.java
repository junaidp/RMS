package com.leavemanagement.client.view;

import java.util.ArrayList;

import gwt.material.design.client.ui.MaterialListBox;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialTextBox;
import com.leavemanagement.shared.User;

public class AssignedToWidget extends FlexTable {
	Image btnRemove = new Image("deleteIcon.png");
	 MaterialListBox listAssign = new MaterialListBox();
	 MaterialTextBox txtDays = new MaterialTextBox();
	 private int jobEmployeeId= 0;
	public AssignedToWidget(ArrayList<User> employeesList){
		Label lbl = new Label("Assign to ");
		btnRemove.setTitle("Remove Employee");
		btnRemove.addStyleName("hover");
		txtDays.setWidth("20px");
		setWidget(0, 0, lbl);
		setWidget(0, 1, listAssign);
		//setWidget(1, 0, new Label("Hours: "));
		//setWidget(1, 1, txtDays);
		setWidget(0, 3, btnRemove);
		listAssign.clear();
		for(int i=0; i< employeesList.size(); i++){
			listAssign.addItem(employeesList.get(i).getUserId()+"", employeesList.get(i).getName());
			
		}
		
	}
	
	public MaterialListBox getListAssign() {
		return listAssign;
	}
	public void setListAssign(MaterialListBox listAssign) {
		this.listAssign = listAssign;
	}
	public MaterialTextBox getTxtDays() {
		return txtDays;
	}
	public void setTxtDays(MaterialTextBox txtDays) {
		this.txtDays = txtDays;
	}
	public int getJobEmployeeId() {
		return jobEmployeeId;
	}
	public void setJobEmployeeId(int jobEmployeeId) {
		this.jobEmployeeId = jobEmployeeId;
	}

	public Image getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(Image btnRemove) {
		this.btnRemove = btnRemove;
	}

}
