package by.man.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import by.man.weather.entity.example.ResponseBody;
import by.man.weather.entity.example.Structure;
import by.man.weather.repository.MongoRepository;
import by.man.weather.service.ServiceExeption;
import by.man.weather.service.WeatherService;

@Component
public class WeatherMain  {
	
	private MongoRepository mongoRep;
	private WeatherService weatherService;
	
	@Autowired
	public WeatherMain(WeatherService service, MongoRepository mgr) {
		this.weatherService = service;
		this.mongoRep = mgr;
		WeatherDataRecive();
	}
	
	public void WeatherDataRecive() { 
		String weatherDataString = ""; 
		
		for(;;) {		 
		try {
			weatherDataString = weatherService.new WeatherExecutor().getWeatherString();			
		} catch (ServiceExeption e) {
			e.printStackTrace();
		}
		
		mongoRep.save(jsonToPojo(weatherDataString));		
		jsonToPojo(weatherDataString);
		}
	}
	
	public Structure jsonToPojo(String string) {
		Gson gsonObj = new Gson();	
		ResponseBody weatherInfo = gsonObj.fromJson(string, ResponseBody.class);
		Structure structure = new Structure(weatherInfo);
		return structure;	
	}
}
