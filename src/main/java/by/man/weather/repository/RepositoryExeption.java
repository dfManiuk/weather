package by.man.weather.repository;

public class RepositoryExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RepositoryExeption(String message, Exception e) {
		super(e);
	}

}
