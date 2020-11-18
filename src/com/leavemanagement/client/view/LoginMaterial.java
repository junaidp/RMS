package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.presenter.LoginPresenter.Display;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;

public class LoginMaterial extends Composite implements Display {

	private static LoginMaterialUiBinder uiBinder = GWT.create(LoginMaterialUiBinder.class);

	interface LoginMaterialUiBinder extends UiBinder<Widget, LoginMaterial> {
	}

	public LoginMaterial() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialTextBox txtUserName;
	@UiField
	MaterialTextBox txtPassword;
	@UiField
	MaterialButton btnSubmit;
	@UiField
	Label lblError;

	public MaterialTextBox getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(MaterialTextBox txtUserName) {
		this.txtUserName = txtUserName;
	}

	public MaterialTextBox getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(MaterialTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	@Override
	public Label getLblError() {
		// TODO Auto-generated method stub
		return lblError;
	}

}
