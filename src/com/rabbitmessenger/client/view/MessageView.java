package com.rabbitmessenger.client.view;


public interface MessageView {

	interface Presenter {
		void fetchRabbitName();
		void sendMessage(String name, String message);
		void playMP3(String mp3);
		void fetchRabbitStatus();
	}

	void showMessage(String result);

	void setPresenter(Presenter presenter);

	void setStatus(boolean status);

}
