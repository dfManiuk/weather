package by.man.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyOpenWeatherConstant {

	public static String url;	

	@Autowired
	private MyOpenWeatherConstant(@Value("${openweathermap.api.base_url}") String  weatherUrlBase,
								  @Value("${openweathermap.api.city_id}")  String weatherSityId,
								  @Value("${openweathermap.api_key}") String weatherApiKey,
								  @Value("${openweathermap.api.units}") String weatherUnits){
		StringBuffer sb = new StringBuffer(weatherUrlBase );
    	sb.append("weather?id=").append(weatherSityId)
    						    .append("&appid=")
    						    .append(weatherApiKey)
    						    .append("&units=")
    						    .append(weatherUnits);
    	MyOpenWeatherConstant.url=sb.toString();
	}
}
