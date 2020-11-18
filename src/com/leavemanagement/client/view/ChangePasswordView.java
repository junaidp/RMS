package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.leavemanagement.client.presenter.ChangePasswordPresenter.Display;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;

public class ChangePasswordView extends MaterialColumn implements IsWidget, Display {

	PasswordTextBox oldPassword = new PasswordTextBox();
	PasswordTextBox newPassword = new PasswordTextBox();
	PasswordTextBox confrimPassword = new PasswordTextBox();
	MaterialButton btnSubmit = new MaterialButton("Submit");
	MaterialButton btnBack = new MaterialButton("Back");

	public ChangePasswordView() {
		Label lblHeading = new Label("Change Password");
		// lblHeading.setStyleName("blueBackground");
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth() - 100 + "px");

		MaterialColumn vpnl = new MaterialColumn();
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new Label("old password"));
		flex.setWidget(0, 1, oldPassword);
		flex.setWidget(1, 0, new Label("new password"));
		flex.setWidget(1, 1, newPassword);
		flex.setWidget(2, 0, new Label("confirm new password"));
		flex.setWidget(2, 1, confrimPassword);
		flex.setWidget(3, 0, btnBack);
		flex.setWidget(3, 1, btnSubmit);
		add(vpnl);
		MaterialRow hp = new MaterialRow();
		vpnl.add(hp);
		hp.add(lblHeading);
		hp.setWidth("100%");
		vpnl.add(flex);

		oldPassword.setWidth("200px");
		newPassword.setWidth("200px");
		confrimPassword.setWidth("200px");
		btnSubmit.setWidth("100px");
		backMethod();

	}

	private void backMethod() {
		btnBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.back();

			}
		});
	}

	public PasswordTextBox getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(PasswordTextBox oldPassword) {
		this.oldPassword = oldPassword;
	}

	public PasswordTextBox getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(PasswordTextBox newPassword) {
		this.newPassword = newPassword;
	}

	public PasswordTextBox getConfrimPassword() {
		return confrimPassword;
	}

	public void setConfrimPassword(PasswordTextBox confrimPassword) {
		this.confrimPassword = confrimPassword;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
}
