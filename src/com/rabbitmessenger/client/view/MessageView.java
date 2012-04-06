package com.rabbitmessenger.client.view;


public interface MessageView {

	interface Presenter {
		void sendMessage(String name, String message);
		void playMP3(String mp3);
		void getStatus();
	}

	void showMessage(String result);

	void setPresenter(Presenter presenter);

	void setStatus(boolean status);

}
