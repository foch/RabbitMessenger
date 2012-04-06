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

public class MessageWidget extends Composite implements MessageView {

	interface MessageUiBinder extends UiBinder<Widget, MessageWidget> {
	};

	private static final MessageUiBinder uiBinder = GWT
			.create(MessageUiBinder.class);

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
		presenter.sendMessage(nameBox.getText(), messageBox.getText());
	}
	
	@UiHandler("mp3Button")
	void handleMP3ButtonClick(ClickEvent e) {
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
	public void setStatus(boolean status) {
		if (status) {
			statusLabel.setText("Heisenberg est allumé !!! Envoie lui un message.");
		} else {
			statusLabel.setText("Heisenberg est éteint :( Repasse plus tard.");
		}
		
		messageButton.setEnabled(status);
		mp3Button.setEnabled(status);
	}

}
