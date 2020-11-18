package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Activity;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialRow;

public class TimeSheetTableView extends MaterialColumn {
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private User loggedInUser = null;
	private TimeSheetTree timeSheetTree;
	private Label lblTotalHour;
	private int selectedMonth = 0;
	private int selectedYear = 0;
	private Label heading;

	public TimeSheetTableView(final Job job, User loggedInUser2, MaterialListBox listMonth, MaterialListBox listYear,
			final boolean chargeable2, TimeSheetTree timeSheetTree) {
		selectedMonth = Integer.parseInt(listMonth.getSelectedValue());
		selectedYear = Integer.parseInt(listYear.getSelectedItemText());

		this.timeSheetTree = timeSheetTree;
		MaterialRow columnMain = new MaterialRow();

		ScrollPanel scrollPanelWidth = new ScrollPanel();
		// columnMain.setWidth("1200px");
		this.loggedInUser = loggedInUser2;
		ScrollPanel scrollPanel = new ScrollPanel();

		MaterialButton btnSave = new MaterialButton("Save");
		final FlexTable flex = new FlexTable();
		final FlexTable flex1 = new FlexTable();
		// Window.alert("start ");
		ScrollPanel scrollFlex1 = new ScrollPanel();
		scrollFlex1.setHeight("250px");
		// scrollFlex1.setWidth("1700px");
		scrollFlex1.add(flex1);
		setHandler(job, flex, flex1, listMonth, listYear);
		// Window.alert("before adding anything ");
		// scrollPanel.setHeight("400px");
		// scrollPanel.setWidth("1896px");
		// scrollPanel.add(flex1);
		// Window.alert("adding flex1 in scrollpanel");
		// ScrollPanel sc = new ScrollPanel();
		// sc.setHeight("600px");
		// sc.setWidth("1300px");
		// Window.alert("createed sc");
		columnMain.add(flex);
		// Window.alert("added flex in column main ");
		// add(flex);
		// add(scrollPanel);
		// add(btnSave);
		columnMain.add(scrollFlex1);
		// Window.alert("added scrollpanel in column main");
		columnMain.add(btnSave);
		add(columnMain);
		// Window.alert("adding cloumn main oto sc ");
		// add(sc);

		saveHandler(job, chargeable2, btnSave, flex1, flex);
	}

