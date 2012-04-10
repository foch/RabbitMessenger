package com.rabbitmessenger.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("message")
public interface MessageService extends RemoteService {

	String getRabbitName();
	
	boolean sendMessage(String name, String message)
			throws IllegalArgumentException;

	boolean getStatus() throws IllegalArgumentException;

	boolean playMP3(String mp3) throws IllegalArgumentException;

}
