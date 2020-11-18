package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.AttributeRating;

public class AddRatingWidget extends MaterialRow {

	Label txtAttribute = new Label();
	MaterialListBox lstAttribute = new MaterialListBox();
	MaterialButton btnSave = new MaterialButton("Save");
	MaterialButton btnEdit = new MaterialButton("Edit");
	MaterialListBox listScore = new MaterialListBox();
	Label lblRating = new Label();
	private int attributeIdRatingId=0;
	
	private int jobId=0;
	private int attributeId = 0;
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);

	public AddRatingWidget(final MaterialListBox listUsers) {
		lstAttribute.addItem("0", "10%");
		lstAttribute.addItem("1", "20%");
		lstAttribute.addItem("2", "30%");
		lstAttribute.addItem("3", "40%");
		lstAttribute.addItem("4", "50%");
		lstAttribute.setEnabled(false);
		listScore.addItem("0");
		listScore.addItem("1");
		listScore.addItem("2");
		listScore.addItem("3");
		
		btnEdit.setVisible(false);
		btnSave.setWidth("100px");
		btnEdit.setWidth("100px");
		
		MaterialColumn coltextAttribute = new MaterialColumn();
		MaterialColumn collstAttribute = new MaterialColumn();
		MaterialColumn collistScore = new MaterialColumn();
		MaterialColumn collblRating = new MaterialColumn();
		MaterialColumn colBtnSave = new MaterialColumn();
		MaterialColumn colBtnEdit = new MaterialColumn();
		
		coltextAttribute.add(txtAttribute);
		collstAttribute.add(lstAttribute);
		collistScore.add(listScore);
		collblRating.add(lblRating);
		colBtnSave.add(btnSave);
		colBtnEdit.add(btnEdit);
		
		add(coltextAttribute);
		add(collstAttribute);
		add(collistScore);
		add(collblRating);
		txtAttribute.setWidth("300px");
		lblRating.setWidth("100px");
		add(colBtnSave);
		add(colBtnEdit);
		lblRating.setStyleName("blue");
		
		btnSave.getElement().getStyle().setMarginTop(25, Unit.PX);
		btnEdit.getElement().getStyle().setMarginTop(25, Unit.PX);
		btnSave.setHeight("30px");
		

		calculate();
		btnEdit.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				enable();
			}});
		
		btnSave.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				int score = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
				String rating = lblRating.getText();
				String name = txtAttribute.getText();
				int level = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
				int userId =0;
				try{
				userId = Integer.parseInt(listUsers.getValue(listUsers.getSelectedIndex()));
				}catch(Exception ex){
					Window.alert("please select user");
				}
				AttributeRating attributeRating = new AttributeRating();
				attributeRating.setAttributeId(attributeId);
				attributeRating.setRating(rating);
				attributeRating.setScore(score);
//				attributeRating.setLevel(level);
				attributeRating.setUserId(userId);
				attributeRating.setJobId(jobId);
//				attributeRating.setAttributeIdRatingId(attributeIdRatingId);
				rpcService.saveRating(attributeRating, new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail save rating");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}});
				
			}});
	}

	

	private void calculate() {
//		listScore.addChangeHandler(new ChangeHandler(){
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				calcultateAndupdate();
//			}
//
//			});
		
		listScore.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calcultateAndupdate();
			}
		});
		
//		lstAttribute.addChangeHandler(new ChangeHandler(){
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				calcultateAndupdate();
//			}
//
//			});
		
		listScore.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calcultateAndupdate();
				
			}
		});
	}
	
	private void calcultateAndupdate() {
		float score = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
		String attributeValue = lstAttribute.getItemText(lstAttribute.getSelectedIndex());
		attributeValue = attributeValue.substring(0, attributeValue.length()-1);
		float attrValue = Float.parseFloat(attributeValue) /100;
		float rating = score* attrValue;
		String ratings =rating+"";
		if(ratings.length()>4){
		ratings = ratings.substring(0, 4);
		}
		lblRating.setText(ratings);
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

	
	public void readOnlyView(){
		btnSave.setVisible(false);
		listScore.setEnabled(false);
		lstAttribute.setEnabled(false);
		btnEdit.setVisible(false);
		listScore.setEnabled(false);
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public Label getLblRating() {
		return lblRating;
	}



	public void setLblRating(Label lblRating) {
		this.lblRating = lblRating;
	}



	public Label getTxtAttribute() {
		return txtAttribute;
	}



	public void setTxtAttribute(Label txtAttribute) {
		this.txtAttribute = txtAttribute;
	}



	public MaterialListBox getListScore() {
		return listScore;
	}



	public void setListScore(MaterialListBox listScore) {
		this.listScore = listScore;
	}



	public int getAttributeId() {
		return attributeId;
	}



	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}



	public void disable() {
		btnSave.setEnabled(false);
		listScore.setEnabled(false);
		lstAttribute.setEnabled(false);
		btnEdit.setVisible(true);
		
	}
	public void enable() {
		btnSave.setEnabled(true);
		listScore.setEnabled(true);
//		lstAttribute.setEnabled(true);
		
		
	}
	public void refreshFields(){
		enable();
		listScore.setSelectedIndex(0);
		lblRating.setText("");
		btnEdit.setVisible(false);
//		lstAttribute.setSelectedIndex(0);
	}



	public int getAttributeIdRatingId() {
		return attributeIdRatingId;
	}



	public void setAttributeIdRatingId(int attributeIdRatingId) {
		this.attributeIdRatingId = attributeIdRatingId;
	}



	
}
