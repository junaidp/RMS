package com.leavemanagement.client.view;

/*
 * public class TimeSheetView extends MaterialColumn{
 * 
 * private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
 * private User loggedInUser =null; private MaterialListBox listMonth = new
 * MaterialListBox();
 * 
 * FlexTable flex = new FlexTable(); private int selectedMonth = 0; private
 * MaterialButton btnSave = new MaterialButton("Save"); private MaterialButton
 * btnSubmit = new MaterialButton("Submit"); private MaterialListBox
 * listActivities = new MaterialListBox(); private MaterialCheckBox
 * chkChargeable = new MaterialCheckBox(Allocations.CHARGEABLE.getName());
 * private boolean chargeable = false; ScrollPanel panelScroll = new
 * ScrollPanel();
 * 
 * public TimeSheetView(User loggedInUser){ this.loggedInUser = loggedInUser;
 * 
 * btnSave.setEnabled(false); panelScroll.setWidth("1400px");
 * panelScroll.setHeight("400px"); MaterialRow hpnl = new MaterialRow(); Label
 * lblJob = new Label("Job Name"); // hpnl.add(lblJob);
 * 
 * panelScroll.add(flex); hpnl.add(panelScroll); MaterialRow mRow = new
 * MaterialRow(); MaterialColumn mcMonth = new MaterialColumn(); MaterialColumn
 * mcChargeable = new MaterialColumn(); mcMonth.add(listMonth);
 * mcChargeable.add(chkChargeable); mRow.add(mcMonth); mRow.add(mcChargeable);
 * 
 * listMonth.getElement().getStyle().setHeight(60, Unit.PX);
 * 
 * MaterialRow hpnlButtons = new MaterialRow(); MaterialColumn colBtnSave = new
 * MaterialColumn(); colBtnSave.add(btnSave); hpnlButtons.add(colBtnSave);
 * MaterialColumn colBtnSubmit = new MaterialColumn();
 * //colBtnSubmit.add(btnSubmit); hpnlButtons.add(colBtnSubmit);
 * add(hpnlButtons); add(mRow); add(hpnl);
 * 
 * listMonth.addItem("0", "Select Month"); listMonth.addItem("1", "Jan");
 * listMonth.addItem("2", "Feb"); // listMonth.addItem("Mar", "3");
 * listMonth.addItem("3","Mar"); listMonth.addItem("4","Apr");
 * listMonth.addItem("5", "May"); listMonth.addItem("6", "Jun");
 * listMonth.addItem("7", "Jul"); listMonth.addItem("8","Aug");
 * listMonth.addItem("9", "Sep"); listMonth.addItem("10","Oct");
 * listMonth.addItem("11", "Nov"); listMonth.addItem("12", "Dec");
 * 
 * listActivities.addItem("1","Planning");
 * listActivities.addItem("2","Execution");
 * listActivities.addItem("3","Reporting");
 * listActivities.addItem("4","Followup");
 * 
 * 
 * btnSubmit.addClickHandler(new ClickHandler() {
 * 
 * @Override public void onClick(ClickEvent event) { submitTimeSheet(); } });
 * 
 * // btnSave.addClickHandler(new ClickHandler(){ // // @Override // public void
 * onClick(ClickEvent event) { // saveTimeSheet(); // } // // });
 * 
 * // listMonth.addChangeHandler(new ChangeHandler(){ // // @Override // public
 * void onChange(ChangeEvent event) { // selectedMonth =
 * Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex())); //
 * fetchJobs(); // }});
 * 
 * listMonth.addValueChangeHandler(new ValueChangeHandler<String>() {
 * 
 * @Override public void onValueChange(ValueChangeEvent<String> event) {
 * selectedMonth =
 * Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
 * fetchJobs(); } });
 * 
 * chkChargeable.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
 * 
 * @Override public void onValueChange(ValueChangeEvent<Boolean> event) {
 * chargeable = event.getValue(); if(selectedMonth > 0){ fetchJobs(); } } });
 * 
 * }
 * 
 * public void fetchJobs(){ flex.clear(); flex.setWidget(0, 0, new
 * Label("Job")); //flex.setWidget(0, 1, new Label("Chargable")); for(int k=0;
 * k<31;k++){ Label heading = new Label(k+1+"");
 * heading.addStyleName("blueBold"); //flex.setWidget(0, k+2, heading);
 * flex.setWidget(0, k+1, heading);
 * flex.getFlexCellFormatter().setHorizontalAlignment(0, k+1,
 * HasHorizontalAlignment.ALIGN_CENTER);
 * 
 * } rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable, new
 * AsyncCallback<ArrayList<Job>>() {
 * 
 * @Override public void onSuccess(final ArrayList<Job> jobs) {
 * 
 * int iIndex = 0;
 * 
 * for(int i=0; i< jobs.size(); i++){ Job job = jobs.get(i); Label lblName = new
 * Label(job.getJobName()); lblName.addStyleName("blueBold"); //MaterialCheckBox
 * chkChargable = new MaterialCheckBox(); lblName.setWordWrap(false);
 * 
 * if(i ==0 ){ flex.setWidget(i+1, 0, lblName); //flex.setWidget(i+1, 1,
 * chkChargable); } else{ iIndex =
 * (i+1)+(i*jobs.get(i).getActivityLists().size()); flex.setWidget(iIndex, 0,
 * lblName); // flex.setWidget(i+5, 1, chkChargable); }
 * 
 * for(int k =0; k< job.getActivityLists().size(); k++){ if(i == 0){ // for (int
 * j = 0; j < job.getActivityLists().size(); j++) { flex.setWidget(k+2, 0, new
 * Label(job.getActivityLists().get(k).getActivityName())); // } //
 * flex.setWidget(k+2, 0, new Label(listActivities.getItemText(k)));
 * 
 * } else{ // flex.setWidget(k+(iIndex)+1, 0, new
 * Label(listActivities.getItemText(k))); flex.setWidget(k+(iIndex)+1, 0, new
 * Label(job.getActivityLists().get(k).getActivityName())); } for(int j=0;
 * j<31;j++){ MaterialTextBox text = new MaterialTextBox();
 * text.setWidth("30px"); if(i == 0){ flex.setWidget(k+2, j+1, text); } else{
 * flex.setWidget(k+(iIndex)+1, j+1, text); } for(int m=0; m<
 * job.getTimeSheets().size(); m++){ TimeSheet timeSheet =
 * job.getTimeSheets().get(m); // if(timeSheet.getMonth() == selectedMonth &&
 * timeSheet.getDay() == j+1 &&
 * timeSheet.getActivity().getActivityName().equalsIgnoreCase(listActivities.
 * getItemText(k))){
 * 
 * for (int l = 0; l < job.getActivityLists().size(); l++) {
 * if(timeSheet.getMonth() == selectedMonth && timeSheet.getDay() == j+1 &&
 * timeSheet.getActivity().getActivityName().equalsIgnoreCase(job.
 * getActivityLists().get(i).getActivityName())){
 * 
 * } text.setText(timeSheet.getHours()+""); } } } } Label lblJobId = new
 * Label(); lblJobId.setText(job.getJobId()+""); lblJobId.setStyleName("white");
 * if(i ==0){ flex.setWidget(i+1, 33, lblJobId); }else{ flex.setWidget(iIndex,
 * 33, lblJobId); }
 * 
 * } btnSave.setEnabled(true); btnSave.addClickHandler(new ClickHandler(){
 * 
 * @Override public void onClick(ClickEvent event) { saveTimeSheet(jobs); }
 * 
 * }); }
 * 
 * 
 * 
 * @Override public void onFailure(Throwable caught) {
 * Window.alert("fetch jobs list failed"); } }); }
 * 
 * 
 * private void submitTimeSheet() { float totalHours =0; ArrayList<TimeSheet>
 * timeSheetList = new ArrayList<TimeSheet>(); for(int i=0; i<
 * flex.getRowCount()-1; i++){
 * 
 * int iIndex = (i+1)+(i*4); // for(int k =0; k< listActivities.getItemCount();
 * k++){ for(int k =0; k< listActivities.getItemCount(); k++){ for(int j=0;j<31;
 * j++){ MaterialTextBox text ; if(i == 0){ text = (MaterialTextBox)
 * flex.getWidget(i+1, j+1); }else{ text = (MaterialTextBox)
 * flex.getWidget(iIndex, j+1); } text.setWidth("30px"); if(text.getText()!=null
 * && !text.getText().equals("")){ TimeSheet timeSheet = new TimeSheet();
 * timeSheet.setDay(j+1); timeSheet.setHours(Float.parseFloat(text.getText()));
 * Activity activity = new Activity();
 * activity.setActivityId(Integer.parseInt(listActivities.getValue(k)));
 * timeSheet.setActivity(activity); totalHours = totalHours
 * +timeSheet.getHours(); timeSheet.setMonth(selectedMonth);
 * 
 * Label jobField = (Label) flex.getWidget(i+1, 32); Job job = new Job();
 * job.setJobId(Integer.parseInt(jobField.getText())); timeSheet.setJobId(job);
 * User user = new User(); user.setUserId(loggedInUser.getUserId());
 * timeSheet.setUserId(user); timeSheet.setStatus(1);
 * timeSheetList.add(timeSheet);
 * 
 * } } }
 * 
 * } // if(totalHours> ) getMonthAllowedHours(totalHours,
 * timeSheetList.get(0).getMonth(), timeSheetList);
 * 
 * }
 * 
 * private void saveTimeSheet(ArrayList<Job> jobs) { float totalHours =0;
 * ArrayList<TimeSheet> timeSheetList = new ArrayList<TimeSheet>(); try{ for(int
 * i=0; i< flex.getRowCount()-1; i++){ int iIndex = (i+1)+(i*4); // for(int k
 * =0; k< listActivities.getItemCount(); k++){ for(int k =0; k<
 * jobs.get(i).getActivityLists().size(); k++){ for(int j=0;j<31; j++){
 * MaterialTextBox text ; if(i == 0){ text = (MaterialTextBox)
 * flex.getWidget(k+2, j+1); }else{ text = (MaterialTextBox)
 * flex.getWidget(k+(iIndex)+1, j+1); } if(text.getText()!=null &&
 * !text.getText().equals("")){ TimeSheet timeSheet = new TimeSheet();
 * timeSheet.setDay(j+1); timeSheet.setHours(Float.parseFloat(text.getText()));
 * Activity activity = new Activity();
 * activity.setActivityId(jobs.get(i).getActivityLists().get(k).getActivityId())
 * ; // activity.setActivityId(Integer.parseInt(listActivities.getValue(k)));
 * timeSheet.setActivity(activity); totalHours =totalHours
 * +timeSheet.getHours(); timeSheet.setMonth(selectedMonth); Label jobField;
 * if(i ==0){ jobField = (Label) flex.getWidget(i+1, 33); } else{ jobField =
 * (Label) flex.getWidget(iIndex, 33); } Job job = new Job();
 * job.setJobId(Integer.parseInt(jobField.getText())); timeSheet.setJobId(job);
 * User user = new User(); user.setUserId(loggedInUser.getUserId());
 * timeSheet.setUserId(user); timeSheet.setStatus(0);
 * timeSheetList.add(timeSheet);
 * 
 * } } }
 * 
 * } }catch(Exception ex){ System.out.println(ex); }
 * saveTimeSheetToDb(timeSheetList); }
 * 
 * private void saveTimeSheetToDb(ArrayList<TimeSheet> timeSheetList) {
 * rpcService.saveTimeSheet(timeSheetList, new AsyncCallback<String>() {
 * 
 * @Override public void onFailure(Throwable caught) {
 * Window.alert("fail savetime"); }
 * 
 * @Override public void onSuccess(String result) {
 * MaterialToast.fireToast("Time sheet updated"); } }); }
 * 
 * private void submitTimeSheetToDb(ArrayList<TimeSheet> timeSheetList) {
 * rpcService.saveTimeSheet(timeSheetList, new AsyncCallback<String>() {
 * 
 * @Override public void onFailure(Throwable caught) {
 * Window.alert("fail savetime"); }
 * 
 * @Override public void onSuccess(String result) {
 * Window.alert("time sheet submitted"); } }); }
 * 
 * public void getMonthAllowedHours(final float totalHours, int month, final
 * ArrayList<TimeSheet> timeSheetList){ rpcService.fetchMonthAllowedhours(month,
 * new AsyncCallback<Integer>() {
 * 
 * @Override public void onFailure(Throwable caught) {
 * Window.alert("fail : fetch monthallowedHours"); }
 * 
 * @Override public void onSuccess(Integer result) { if(totalHours>=result){
 * submitTimeSheetToDb(timeSheetList); }else{
 * Window.alert("Total Number of hours ("+ totalHours+
 * ") cannot be less than Total working hours(" +result+ ") of this month"); } }
 * }); }
 * 
 * }
 */
