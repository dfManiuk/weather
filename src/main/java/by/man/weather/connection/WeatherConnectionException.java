package by.man.weather.connection;

public class WeatherConnectionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WeatherConnectionException(String message, Exception e) {
		super(message, e);
	}

}
