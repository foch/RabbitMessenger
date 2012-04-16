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
