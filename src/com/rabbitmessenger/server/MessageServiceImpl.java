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
package com.rabbitmessenger.server;

import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rabbitmessenger.client.service.MessageService;
import com.rabbitmessenger.shared.StatusWrapper;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MessageServiceImpl extends RemoteServiceServlet implements
		MessageService {

	private static final Logger log = Logger.getLogger(MessageServiceImpl.class
			.getName());

	public static final ConfigurationManager config = ConfigurationManager
			.getInstance();
	
	private static final String MESSAGE_SENT = "Ton message a été envoyé à ";
	private static final String MP3_SENT = "Ton mp3 a été envoyé à Heisenberg !";
	
	@Override
	public StatusWrapper sendMessage(String name, String message)
			throws IllegalArgumentException {

		log.info("Received message '" + message + "' from " + name);

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		name = escapeHtml(name);
		message = escapeHtml(message);

		boolean result = false;

		String msg = "vous avez un message de la part de " + name + ". "
				+ message;
		result = RabbitCommunication.sendMessage(msg);

		if (result == false) {
			log.warning("Unable to send: " + msg);
			return StatusWrapper.create(false, "Unable to send message");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.severe("InterruptedException: " + e.getLocalizedMessage());
			e.printStackTrace();
			return StatusWrapper.create(false, "InterruptedException caught");
		}

		return StatusWrapper.create(true, MESSAGE_SENT + config.getRabbitName() + " !");
	}

	@Override
	public StatusWrapper getStatus() throws IllegalArgumentException {

		log.fine("Get status");

		// send an empty message
		return StatusWrapper.create(RabbitCommunication.sendMessage(""), "");
	}

	@Override
	public StatusWrapper playMP3(String mp3) throws IllegalArgumentException {

		log.info("Playing MP3 " + mp3);

		// send an empty message
		boolean result = RabbitCommunication.playMP3(mp3);
		if (result == false) {
			log.warning("Unable to play MP3: " + mp3);
			return StatusWrapper.create(false, "Unable to play MP3");
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.severe("InterruptedException: " + e.getLocalizedMessage());
			return StatusWrapper.create(false, "InterruptedException caught");
		}

		return StatusWrapper.create(true, MP3_SENT + config.getRabbitName() + " !");
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll(" ", "%20").toLowerCase();
	}

}
