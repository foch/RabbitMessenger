package com.rabbitmessenger.server;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 
 * @author julien
 */
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
