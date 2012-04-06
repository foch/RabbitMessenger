package com.rabbitmessenger.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MessageServiceAsync {
	void sendMessage(String name, String message, AsyncCallback<Boolean> callback);

	void getStatus(AsyncCallback<Boolean> callback);

	void playMP3(String mp3, AsyncCallback<Boolean> callback);
}
