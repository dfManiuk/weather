package by.man.weather.connection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import by.man.weather.config.MyOpenWeatherConstant;


/**
 * This class implements a connection to the OpenWeather API {@link https://openweathermap.org/api} to get
 * information about the weather in a specific city at a given time. String request forms a class {@link MyOpenWeatherConstant}
 * 
 * @author dfManiuk
 *
 */

@Scope("singleton")
public class WeatherConnection {
	
	private static final Logger logger = LogManager.getLogger(WeatherConnection.class);
	
    private static final int CONNECTION_TIMEOUT = 5000;

    
    /**
     * This method connects to the openWeather server, receives information about weather using the {@link readInputStream (HttpURLConnection con)}
     * as a json string
     * 
     *@return JSON string
     *@throws  WeatherConnectionException
     */  
    public String postRequest() throws WeatherConnectionException {
        URL url;
		HttpURLConnection con = null;
		String content = "";
		try {
			url = new URL(MyOpenWeatherConstant.url);
			con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setConnectTimeout(CONNECTION_TIMEOUT);
	        con.setReadTimeout(CONNECTION_TIMEOUT);
	        con.setDoOutput(true);
	        DataOutputStream out = new DataOutputStream(con.getOutputStream());
	        out.flush();
	        out.close();
	        content = readInputStream(con);
		} catch (MalformedURLException e) {
			logger.debug("URL not connection. StackTrace: " + e);
			throw new WeatherConnectionException("URL not connection ", e);		
		} catch (ProtocolException e) {
			logger.debug("URL not connection. StackTrace: " + e);
			throw new WeatherConnectionException("URL not connection ", e);
		} catch (IOException e) {
			logger.debug("URL not connection. StackTrace: " + e);
			throw new WeatherConnectionException("URL not connection ", e);
		} finally {
			 con.disconnect();
		}           
        System.out.println(content);

		return content;
        
    }

    /**
     * This method directly processes the server response.
     * 
     *@param con (HttpURLConnection) - this is http connection class java.net.HttpURLConnection;
     *@return JSON string
     */ 
    public String readInputStream(HttpURLConnection con){
   
        try (BufferedReader  in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (IOException ex) {
        	logger.debug("Data from openWeather do not resieved. StackTrace: " + ex);
            return "";
        } 
    }
    
}