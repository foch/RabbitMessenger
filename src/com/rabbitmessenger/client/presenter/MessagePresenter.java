package com.rabbitmessenger.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rabbitmessenger.client.RabbitMessenger;
import com.rabbitmessenger.client.service.MessageServiceAsync;
import com.rabbitmessenger.client.view.MessageView;

public class MessagePresenter implements MessageView.Presenter {

	private MessageServiceAsync messageService;

	private MessageView messageView;

	public MessagePresenter(MessageServiceAsync messageService,
			MessageView messageView) {
		super();
		this.messageService = messageService;
		this.messageView = messageView;
	}

	private static final String SERVER_ERROR = "Il y a eu une erreur. Essaye une autre fois !";
	private static final String MESSAGE_SENT = "Ton message a été envoyé à Heisenberg !";
	private static final String MP3_SENT = "Ton mp3 a été envoyé à Heisenberg !";

	@Override
	public void sendMessage(String name, String message) {
		messageService.sendMessage(name, message, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				messageView.showMessage(result ? MESSAGE_SENT : SERVER_ERROR);
			}

			@Override
			public void onFailure(Throwable caught) {
				messageView.showMessage(SERVER_ERROR);
			}
		});
	}

	@Override
	public void fetchRabbitStatus() {
		messageService.getStatus(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				messageView.showMessage(SERVER_ERROR);
			}

			@Override
			public void onSuccess(Boolean result) {
				messageView.setStatus(result);
			}
		});
	}

	@Override
	public void playMP3(String mp3) {
		messageService.playMP3(mp3, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				messageView.showMessage(SERVER_ERROR);
			}

			@Override
			public void onSuccess(Boolean result) {
				messageView.showMessage(result ? MP3_SENT : SERVER_ERROR);
			}
		});
	}

	@Override
	public void fetchRabbitName() {
		messageService.getRabbitName(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				messageView.showMessage(SERVER_ERROR);
			}

			@Override
			public void onSuccess(String result) {
				RabbitMessenger.setRabbitName(result);
			}
		});
	}

}
