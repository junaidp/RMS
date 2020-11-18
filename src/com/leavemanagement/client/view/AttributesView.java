package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.User;

public class AttributesView extends MaterialColumn{
	
	MaterialColumn container = new MaterialColumn();
	private User loggedInUser;
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private int totalAttributesValue=0;
//	private Label lblTotalAttributesLeve ;
	
	public AttributesView(User loggedInUser){
		this.loggedInUser = loggedInUser;
		Label lblAttributes = new Label("Attribues for the Job");
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
		   rpcService.fetchJobs(loggedInUser, new AsyncCallback<ArrayList<Job>>() {
			
			@Override
			public void onSuccess(ArrayList<Job> jobs) {
				populateJobsList(jobs);
			}
			
			

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("fetch jobs list failed in attributes view");
			}
		});
	}
	
	private void populateJobsList(ArrayList<Job> jobs) {
		container.clear();
	Tree tree = new Tree();
		container.add(tree);
		for(int i=0; i<jobs.size(); i++ ){
			final Job job =jobs.get(i);
//			final TreeItem jobTree = new TreeItem(job.getJobName());
			final TreeItem jobTree = new TreeItem();
			
			jobTree.setText(job.getJobName());
			tree.addItem(jobTree);
			Image btnAddAttribute = new Image("add.png");
			final Label lblTotalAttributesLeve = new Label("Total:");
			final MaterialColumn vpnl = new MaterialColumn();
			jobTree.addItem(vpnl);
			
			if(loggedInUser.getRoleId().getRoleId() == 5){
				MaterialRow hpnl = new MaterialRow();
				MaterialColumn colBtnAddAttribute = new MaterialColumn();
				MaterialColumn colTotalAttributesLeve = new MaterialColumn();
				colBtnAddAttribute.add(btnAddAttribute);
				hpnl.add(colBtnAddAttribute);
				//hpnl.add(btnAddAttribute);
				colTotalAttributesLeve.add(lblTotalAttributesLeve);
				hpnl.add(colTotalAttributesLeve);
				vpnl.add(hpnl);
			}
			
			
			for(int j=0; j< job.getJobAttributes().size(); j++){
				final AddAttributeWidget addAttributeWidget = new AddAttributeWidget();
				addAttributeWidget.getBtnSave().setEnabled(false);
				addAttributeWidget.getLstAttribute().setEnabled(false);
				if(loggedInUser.getRoleId().getRoleId() != 5){
					addAttributeWidget.readOnlyView();
					
				}
				
				addAttributeWidget.setJobId(job.getJobId());
				vpnl.add(addAttributeWidget);
				addAttributeWidget.getTxtAttribute().setText(job.getJobAttributes().get(j).getName());
				for(int k=0; k< addAttributeWidget.getLstAttribute().getItemCount(); k++){
					if(addAttributeWidget.getLstAttribute().getValue(k).equals(job.getJobAttributes().get(j).getLevel()+""))
					{
						addAttributeWidget.getLstAttribute().setSelectedIndex(k);
						break;
					}
					
				}
				
				final DataSetter data = new DataSetter();
				data.setId(j);
				addAttributeWidget.getBtnDelete().addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {
						boolean confirm = Window.confirm("Attribute will be removed !");
						if(confirm){
						deleteJobAttribute(job.getJobAttributes().get(data.getId()).getJobAttributeId());
						vpnl.remove(addAttributeWidget);
						}
					}

					});
			}
			
			
			
			btnAddAttribute.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					
					final AddAttributeWidget addAttributeNewWidget = new AddAttributeWidget();
					addAttributeNewWidget.setJobId(job.getJobId());
					
					vpnl.add(addAttributeNewWidget);
					calculateTotalAttributelevels(vpnl, lblTotalAttributesLeve);
					addAttributeNewWidget.getBtnSave().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							if(totalAttributesValue>100){
								Window.alert("Total Attributes Level should not exceed 100%");
							}else{
							saveJobAttribute(job.getJobId(), addAttributeNewWidget.getTxtAttribute().getText(), addAttributeNewWidget.getLstAttribute().getValue(addAttributeNewWidget.getLstAttribute().getSelectedIndex()) );
							}
						}

						});
					
//					addAttributeNewWidget.getLstAttribute().addChangeHandler(new ChangeHandler() {
//						
//						@Override
//						public void onChange(ChangeEvent event) {
//							calculateTotalAttributelevels(vpnl, lblTotalAttributesLeve);
//						}
//
//						
//					});
					
					addAttributeNewWidget.getLstAttribute().addValueChangeHandler(new ValueChangeHandler<String>() {
						
						@Override
						public void onValueChange(ValueChangeEvent<String> event) {
							calculateTotalAttributelevels(vpnl, lblTotalAttributesLeve);
							
						}
					});
					
					addAttributeNewWidget.getBtnDelete().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							vpnl.remove(addAttributeNewWidget);
							calculateTotalAttributelevels(vpnl, lblTotalAttributesLeve);
						}});
				}});
		}
		
		
	}
	
	private void calculateTotalAttributelevels(MaterialColumn vpnl, Label lblTotalAttributesLeve) {
		totalAttributesValue = 0;
		for(int i=1; i< vpnl.getWidgetCount(); i++){
			AddAttributeWidget addAttributeWidget = (AddAttributeWidget) vpnl.getWidget(i);
//			addAttributeWidget.getLstAttribute().getItemText(addAttributeWidget.getLstAttribute().getSelectedIndex());
			String attributeValue = addAttributeWidget.getLstAttribute().getItemText(addAttributeWidget.getLstAttribute().getSelectedIndex());
			attributeValue = attributeValue.substring(0, attributeValue.length()-1);
			int attributeVal = Integer.parseInt(attributeValue);
			totalAttributesValue = totalAttributesValue+attributeVal;
			lblTotalAttributesLeve.setText(totalAttributesValue+"%");
		}
		
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
