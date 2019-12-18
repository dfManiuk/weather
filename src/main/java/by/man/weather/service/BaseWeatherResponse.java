package by.man.weather.service;

import org.json.JSONObject;

public class BaseWeatherResponse {

	public static final String JSON_COD = "cod";
	public static final String JSON_MESSAGE = "message";
	public static final String JSON_CNT = "cnt";
	public static final String JSON_LIST = "list";
	
	
	private  int code;
	private  String message;
	private  String cnt;
	
	public BaseWeatherResponse (JSONObject json) {
		this.code = json.optInt (BaseWeatherResponse.JSON_COD, Integer.MIN_VALUE);
		this.message = json.optString (BaseWeatherResponse.JSON_MESSAGE);
		this.cnt = json.optString (BaseWeatherResponse.JSON_CNT);
		
		
	}

	@Override
	public String toString() {
		return "BaseWeatherResponse [code=" + code + ", message=" + message + ", cnt=" + cnt + "]";
	}
	
	
}
