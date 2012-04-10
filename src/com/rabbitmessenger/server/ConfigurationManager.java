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

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public final class ConfigurationManager {

	private static final Logger log = Logger
			.getLogger(ConfigurationManager.class.getName());

	private static ConfigurationManager instance;

	private Properties properties;

	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager("config.properties");
		}
		return instance;
	}

	private ConfigurationManager(String configFile) {
		properties = new Properties();
		InputStream fileInputStream;
		try {
			fileInputStream = this.getClass().getClassLoader()
					.getResourceAsStream(configFile);

			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			log.severe("Error reading configuration file: " + e.getMessage());
		}
	}

	public String getRabbitName() {
		return properties.getProperty("name");
	}

	public String getToken() {
		return properties.getProperty("token");
	}

	public String getSerialNumber() {
		return properties.getProperty("sn");
	}

}