	private void setHandler(final Job job, final FlexTable flex, final FlexTable flex1, MaterialListBox listMonth,
			MaterialListBox listYear) {

		for (int k = 0; k < 31; k++) {

			VerticalPanel vpHeading = new VerticalPanel();
			final MaterialLabel lblSum = new MaterialLabel("0");
			final Data data = new Data();
			data.setDay(k);
			lblSum.setWidth("30px");

			heading = new Label(k + 1 + "");
			// Window.alert("after adding lbl heading");
			heading.addStyleName("blueBold");
			vpHeading.add(heading);
			heading.setWidth("30px");
			vpHeading.add(lblSum);
			Label lbl = new Label("32");
			lbl.setStyleName("white");
			flex.setWidget(0, k + 1, vpHeading);
			flex.setWidget(0, 32, lbl);
			// Window.alert("lbltotal before");
			lblTotalHour = new Label("Total Hours:");
			lblTotalHour.addStyleName("blueBold");
			lblTotalHour.setWidth("195px");
			flex.setWidget(0, 0, lblTotalHour);
			for (int i = 0; i < job.getActivityLists().size(); i++) {
				Activity activity = job.getActivityLists().get(i);
				Label lblName = new Label(activity.getActivityName());
				// Window.alert("lbname");
				lblName.setWidth("150px");
				lblName.setWordWrap(true);

				flex1.setWidget(i + 1, 0, lblName);

				// Window.alert("after adding lblname");
				final TextBox text = new TextBox();
				// setdoublle
				// text.setValue("0");
				text.setValue("0");
				text.setText("");
				// text.getElement().setPropertyString("placeholder", "0");
				text.setAlignment(TextAlignment.CENTER);
				text.setTitle(activity.getActivityName());
				text.setWidth("30px");
				text.setMaxLength(4);
				flex1.setWidget(i + 1, k + 1, text);
				// Window.alert("befotre adding text");
				for (int m = 0; m < activity.getTimeSheets().size(); m++) {
					TimeSheet timeSheet = activity.getTimeSheets().get(m);
					if (timeSheet.getMonth() == selectedMonth && timeSheet.getDay() == k + 1
							&& timeSheet.getYear() == selectedYear
							&& job.getJobId() == timeSheet.getJobId().getJobId()) {
						double roundOff1 = Math.round(timeSheet.getHours() * 100.0) / 100.0;
						text.setValue(roundOff1 + "");
						// break; //TODO

					}
				}

				try {
					if (text.getValue().isEmpty()) {
						data.setSum(data.getSum() + 0);
					} else {
						data.setSum(data.getSum() + Float.parseFloat(text.getValue()));
					}
					double roundOff = Math.round(data.getSum() * 100.0) / 100.0;
					lblSum.setText(roundOff + "");

					text.addKeyDownHandler(new KeyDownHandler() {

						@Override
						public void onKeyDown(KeyDownEvent event) {

							if (isStringInt(text.getText())) {

								// if (!text.getText().isEmpty()) {
								// if (event.getNativeKeyCode() != 9) {
								// GWT.log(data.getOldValue() + "line 153
								// after adding it to upper");
								// Logging.console("testing logsss");
								// System.out.println(data.getOldValue()
								// + "line 153 after adding it to
								// upper");
								data.setOldValue(text.getValue().isEmpty() ? 0 : Float.parseFloat(text.getValue()));
								// GWT.log(data.getOldValue() + "line 161
								// before adding it to upper");
								// System.out.println(data.getOldValue()
								// + "line 156 before adding it to
								// upper");
								// }
							}
						}

						// }

					});
					// timeSheetTree.hoursCalculate(k, timeSheets, data, lblSum,
					// listMonth2, loggedInUser);

					text.addBlurHandler(new BlurHandler() {

						@Override

						public void onBlur(BlurEvent event) {

						}
					});

					text.addKeyUpHandler(new KeyUpHandler() {

						@Override
						public void onKeyUp(final KeyUpEvent event) {

							calculateTotal(lblSum, data, text, event);

						} // }

						private void calculateTotal(final MaterialLabel lblSum, final Data data, final TextBox text,
								KeyUpEvent event) {
							if (event.getNativeKeyCode() != 9) {
								String textValue = "0";
								if (text.getValue() == null || text.getValue().equals("")) {
									textValue = "0";
								} else {
									textValue = text.getValue();
								}
								if (isStringInt(textValue)) {
									if (textValue.startsWith("0") && textValue.length() > 1) {
										// GWT.log(text.getValue() + "line 193
										// after start with 0");
										// System.out.println(text.getValue() +
										// "line 186 after start with 0");
										text.setValue(textValue.substring(1));
										// GWT.log(text.getValue() + "line 197
										// before starts with 0 condition");
										// System.out.println(text.getValue() +
										// "line 193 before starts with 0
										// condition");
									}
									if (!textValue.isEmpty()) {
										GWT.log(data.getOldValue() + "line 203");
										// System.out.println(data.getOldValue()
										// +
										// "line 198");
										data.setSum(data.getSum() - data.getOldValue());
										// GWT.log(data.getSum() +
										// "data.getsum");
										// GWT.log(data.getOldValue() + "old
										// value data");
										// GWT.log(data.getSum() -
										// data.getOldValue() + "line 208
										// data.setseum minnus");

										data.setSum(data.getSum() + Float.parseFloat(textValue));
										// System.out.println(data.getSum() +
										// Float.parseFloat(text.getValue())
										// + "line 193 data.setseum minnus");
										// GWT.log(data.getSum() +
										// Float.parseFloat(text.getValue())
										// + "line 215 data.setseum plus");

										double roundOff = Math.round(data.getSum() * 100.0) / 100.0;
										lblSum.setText(roundOff + "");
										// GWT.log(data.getSum() + "");
										// Uncomment this to test TotalHours
										timeSheetTree.updateTotalHours(data.getDay(), data.getOldValue(),
												Float.parseFloat(textValue));

										data.setOldValue(Float.parseFloat(textValue));
									}
								}
							}
						}
					});

				} catch (Exception ex) {
					System.out.println("no textbox here");
				}

				////////////

				// }

				Label lblActicityId = new Label();
				lblActicityId.setText(job.getActivityLists().get(i).getActivityId() + "");
				lblActicityId.setStyleName("white");
				flex1.setWidget(i + 1, 32, lblActicityId);
				lblActicityId.setVisible(false);

			}

		}
	}

