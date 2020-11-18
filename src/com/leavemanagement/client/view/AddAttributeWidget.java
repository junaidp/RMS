package com.leavemanagement.client.view;

import com.google.gwt.dom.client.Style.Unit;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class AddAttributeWidget extends MaterialRow {

	MaterialTextBox txtAttribute = new MaterialTextBox();
	MaterialListBox lstAttribute = new MaterialListBox();
	MaterialButton btnSave = new MaterialButton("Save");
	MaterialButton btnDelete = new MaterialButton("Delete");
	private int jobId=0;

	public AddAttributeWidget() {
		lstAttribute.addItem( "0","10%");
		lstAttribute.addItem( "1","20%");
		lstAttribute.addItem( "2","30%");
		lstAttribute.addItem( "3","40%");
		lstAttribute.addItem( "4","50%");

		btnSave.setWidth("100px");
		btnDelete.setWidth("100px");
		
		MaterialColumn coltxtAttribute = new MaterialColumn();
		MaterialColumn collstAttribute = new MaterialColumn();
		MaterialColumn colbtnSave = new MaterialColumn();
		MaterialColumn colbtnDelete = new MaterialColumn();
		
		coltxtAttribute.add(txtAttribute);
		collstAttribute.add(lstAttribute);
		colbtnSave.add(btnSave);
		colbtnDelete.add(btnDelete);
		
		btnSave.getElement().getStyle().setMarginTop(25, Unit.PX);
		btnDelete.getElement().getStyle().setMarginTop(25, Unit.PX);
		
		add(coltxtAttribute);
		add(collstAttribute);
		add(colbtnSave);
		add(colbtnDelete);
		
		//txtAttribute.setHeight("20px");
		//lstAttribute.setHeight("30px");
		


	}

	public MaterialTextBox getTxtAttribute() {
		return txtAttribute;
	}

	public void setTxtAttribute(MaterialTextBox txtAttribute) {
		this.txtAttribute = txtAttribute;
	}

	public MaterialListBox getLstAttribute() {
		return lstAttribute;
	}

	public void setLstAttribute(MaterialListBox lstAttribute) {
		this.lstAttribute = lstAttribute;
	}

	public MaterialButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(MaterialButton btnSave) {
		this.btnSave = btnSave;
	}

	public MaterialButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(MaterialButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void readOnlyView(){
		btnDelete.setVisible(false);
		btnSave.setVisible(false);
		txtAttribute.setEnabled(false);
		lstAttribute.setEnabled(false);
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
