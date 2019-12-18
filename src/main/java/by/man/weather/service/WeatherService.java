package by.man.weather.service;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import by.man.weather.connection.WeatherConnection;
import by.man.weather.connection.WeatherConnectionException;
import by.man.weather.controller.WeatherCallable;

@Service
public class WeatherService {
	
	 private static final Logger logger = LogManager.getLogger(WeatherService.class);
	
	@Value("${schedule.cron}")
	private String time;
	
	private WeatherConnection connection;
	
	@Autowired
	public void setWeatherConnection(WeatherConnection connection) {
		this.connection = connection;
	}
	
	public String getWeatherOneTime() throws ServiceExeption {
		String weatherData="";
		try {
			weatherData = connection.postRequest();
		} catch (WeatherConnectionException e) {
			logger.debug("Not connecting!. StackTrace: " + e);
			throw new ServiceExeption("Not connecting!", e);			
		} 
				
		return weatherData;
	}
	
	public class WeatherExecutor {

		private final int nThreads = 1;
		
		public String getWeatherString() throws ServiceExeption  {		
		String weatherString = "";
		
		
		CronExpression cron;
		try {
			cron = new CronExpression(time);
		} catch (ParseException e1) {
			logger.debug("Not to parse the time cron expression. StackTrace: " + e1);
			throw new ServiceExeption("Not to parse the time cron",e1);
		}
		Date firstExecution = cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
		Date secondExecution = cron.getNextValidTimeAfter(firstExecution);
		Long sleep = secondExecution.getTime() - firstExecution.getTime();
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(nThreads);
		final  ScheduledFuture<String> future  = executor.schedule(new WeatherCallable(connection), sleep, TimeUnit.MILLISECONDS);
		try {
			weatherString = future.get();
		} catch (InterruptedException e) {
			logger.debug("InterruptedException. StackTrace: " + e);
			throw new ServiceExeption("InterruptedException", e);
		} catch (ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new ServiceExeption("ExecutionException", e);
		} finally {
			executor.shutdown();
		}
		return weatherString;
		
		}
	}
	
}
