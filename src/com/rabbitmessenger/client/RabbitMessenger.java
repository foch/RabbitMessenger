package com.rabbitmessenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.rabbitmessenger.client.presenter.MessagePresenter;
import com.rabbitmessenger.client.widget.MessageWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RabbitMessenger implements EntryPoint {
	private final ApplicationController controller = new ApplicationController();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		MessageWidget mainView = new MessageWidget();

		MessagePresenter messagePresenter = new MessagePresenter(
				controller.getMessageService(), mainView);

		mainView.setPresenter(messagePresenter);

		// get the rabbit status
		messagePresenter.getStatus();
		
		RootPanel.get("mainPanel").add(mainView);
		
	}
}
