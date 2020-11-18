package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.presenter.HeaderPresenter.Display;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavSection;

public class HeaderView extends Composite implements Display {

	private static HeaderViewUiBinder uiBinder = GWT.create(HeaderViewUiBinder.class);

	interface HeaderViewUiBinder extends UiBinder<Widget, HeaderView> {
	}

	@UiField
	MaterialNavSection navSection;
	@UiField
	MaterialLink addUser;
	@UiField
	MaterialLink changePassword;
	@UiField
	MaterialLink addCompany;
	@UiField
	MaterialLink logOff;
	@UiField
	MaterialLink leaveHistory;
	@UiField
	MaterialLink admin;
	@UiField
	MaterialNavBar nav;
	@UiField
	MaterialLink userView;

	public HeaderView() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public void adminView() {
		// navSection.setVisible(false);
		// nav.hideProgress();
		// Timer t = new Timer() {
		//
		// @Override
		// public void run() {
		// navSection.setVisible(true);
		//
		// }
		// };
		//
		// t.schedule(1000);

		changePassword.setVisible(true);

		leaveHistory.setVisible(false);
		admin.setVisible(false);
		logOff.setVisible(true);
		addCompany.setVisible(false);
		addUser.setVisible(true);
		userView.setVisible(true);
	}

	public void userView() {

		changePassword.setVisible(true);
		leaveHistory.setVisible(true);
		admin.setVisible(true);
		logOff.setVisible(true);
		addCompany.setVisible(false);
		addUser.setVisible(false);
		userView.setVisible(false);
	}

	@Override
	public MaterialLink getaddUser() {
		return addUser;
	}

	@Override
	public MaterialLink getchangePassword() {
		return changePassword;
	}

	@Override
	public MaterialLink getaddCompany() {
		return addCompany;
	}

	@Override
	public MaterialLink getlogOff() {
		return logOff;

	}

	@Override
	public MaterialLink getuserView() {
		return userView;

	}

	public MaterialLink getleaveHistory() {
		return leaveHistory;
	}

	public void setLeaveHistory(MaterialLink leaveHistory) {
		this.leaveHistory = leaveHistory;
	}

	public MaterialLink getadmin() {
		return admin;
	}

	public void setAdmin(MaterialLink admin) {
		this.admin = admin;
	}

}
