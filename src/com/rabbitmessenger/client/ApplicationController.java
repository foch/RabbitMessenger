package com.rabbitmessenger.client;

import com.google.gwt.core.client.GWT;
import com.rabbitmessenger.client.service.MessageService;
import com.rabbitmessenger.client.service.MessageServiceAsync;

public class ApplicationController {

	private MessageServiceAsync messageService;

	public MessageServiceAsync getMessageService() {
		if (messageService == null) {
			messageService = GWT.create(MessageService.class);
		}
		return messageService;
	}

}
