package com.leavemanagement.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.event.AdminEvent;
import com.leavemanagement.client.event.ChangePasswordEvent;
import com.leavemanagement.client.event.LeaveHistoryEvent;
import com.leavemanagement.client.event.MainEvent;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

public class HeaderPresenter implements Presenter

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private User loggedInUser = new User();

	public interface Display {
		Widget asWidget();

		MaterialLink getaddUser();

		MaterialLink getchangePassword();

		MaterialLink getaddCompany();

		MaterialLink getlogOff();

		MaterialLink getleaveHistory();

		MaterialLink getadmin();

		MaterialLink getuserView();

	}

	public HeaderPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;
		if (loggedInUser.getRoleId().getRoleId() != 5) {
			display.getaddUser().setVisible(false);
		}
		if (loggedInUser.getUserId() == 1 && loggedInUser.getName().equalsIgnoreCase("nauman")) {
			display.getaddCompany().setVisible(true);
			display.getuserView().setVisible(false);
		}
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	public void bind() {
		display.getuserView().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rpcService.fetchLoggedInUser(new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						MaterialToast.fireToast("getting loggedin User:" + caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(User result) {
						eventBus.fireEvent(new MainEvent(result));
					}
				});

			}
		});
		display.getaddUser().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("addUser");
			}
		});
		display.getchangePassword().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new ChangePasswordEvent(loggedInUser));

			}
		});
		display.getaddCompany().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("addCompany");

			}
		});
		display.getlogOff().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rpcService.logOut(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// MaterialToast.fireToast("logging out:" +
						// caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(String result) {
						// loggedInUser = null;
						History.newItem("login");

					}
				});

			}
		});
		display.getadmin().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rpcService.fetchLoggedInUser(new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						MaterialToast.fireToast("getting loggedin User:" + caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(User result) {
						eventBus.fireEvent(new AdminEvent(result));
					}
				});

			}
		});
		display.getleaveHistory().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				rpcService.fetchLoggedInUser(new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						MaterialToast.fireToast("getting loggedin User:" + caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(User result) {
						eventBus.fireEvent(new LeaveHistoryEvent(result));
					}
				});

			}
		});

	}

}
