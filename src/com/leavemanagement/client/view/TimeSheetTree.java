package com.leavemanagement.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Allocations;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;

public class TimeSheetTree extends Composite {
	int selectedMonth = 0;
	int selectedYear = 0;
	@UiField
	MaterialTree tree;
	@UiField
	MaterialRow rowMonth;
	@UiField
	FlexTable flex;
	@UiField
	MaterialRow rowTotal;
	Label heading;
	MaterialColumn c2;
	ScrollPanel panelScroll = new ScrollPanel();
	MaterialRow rowYearMonth = new MaterialRow();
	private boolean chargeable = false;
	MaterialListBox listMonth = new MaterialListBox();
	MaterialListBox listYear = new MaterialListBox();
	MaterialCheckBox chkChargeable = new MaterialCheckBox();
	Boolean booleanJobClick = false;
	Label lblTotalHour = new Label("Total Hours for all Jobs:");
	MaterialColumn vpHeading;
	private MaterialColumn vpLabel = new MaterialColumn();
	private ArrayList<Job> listjob = new ArrayList<Job>();
	private static TimeSheetTreeUiBinder uiBinder = GWT.create(TimeSheetTreeUiBinder.class);
	float sum = 0;
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);

	interface TimeSheetTreeUiBinder extends UiBinder<Widget, TimeSheetTree> {
	}

	public TimeSheetTree(final User loggedInUser) {

		initWidget(uiBinder.createAndBindUi(this));
		System.out.println("hello");
		// for (int k = 0; k < 31; k++) {
		// // Window.alert("in loop k");
		//
		// VerticalPanel vpHeading = new VerticalPanel();
		// Label heading = new Label(k + 1 + "");
		// heading.addStyleName("blueBold");
		// heading.setWidth("30px");
		// vpHeading.add(heading);
		//
		// flex.getElement().getStyle().setMarginLeft(250, Unit.PX);
		// flex.setWidget(0, k + 1, vpHeading);
		//
		// }
		rowMonth.add(rowYearMonth);
		MaterialColumn collistMonth = new MaterialColumn();
		collistMonth.add(listMonth);
		MaterialColumn collistYear = new MaterialColumn();

		collistYear.add(listYear);
		rowYearMonth.add(collistMonth);
		rowMonth.add(collistYear);
		rowMonth.add(rowYearMonth);
		rowMonth.add(chkChargeable);
		listYear.setWidth("70px");
		listMonth.setWidth("110px");
		//
		// lblTotalHour.setWidth("300px");
		lblTotalHour.setVisible(false);

		chkChargeable.setText(Allocations.CHARGEABLE.getName());
		listYear.addItem("0", "2019");
		listYear.addItem("1", "2020");
		listYear.addItem("2", "2021");
		listYear.addItem("3", "2022");
		listYear.addItem("3", "2023");
		listYear.setSelectedIndex(1);

		listMonth.addItem("0", "Select Month");
		listMonth.addItem("1", "Jan");
		listMonth.addItem("2", "Feb");
		listMonth.addItem("3", "Mar");
		listMonth.addItem("4", "Apr");
		listMonth.addItem("5", "May");
		listMonth.addItem("6", "Jun");
		listMonth.addItem("7", "Jul");
		listMonth.addItem("8", "Aug");
		listMonth.addItem("9", "Sep");
		listMonth.addItem("10", "Oct");
		listMonth.addItem("11", "Nov");
		listMonth.addItem("12", "Dec");
		// panelScroll.setHeight("800px");
		// panelScroll.add(tree);

		listMonth.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				refreshOnChangeHandler(loggedInUser);
			}

		});

		listYear.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				refreshOnChangeHandler(loggedInUser);
			}

		});

		chkChargeable.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				tree.clear();
				chargeable = event.getValue();
				fetchJobs(loggedInUser, selectedMonth, selectedYear);
			}
		});

	}

	private void refreshOnChangeHandler(final User loggedInUser) {
		lblTotalHour.setVisible(true);
		tree.clear();
		selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
		selectedYear = Integer.parseInt((listYear.getSelectedItemText()));
		fetchJobs(loggedInUser, selectedMonth, selectedYear);
	}

	private void fetchJobs(final User loggedInUser, int selectedMonth2, int selectedYear2) {
		rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable, selectedMonth2, selectedYear2,
				new AsyncCallback<ArrayList<Job>>() {

					@Override
					public void onSuccess(final ArrayList<Job> result) {
						// start
						// rowTotal.getElement().getStyle().setMarginLeft(20,
						// Unit.PX);
						rowTotal.clear();

						// tree.clear();
						calculateTotalHoursForTimeSheet(result, loggedInUser);
						// end

						for (int i = 0; i < result.size(); i++) {
							// sum = 0;
							final Job job = result.get(i);
							float sum = 0;
							float total = 0;

							total = calculatePerJobHour(job, total);

							final MaterialTreeItem treeItem1 = new MaterialTreeItem();
							treeItem1.setIconType(IconType.FOLDER_SHARED);
							//
							// FlexTable tableTree = new FlexTable();
							// HTML htmlTotal = new HTML(total + "");
							// HTML htmlText = new
							// HTML(result.get(i).getJobName());
							// Label lblt = new Label(htmlText + "");
							// htmlText.setWidth("300px");
							// tableTree.setWidget(0, 1, htmlText);
							// tableTree.setWidget(0, 2, htmlTotal);
							//
							// HTML html = new HTML(total+"");
							// html.getElement().getStyle().setColor(value);
							// treeItem1.add(tableTree);
							double jobtotalworkhour = Math.round(total * 100.0) / 100.0;
							treeItem1.setText(result.get(i).getJobName() + " " + "(" + jobtotalworkhour + ")");

							final Data data = new Data();
							data.setDataDisplayed(false);

							tree.add(treeItem1);

							clickHandler(loggedInUser, job, treeItem1);
						}

					}

					private float calculatePerJobHour(final Job job, float total) {
						float sum;
						for (int j = 0; j < job.getTimeSheets().size(); j++) {

							if (selectedMonth == job.getTimeSheets().get(j).getMonth()
									&& (selectedYear == job.getTimeSheets().get(j).getYear())) {
								sum = job.getTimeSheets().get(j).getHours();
								total = total + sum;
							}

						}
						return total;
					}

					private void clickHandler(final User loggedInUser, final Job job,
							final MaterialTreeItem treeItem1) {
						treeItem1.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {

								if (!listjob.contains(job)) {
									if (booleanJobClick == false) {
										booleanJobClick = true;
										fetchSelectedJobForTimeSheet(loggedInUser, job, treeItem1);
									}
									booleanJobClick = false;
									listjob.add(job);
								}

							}
						});
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail fetch jobs " + caught.getLocalizedMessage());

					}
				});
	}

	private void fetchSelectedJobForTimeSheet(final User loggedInUser, Job job, final MaterialTreeItem treeItem1) {

		rpcService.fetchSelectedJobForTimeSheet(loggedInUser, chargeable, job.getJobId(),
				new AsyncCallback<ArrayList<Job>>() {

					@Override
					public void onSuccess(ArrayList<Job> job) {
						displayInTree(loggedInUser, job.get(0), treeItem1, listMonth, listYear, chargeable);

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("faile fetch selectedd job" + caught.getLocalizedMessage());

					}
				});

	}

	public void calculateTotalHoursForTimeSheet(List<Job> result, User loggedInUser) {
		rowTotal.add(vpLabel);
		for (int k = 1; k <= 31; k++) {

			vpLabel.add(lblTotalHour);
			MaterialLabel lblSum = new MaterialLabel("0");
			vpHeading = new MaterialColumn();
			lblSum.setWidth("3px");
			Data data = new Data();
			data.setSum(0);

			heading = new Label(k + "");
			// Window.alert("after adding lbl heading");
			heading.addStyleName("blueBold1");
			lblTotalHour.addStyleName("blueBold1");

			vpHeading.add(heading);
			vpHeading.add(lblSum);

			rowTotal.add(vpHeading);
			for (int i = 0; i < result.size(); i++) {
				hoursCalculate(k, result.get(i).getTimeSheets(), data, lblSum, listMonth, loggedInUser);
			}
			double roundOff1 = Math.round(data.getSum() * 100.0) / 100.0;
			lblSum.setText(roundOff1 + "");
		}

	}

	// on keyup
	protected void updateTotalHours(int day, float currentHours, float newHours) {

		try {
			MaterialColumn vpHeading = (MaterialColumn) rowTotal.getWidget(day + 1);
			MaterialLabel lblSum = (MaterialLabel) vpHeading.getWidget(1);
			float oldHours = Float.parseFloat(lblSum.getText());
			float totalHours = 0;
			totalHours = oldHours - currentHours;
			totalHours = totalHours + newHours;
			double roundOff = Math.round(totalHours * 100.0) / 100.0;

			lblSum.setText(roundOff + "");
		} catch (Exception ex) {
			System.out.println("error in update Total Hours(TimeSheetTree");
		}
	}

	public void hoursCalculate(int k, ArrayList<TimeSheet> timeSheets, Data data, MaterialLabel lblSum,
			MaterialListBox listMonth2, User loggedInUser) {

		for (int i = 0; i < timeSheets.size(); i++) {
			selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
			selectedYear = Integer.parseInt((listYear.getSelectedItemText()));

			if (k == timeSheets.get(i).getDay() && (selectedMonth == timeSheets.get(i).getMonth())
					&& selectedYear == timeSheets.get(i).getYear()) {
				float total = timeSheets.get(i).getHours();
				data.setSum(data.getSum() + total);
				sum = total + sum;

				// fetchJobs(loggedInUser);
			}

		}

	}

	private void displayInTree(final User loggedInUser, final Job job, final MaterialTreeItem treeItem1,
			final MaterialListBox listMonth, MaterialListBox listYear, boolean chargeable2) {

		MaterialTreeItem tree2 = new MaterialTreeItem();
		ScrollPanel scrolltimesheet = new ScrollPanel();
		scrolltimesheet.setHeight("400px");
		// scrolltimesheet.setWidth("1500px");

		final TimeSheetTableView timeSheetTable = new TimeSheetTableView(job, loggedInUser, listMonth, listYear,
				chargeable2, this);

		scrolltimesheet.add(timeSheetTable);
		tree2.add(scrolltimesheet);

		treeItem1.add(tree2);

	}

}
