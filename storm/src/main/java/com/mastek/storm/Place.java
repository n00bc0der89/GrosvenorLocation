package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

	@JsonProperty("place_type")
	private String placeType;
	
	@JsonProperty("name")
	private String placeName;
	
	@JsonProperty("full_name")
	private String placeFullName;
	
	@JsonProperty("country_code")
	private String placeCountryCode;
	
	@JsonProperty("country")
	private String placeCountry;
	
	@JsonProperty("bounding_box")
	private BoundingBox boundingBox;

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceFullName() {
		return placeFullName;
	}

	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}

	public String getPlaceCountryCode() {
		return placeCountryCode;
	}

	public void setPlaceCountryCode(String placeCountryCode) {
		this.placeCountryCode = placeCountryCode;
	}

	public String getPlaceCountry() {
		return placeCountry;
	}

	public void setPlaceCountry(String placeCountry) {
		this.placeCountry = placeCountry;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	
	
}
