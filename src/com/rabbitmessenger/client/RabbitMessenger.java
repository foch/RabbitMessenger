/*
 * This file is part of Rabbit Messenger.
 * 
 * Rabbit Messenger is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Rabbit Messenger is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Rabbit Messenger. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2012 Julien Faucher
 */
package com.rabbitmessenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
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
		messagePresenter.fetchRabbitStatus();

		RootLayoutPanel.get().add(mainView);
	}

}
