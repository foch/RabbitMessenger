package com.rabbitmessenger.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class RabbitCommunication {

	public static AtomicInteger spaceCounter = new AtomicInteger(0);

	public static final String TOKEN = "token=f9cb4224-b6a7-4b41-b600-510df70167a5&sn=0013D38114F2";

	private static final Logger log = Logger
			.getLogger(RabbitCommunication.class.getName());

	public static boolean sendMessage(String message) {

		String url = "http://api.nabaztag.com/vl/api.jsp?" + TOKEN + "&tts="
				+ message;

		return sendURL(url);
	}

	public static boolean playMP3(String message) {

		String url = "http://api.nabaztag.com/vl/FR/api_stream.jsp?" + TOKEN
				+ "&urlList=" + message;

		return sendURL(url);
	}

	private static boolean sendURL(String inputUrl) {

		// add some spaces at the end of the URL to avoid caching by Google.
//		int addSpaces = spaceCounter.incrementAndGet() % 32;
//		StringBuilder sb = new StringBuilder(inputUrl);
//		for (int i = 0; i < addSpaces; i++) {
//			sb.append("%20");
//		}
//		inputUrl = sb.toString();
		inputUrl = inputUrl.replaceAll(" ", "%20");

		log.fine("Calling URL: " + inputUrl);

		boolean result = false;

		try {
			URL url = new URL(inputUrl);
			
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setDoOutput(true);
            
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // OK
    			BufferedReader reader = new BufferedReader(new InputStreamReader(
    					connection.getInputStream()));
    			String line;

    			while ((line = reader.readLine()) != null) {
    				log.fine("Received: " + line);
    				result = "<string>ok</string>".equals(line);
    				break;
    			}
    			reader.close();
            } else {
                // Server returned HTTP error code.
            	log.severe("Received HTTP error code: " + connection.getResponseCode());
            	return false;
            }
			
		} catch (MalformedURLException e) {
			log.severe("MalformedURLException: " + e.getLocalizedMessage());
			return false;
		} catch (IOException e) {
			log.severe("IOException: " + e.getLocalizedMessage());
			return false;
		}

		return result;
	}

}