package by.man.weather.entity.example;

import com.google.gson.annotations.Expose;

public class Structure {

	public Structure(ResponseBody body) {
		this.response_body=body;
		this.response_status=body.getCod();
	}
	
	@Expose
	private String id;
	
	@Expose
	private String response_date;
	
	@Expose
	private Integer response_status;
	
	@Expose
	private ResponseBody response_body;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponse_date() {
		return response_date;
	}

	public void setResponse_date(String response_date) {
		this.response_date = response_date;
	}

	public Integer getResponse_status() {
		return response_status;
	}

	public void setResponse_status(Integer response_status) {
		this.response_status = response_status;
	}

	public ResponseBody getResponse_body() {
		return response_body;
	}

	public void setResponse_body(ResponseBody response_body) {
		this.response_body = response_body;
	}

	@Override
	public String toString() {
		return "Structure [id=" + id + ", response_date=" + response_date + ", response_status=" + response_status
				+ ", response_body=" + response_body + "]";
	}

	
	
}
