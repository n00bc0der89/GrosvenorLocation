package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundingBox {
	@JsonProperty("coordinates")
	private Float [][][]coordinates;

	public Float[][][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Float[][][] coordinates) {
		this.coordinates = coordinates;
	}

	



}