	private void saveHandler(final Job job, final boolean chargeable2, MaterialButton btnSave, final FlexTable flex1,
			final FlexTable flex) {
		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				saveTimeSheet(job, flex1, chargeable2, flex);
			}

		});
	}

	private void saveTimeSheet(Job job, FlexTable flex, boolean chargeable2, FlexTable flex2) {
		float totalHours = 0;
		ArrayList<TimeSheet> timeSheetList = new ArrayList<TimeSheet>();
		for (int i = 0; i < flex.getRowCount() - 1; i++) {
			for (int j = 0; j < 31; j++) {
				TextBox text = (TextBox) flex.getWidget(i + 1, j + 1);
				text.setWidth("30px");
				if (text.getText() != null && !text.getText().equals("")) {
					TimeSheet timeSheet = new TimeSheet();
					timeSheet.setDay(j + 1);
					timeSheet.setHours(Float.parseFloat(text.getText()));
					totalHours = totalHours + timeSheet.getHours();
					timeSheet.setMonth(selectedMonth);
					timeSheet.setYear(selectedYear);
					Label activityField = (Label) flex.getWidget(i + 1, 32);
					Activity activity = new Activity();
					activity.setActivityId(Integer.parseInt(activityField.getText()));
					timeSheet.setActivity(activity);
					timeSheet.setJobId(job);
					User user = new User();
					user.setUserId(loggedInUser.getUserId());
					timeSheet.setUserId(user);
					timeSheet.setStatus(0);
					if (timeSheet.getHours() >= 0)
						timeSheetList.add(timeSheet);

				}
			}

		}

		saveTimeSheetToDb(timeSheetList, chargeable2, job, flex, flex2);
	}

	private void saveTimeSheetToDb(ArrayList<TimeSheet> timeSheetList, final boolean chargeable2, final Job job,
			final FlexTable flex, final FlexTable flex2) {
		// final LoadingPopup loadingPopup = new LoadingPopup();
		// loadingPopup.display();
		MaterialLoader.loading(true);
		rpcService.saveTimeSheet(timeSheetList, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail savetime");
				// if (loadingPopup != null) {
				// loadingPopup.remove();
				// }
				MaterialLoader.loading(false);
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("time sheet saved");
				MaterialLoader.loading(false);
				// timeSheetTree.calculateTotalHoursForTimeSheet(result,
				// loggedInUser);
				// if (loadingPopup != null) {
				// loadingPopup.remove();
				// }
				// rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable2,
				// new AsyncCallback<ArrayList<Job>>() {
				//
				// @Override
				// public void onFailure(Throwable caught) {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void onSuccess(ArrayList<Job> result) {
				// // for (int j = 0; j < result.size(); j++) {
				// // final Job job1 = result.get(j);
				// // flex.clear();
				// // flex2.clear();
				// // setHandler(job, flex2, flex);
				// // }
				//
				// }
				//
				// });

			}
		});
	}

	public boolean isStringInt(String s) {
		try {

			if (s.isEmpty())
				s = "0";
			if (s.equals("."))
				Integer.parseInt(s);

		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public Label getLblTotalHour() {
		return lblTotalHour;
	}

	public void setLblTotalHour(Label lblTotalHour) {
		this.lblTotalHour = lblTotalHour;
	}

	public Label getHeading() {
		return heading;
	}

	public void setHeading(Label heading) {
		this.heading = heading;
	}
}
