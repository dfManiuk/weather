package by.man.weather.service;

public class ServiceExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceExeption(String message, Exception e) {
		super(message, e);
	}

}
