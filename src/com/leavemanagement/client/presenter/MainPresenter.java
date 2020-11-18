package com.leavemanagement.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.poi.hssf.util.HSSFColor.BLUE_GREY;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;

import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;

import com.google.gwt.user.client.ui.HasWidgets;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.event.AdminEvent;
import com.leavemanagement.client.event.ChangePasswordEvent;
import com.leavemanagement.client.event.LeaveHistoryEvent;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeaveTypes;
import com.leavemanagement.shared.LeavesDTO;
import com.leavemanagement.shared.User;

public class MainPresenter implements Presenter 

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	 User loggedInUser;
	 ArrayList<LeavesDTO> leavesDTO;
	 private int noOfDays;
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		 MaterialColumn getVpnlAvailableLeaves();
		 MaterialListBox getListLeaves() ;
		 DateBox getFrom();
		 DateBox getTo();
		 MaterialLabel getLblNoOfDays();
		 MaterialRichEditor getReason();
		 MaterialButton getBtnSubmit();
		 Anchor getLogOff() ;
		 MaterialLabel getLoggedInUserName();
		 Anchor getLeaveHistory();
		 Anchor getChangePassword();
		 Anchor getAdminView();
	}  

	public MainPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		
		display.getChangePassword().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ChangePasswordEvent(loggedInUser));
			}});
		
		display.getLeaveHistory().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LeaveHistoryEvent(loggedInUser));
			}});
		
		display.getAdminView().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AdminEvent(loggedInUser));
			}});
		
		display.getLoggedInUserName().setText(loggedInUser.getName());
		display.getLogOff().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}
		});
		
		display.getTo().addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				if(display.getTo().getValue()!=null && display.getFrom().getValue()!=null && display.getTo().getValue().getTime() >= display.getFrom().getValue().getTime()){
					fetchDatesDifference();
					}else{
						Window.alert("Start date should be less than end date");
					}
			}

			
		});
		
			display.getFrom().addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				if(display.getTo().getValue()!=null && display.getFrom().getValue()!=null && display.getTo().getValue().getTime() > display.getFrom().getValue().getTime()){
				fetchDatesDifference();
				}
			}

			
		});
		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(leavesAvailable()){
				display.getBtnSubmit().setEnabled(false);
				saveLeaveRequest();
				}else{
					Window.alert("You do not have required number of leaves available for this leave type");
				}
			}

			

		});
		
		fetchLeaveTypes();
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchLeavesRemaining(loggedInUser, new AsyncCallback<ArrayList<LeavesDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchLeavesRemaining ");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(ArrayList<LeavesDTO> result) {
				leavesDTO = result;
				MaterialCard mc = new MaterialCard();
				MaterialCardTitle lblHeading = new MaterialCardTitle();
				lblHeading.setText("Available Leaves");
				MaterialCardContent con = new MaterialCardContent();
				mc.add(lblHeading);
	
				
				 
				for(int i=0; i< result.size(); i++){
					
				MaterialRow hpnl = new MaterialRow();
				
				Label lblName = new Label();
				lblName.setWidth("200px");
				lblName.setText(result.get(i).getLeaveType().getLeaveTypeName());
				
				MaterialColumn colLblName = new MaterialColumn();
				colLblName.add(lblName);
				
				hpnl.add(colLblName);
			     Label a = new Label("");
			     MaterialColumn colA = new MaterialColumn();
			     colA.add(a);
				hpnl.add(colA);
//				Label lblAvailed = new Label(result.get(i).getLeavesAvailed()+"");
				Label lblRemaining = new Label(result.get(i).getLeavesAvaible()+"");
				if(result.get(i).getLeaveType().getLeaveTypeId()==5){
					lblRemaining.setText("");
				}
				MaterialColumn colLblRemaining = new MaterialColumn();
				colLblRemaining.add(lblRemaining);
				hpnl.add(colLblRemaining);
				con.add(hpnl);
				mc.add(con);
				display.getVpnlAvailableLeaves().add(mc);
				
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});
		
	}
	
	private boolean leavesAvailable() {
		boolean leavesAvailable = false;
		if(leavesDTO!=null){
			int leaveTypeId = Integer.parseInt(display.getListLeaves().getValue(display.getListLeaves().getSelectedIndex()));
			for(int i=0; i< leavesDTO.size(); i++){
				if(leavesDTO.get(i).getLeaveType().getLeaveTypeId() == leaveTypeId && leavesDTO.get(i).getLeavesAvaible()>= noOfDays){
					leavesAvailable = true;
					break;
				}
			}
		}
		return leavesAvailable;
	}

	private void fetchLeaveTypes() {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchLeaveTypes(new AsyncCallback<ArrayList<LeaveTypes>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchLeaveTypes ");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(ArrayList<LeaveTypes> result) {
				for(int i=0; i< result.size(); i++){
				display.getListLeaves().addItem( result.get(i).getLeaveTypeId()+"",result.get(i).getLeaveTypeName());
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});
	}
	
	private void saveLeaveRequest() {
		
		
		LeaveRecord leaveRecord = new LeaveRecord();
		LeaveTypes leaveType = new LeaveTypes();
		leaveType.setLeaveTypeId(Integer.parseInt(display.getListLeaves().getValue(display.getListLeaves().getSelectedIndex())));
		leaveRecord.setLeaveType(leaveType);
		leaveRecord.setEndDate(display.getTo().getValue());
		leaveRecord.setReason(display.getReason().getText());
		leaveRecord.setStartDate(display.getFrom().getValue());
		leaveRecord.setStatus("pending");
		
		saveLeaveRequest(leaveRecord);
		
	}

	private void saveLeaveRequest(LeaveRecord leaveRecord) {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.saveLeaveRequest(leaveRecord, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : saveLeaveRequest");
				display.getBtnSubmit().setEnabled(true);
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				History.fireCurrentHistoryState();
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});
	}
	
	private void fetchDatesDifference() {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchDatesDifference(display.getFrom().getValue(), display.getTo().getValue(), new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : fetchDatesDifference");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(String result) {
				noOfDays = Integer.parseInt(result);
				if(!leavesAvailable()){
					Window.alert("You do not have required number of leaves available for this leave type");
					
				}
				display.getLblNoOfDays().setText("No Of Days : " + result);
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});;
		
	}
}


