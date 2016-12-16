package com.mastek.storm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Geo {
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("coordinates")
	private Float[] coordinates;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Float[] coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
