package com.rabbitmessenger.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class StatusWrapper implements IsSerializable {

	private boolean status;

	private String message;

	public static StatusWrapper create(boolean status, String message) {
		return new StatusWrapper(status, message);
	}

	public StatusWrapper() {
		super();
	}

	public StatusWrapper(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
