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
package com.rabbitmessenger.client.view;

import com.rabbitmessenger.shared.StatusWrapper;

public interface MessageView {

	interface Presenter {
		void fetchRabbitStatus();

		void sendMessage(String name, String message);

		void playMP3(String mp3);
	}

	void showMessage(String result);

	void setPresenter(Presenter presenter);

	void setStatus(StatusWrapper status);

}
