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
package com.rabbitmessenger.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.rabbitmessenger.client.view.MessageView;
import com.rabbitmessenger.shared.StatusWrapper;

public class MessageWidget extends Composite implements MessageView {

	interface MessageUiBinder extends UiBinder<Widget, MessageWidget> {
	};

	private static final MessageUiBinder uiBinder = GWT
			.create(MessageUiBinder.class);

	private static final String EMPTY_FIELD_MESSAGE = "Il faut mettre ton nom et ton message, banane !!!";
	private static final String EMPTY_FIELD_MP3 = "Il faut mettre ton MP3, espèce de nouille !!!";
	
	@UiField
	Label statusLabel;

	@UiField
	TextBox nameBox;

	@UiField
	TextBox messageBox;

	@UiField
	Button messageButton;

	@UiField
	TextBox mp3Box;

	@UiField
	Button mp3Button;

	Presenter presenter;

	public MessageWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("messageButton")
	void handleMessageButtonClick(ClickEvent e) {
		
		if ("".equals(nameBox.getText()) || "".equals(messageBox.getText())) {
			Window.alert(EMPTY_FIELD_MESSAGE);
			return;
		}
		presenter.sendMessage(nameBox.getText(), messageBox.getText());
	}

	@UiHandler("mp3Button")
	void handleMP3ButtonClick(ClickEvent e) {
		
		if ("".equals(mp3Box.getText())) {
			Window.alert(EMPTY_FIELD_MP3);
			return;
		}
		presenter.playMP3(mp3Box.getText());
	}

	@Override
	public void showMessage(String result) {
		Window.alert(result);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setStatus(StatusWrapper status) {
		statusLabel.setText(status.getMessage());
		messageButton.setEnabled(status.getStatus());
		mp3Button.setEnabled(status.getStatus());
	}

}
