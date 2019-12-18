package by.man.weather.controller;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.man.weather.connection.WeatherConnection;
import by.man.weather.connection.WeatherConnectionException;

public class WeatherCallable implements Callable<String>{
	
	private static final Logger logger = LogManager.getLogger(WeatherCallable.class);

	WeatherConnection connection;
	
	public WeatherCallable(WeatherConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public String call() throws Exception {
		String weatherData="";
		try {
			weatherData = connection.postRequest();
		} catch (WeatherConnectionException e) {
			logger.debug("URL not connection. StackTrace: " + e);
		} 
		return weatherData;
	}

}
