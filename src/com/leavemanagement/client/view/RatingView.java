package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;

import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.AttributeRating;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.JobUsersDTO;
import com.leavemanagement.shared.User;

public class RatingView extends MaterialColumn{
	
	MaterialColumn container = new MaterialColumn();
	private User loggedInUser;
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private int totalRatingValue = 0;
	
	public RatingView(User loggedInUser){
		this.loggedInUser = loggedInUser;
		Label lblAttributes = new Label("Ratings");
		container.add(lblAttributes);
		Image refresh = new Image("refresh.png");
		add(refresh);
		refresh.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchJobs();
			}});
		add(container);
		setWidth("100%");
//		Image addImage = new Image("add.png");
//		add(addImage);
		fetchJobs();
		
//		addImage.addClickHandler(new ClickHandler(){});
	}
	
	public void fetchJobs(){
		   rpcService.fetchUsersWithJobs( new AsyncCallback<ArrayList<JobUsersDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<JobUsersDTO> jobUsersDTO) {
				fetchUsersofSelectedJob(jobUsersDTO);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("fetch jobs list failed in Rating View");
			}
		});
	}
	
	private void fetchUsersofSelectedJob(ArrayList<JobUsersDTO> jobUsersDTO) {
		
		populateJobsList(jobUsersDTO);
	}
	
	private void populateJobsList(ArrayList<JobUsersDTO> jobUsersDTO) {
		container.clear();
		Tree tree = new Tree();
	
		container.add(tree);
		for(int i=0; i<jobUsersDTO.size(); i++ ){
			final Job job =jobUsersDTO.get(i).getJob();
//			final TreeItem jobTree = new TreeItem(job.getJobName());
			final TreeItem jobTree = new TreeItem();
			jobTree.setText(job.getJobName());
			//////////////////////////
			
			final MaterialListBox listUsers = new MaterialListBox();
			listUsers.addItem("0","Select User");
			listUsers.setWidth("200px");
			for(int j=0; j< jobUsersDTO.get(i).getUsers().size(); j++){
				listUsers.addItem(jobUsersDTO.get(i).getUsers().get(j).getUserId()+"",  jobUsersDTO.get(i).getUsers().get(j).getName());
			}
//			hpnl.add(jobTree.as);
			tree.addItem(jobTree);
			tree.addItem(listUsers);
			
			//////////////////////////
			
//			Image btnAddAttribute = new Image("add.png");
			MaterialColumn vpnlMain = new MaterialColumn();
			final Label lblTotal = new Label(".");
			final MaterialColumn vpnl = new MaterialColumn();
			jobTree.addItem(vpnlMain);
			MaterialColumn colTotal = new MaterialColumn();
			colTotal.add(lblTotal);
			vpnlMain.add(colTotal);
			vpnlMain.add(vpnl);
			
			if(loggedInUser.getRoleId().getRoleId() == 5){
//				vpnl.add(btnAddAttribute);
			}
			
			updateJobAttribute(job, listUsers, vpnl, lblTotal);
			
			listUsers.addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					fetchJobUserRating(job.getJobId(),listUsers, vpnl, lblTotal);
				}
			
		
//			
//			listUsers.addChangeHandler(new ChangeHandler(){
//
//				@Override
//				public void onChange(ChangeEvent event) {
//					fetchJobUserRating(job.getJobId(),listUsers, vpnl, lblTotal);
//					
//					
//				}

				private void fetchJobUserRating(final int jobId, final MaterialListBox listUsers,
						final MaterialColumn vpnl, final Label lblTotal) {
					int userId = Integer.parseInt(listUsers.getValue(listUsers.getSelectedIndex()));
					rpcService.fetchjobUserRating(userId, jobId, new AsyncCallback<ArrayList<AttributeRating>>() {
						
						@Override
						public void onSuccess(ArrayList<AttributeRating> attributRatings) {
							updateJobUserAttribute(listUsers, vpnl, attributRatings, jobId, lblTotal);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}});
			
			
		}

		
		
	}
	
	private void calculateTotalRatinglevels(MaterialColumn vpnl, Label lblTotalRatingLeve) {
		 totalRatingValue = 0;
//		for(int i=0; i< vpnl.getWidgetCount(); i++){
//			AddRatingWidget addAttributeWidget = (AddRatingWidget) vpnl.getWidget(i);
////			addAttributeWidget.getLstAttribute().getItemText(addAttributeWidget.getLstAttribute().getSelectedIndex());
//			String attributeValue = addAttributeWidget.getLstAttribute().getItemText(addAttributeWidget.getLstAttribute().getSelectedIndex());
//			attributeValue = attributeValue.substring(0, attributeValue.length()-1);
//			int attributeVal = Integer.parseInt(attributeValue);
//			totalRatingValue = totalRatingValue+attributeVal;
//			lblTotalRatingLeve.setText(totalRatingValue+"%");
//			lblTotalRatingLeve.setStyleName("blue");
//		}
		
	}
	
	private void updateJobAttribute(Job job,MaterialListBox listUsers,
			final MaterialColumn vpnl, final Label lblTotal) {
		for(int j=0; j< job.getJobAttributes().size(); j++){
			final AddRatingWidget addRatingWidget = new AddRatingWidget(listUsers);
			
//			addRatingWidget.getListScore().addChangeHandler(new ChangeHandler(){
//
//				@Override
//				public void onChange(ChangeEvent event) {
//					calculateTotalRatinglevels(vpnl, lblTotal);
//					if(totalRatingValue>100){
//						addRatingWidget.getBtnSave().setEnabled(false);
//					}else{
//						addRatingWidget.getBtnSave().setEnabled(true);
//					}
//				}});
			
			addRatingWidget.getListScore().addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					calculateTotalRatinglevels(vpnl, lblTotal);
					if(totalRatingValue>100){
						addRatingWidget.getBtnSave().setEnabled(false);
					}else{
						addRatingWidget.getBtnSave().setEnabled(true);
					}
				}
			});
			
			
			addRatingWidget.setAttributeId(job.getJobAttributes().get(j).getJobAttributeId());
			if(job.getJobAttributes().get(j).getRating()!=null){
//				addRatingWidget.disable();
			}
			if(loggedInUser.getRoleId().getRoleId() != 5){
				addRatingWidget.readOnlyView();
				
			}
			addRatingWidget.setJobId(job.getJobId());
			vpnl.add(addRatingWidget);
			addRatingWidget.getTxtAttribute().setText(job.getJobAttributes().get(j).getName());
			for(int k=0; k< addRatingWidget.getLstAttribute().getItemCount(); k++){
				if(addRatingWidget.getLstAttribute().getValue(k).equals(job.getJobAttributes().get(j).getLevel()+""))
				{
					addRatingWidget.getLstAttribute().setSelectedIndex(k);
					break;
				}
				
			}
			for(int k=0; k< addRatingWidget.getListScore().getItemCount(); k++){
				if(addRatingWidget.getListScore().getValue(k).equals(job.getJobAttributes().get(j).getScore()+""))
				{
					addRatingWidget.getListScore().setSelectedIndex(k);
					break;
				}
				
			}
			
			addRatingWidget.getLblRating().setText(job.getJobAttributes().get(j).getRating());
			
			final DataSetter data = new DataSetter();
			data.setId(j);
			
			addRatingWidget.refreshFields();
			
		}
	}
	

	private void updateJobUserAttribute(MaterialListBox listUsers,
			final MaterialColumn vpnl,ArrayList<AttributeRating> attributRatings, int jobId, final Label lblTotal) {
		for(int m=0; m<vpnl.getWidgetCount(); m++){
			
			AddRatingWidget addRatingWidget = (AddRatingWidget) vpnl.getWidget(m);
			addRatingWidget.refreshFields();
			}
		for(int j=0; j< attributRatings.size(); j++){
			AddRatingWidget addRatingWidget=null;
			for(int m=0; m<vpnl.getWidgetCount(); m++){
				
				addRatingWidget = (AddRatingWidget) vpnl.getWidget(m);
//				addRatingWidget.getListScore().addChangeHandler(new ChangeHandler(){
//
//					@Override
//					public void onChange(ChangeEvent event) {
//						calculateTotalRatinglevels(vpnl, lblTotal);
//					}});
				
				addRatingWidget.getListScore().addValueChangeHandler(new ValueChangeHandler<String>() {
					
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						calculateTotalRatinglevels(vpnl, lblTotal);
					}
				});
//				addRatingWidget.refreshFields();
				if(addRatingWidget.getAttributeId() == attributRatings.get(j).getAttributeId()){
//					addRatingWidget = addRatingWidgetOld;
					
//				}else{
//					addRatingWidget  = new AddRatingWidget(listUsers);
//					vpnl.add(addRatingWidget);
//				}
				
					
//				addRatingWidget.getBtnSave().setEnabled(false);
			addRatingWidget.setAttributeId(attributRatings.get(j).getAttributeId());
//			addRatingWidget.setAttributeIdRatingId(attributRatings.get(j).getAttributeIdRatingId());
			if(attributRatings.get(j).getRating()!=null){
				addRatingWidget.disable();
			}
			if(loggedInUser.getRoleId().getRoleId() != 5){
				addRatingWidget.readOnlyView();
				
			}
			addRatingWidget.setJobId(jobId);
			
//			addRatingWidget.getTxtAttribute().setText(attributRatings.get(j).getName());
//			for(int k=0; k< addRatingWidget.getLstAttribute().getItemCount(); k++){
//				if(addRatingWidget.getLstAttribute().getValue(k).equals(attributRatings.get(j).getScore()+""))
//				{
//					addRatingWidget.getLstAttribute().setSelectedIndex(k);
//					break;
//				}
//				
//			}
			for(int k=0; k< addRatingWidget.getListScore().getItemCount(); k++){
				if(addRatingWidget.getListScore().getValue(k).equals(attributRatings.get(j).getScore()+""))
				{
					addRatingWidget.getListScore().setSelectedIndex(k);
					break;
				}
				
			}
			
			addRatingWidget.getLblRating().setText(attributRatings.get(j).getRating());
			
			final DataSetter data = new DataSetter();
			data.setId(j);
			}}}
	}
	
	private void deleteJobAttribute(int jobAttributeId) {
	rpcService.deleteJobAttribute(jobAttributeId, new AsyncCallback<String>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("fail delete job Attribute");
		}

		@Override
		public void onSuccess(String result) {
			Window.alert("job attribute deleted");
		}
	});
	}
	
	private void saveJobAttribute(int jobId, String txtAttribute,
			String value) {
		
		JobAttributes jobAttributes = new JobAttributes();
		jobAttributes.setJobId(jobId);
		jobAttributes.setName(txtAttribute);
		jobAttributes.setLevel(Integer.parseInt(value));
		
		rpcService.saveJobAttribute(jobAttributes, new  AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail save job attributes");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("job Attribute saved");
			}});
		
	}


}
