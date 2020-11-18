package com.leavemanagement.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.leavemanagement.client.event.AdminEvent;
import com.leavemanagement.client.event.AdminEventHandler;
import com.leavemanagement.client.event.ChangePasswordEvent;
import com.leavemanagement.client.event.ChangePasswordEventHandler;
import com.leavemanagement.client.event.LeaveHistoryEvent;
import com.leavemanagement.client.event.LeaveHistoryEventHandler;
import com.leavemanagement.client.event.MainEvent;
import com.leavemanagement.client.event.MainEventHandler;
import com.leavemanagement.client.presenter.AddCompanyPresenter;
import com.leavemanagement.client.presenter.AddUserPresenter;
import com.leavemanagement.client.presenter.AdminPresenter;
import com.leavemanagement.client.presenter.ChangePasswordPresenter;
import com.leavemanagement.client.presenter.HeaderPresenter;
import com.leavemanagement.client.presenter.LeaveHistoryPresenter;
import com.leavemanagement.client.presenter.LoginPresenter;
import com.leavemanagement.client.presenter.MainPresenter;
import com.leavemanagement.client.presenter.Presenter;
import com.leavemanagement.client.view.AddCompanyView;
import com.leavemanagement.client.view.AddUserView;
import com.leavemanagement.client.view.AdminView;
import com.leavemanagement.client.view.ChangePasswordView;
import com.leavemanagement.client.view.HeaderView;
import com.leavemanagement.client.view.LeaveHistoryView;
import com.leavemanagement.client.view.LoginMaterial;
import com.leavemanagement.client.view.MainView;
import com.leavemanagement.shared.User;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;

	private final GreetingServiceAsync rpcService;
	private HasWidgets container;
	private User loggedInUser;
	// private MaterialColumn centerPanel;
	private HasWidgets mainContainer, headerContainer;
	Presenter presenter = null;
	private HeaderView headerView;
	// private VerticalPanel PcenterPanel;

	private int jobId;
	private String callingFrom;
	private int selectedYear;

	public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;

		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(MainEvent.TYPE, new MainEventHandler() {
			public void onMain(MainEvent event) {

				loggedInUser = event.getLoggedInUser();
				selectedYear = event.getSelectedYear();

				History.newItem("main");
			}

		});

		eventBus.addHandler(AdminEvent.TYPE, new AdminEventHandler() {
			public void onAdmin(AdminEvent event) {
				loggedInUser = event.getLoggedInUser();

				History.newItem("admin");

			}
		});

		eventBus.addHandler(LeaveHistoryEvent.TYPE, new LeaveHistoryEventHandler() {
			public void onLeaveHistory(LeaveHistoryEvent event) {
				loggedInUser = event.getLoggedInUser();

				History.newItem("leaveHistory");

			}
		});

		eventBus.addHandler(ChangePasswordEvent.TYPE, new ChangePasswordEventHandler() {
			public void onChangePassword(ChangePasswordEvent event) {
				loggedInUser = event.getLoggedInUser();
				History.newItem("changePassword");
			}
		});

	}

	public void go(final HasWidgets container, HasWidgets headerContainer) {
		this.container = container;
		this.mainContainer = container;
		this.headerContainer = headerContainer;
		// if (centerPanel == null) {
		// centerPanel = new MaterialColumn();
		// }

		if ("".equals(History.getToken())) {
			History.newItem("login");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			presenter = null;

			if (token.equals("login")) {
				presenter = new LoginPresenter(rpcService, eventBus, new LoginMaterial());
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}

			}

			if (token.equals("main")) {
				if (headerView == null)
					headerView = new HeaderView();
				headerView.userView();
				presenter = new HeaderPresenter(rpcService, eventBus, headerView, loggedInUser);
				presenter.go(headerContainer);

				presenter = new MainPresenter(rpcService, eventBus, new MainView(), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}

			}

			if (token.equals("admin")) {
				presenter = new AdminPresenter(rpcService, eventBus, new AdminView(loggedInUser), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
				if (headerView == null)
					headerView = new HeaderView();
				headerView.adminView();
				presenter = new HeaderPresenter(rpcService, eventBus, headerView, loggedInUser);
				presenter.go(headerContainer);
			}

			if (token.equals("leaveHistory")) {
				presenter = new LeaveHistoryPresenter(rpcService, eventBus, new LeaveHistoryView(), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}

			if (token.equals("addCompany")) {
				presenter = new AddCompanyPresenter(rpcService, eventBus, new AddCompanyView(), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}

			if (token.equals("changePassword")) {
				presenter = new ChangePasswordPresenter(rpcService, eventBus, new ChangePasswordView(), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}

			if (token.equals("addUser")) {
				presenter = new AddUserPresenter(rpcService, eventBus, new AddUserView(), loggedInUser);
				if (presenter != null) {
					this.container = mainContainer;
					presenter.go(container);
				}
			}

		}
	}

	private void setContainer(HasWidgets container) {
		this.container = container;
		this.container.clear();
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

	}
}
