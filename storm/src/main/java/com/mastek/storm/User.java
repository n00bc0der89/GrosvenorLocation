package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	@JsonProperty("id")
	private Long userId;
	
	@JsonProperty("name")
	private String userFullName;
	
	@JsonProperty("screen_name")
	private String userScreenName;
	
	@JsonProperty("location")
	private String userLocation;
	
	@JsonProperty("description")
	private String userDescription;

	@JsonProperty("url")
	private String userURL;
	
	@JsonProperty("protected")
	private boolean userProtected;
	
	
	@JsonProperty("verified")
	private boolean userVerified;
	
	
	@JsonProperty("followers_count")
	private Integer userFollowerCount;
	
	
	@JsonProperty("following")
	private Integer userFollowingCount;
	
	@JsonProperty("statuses_count")
	private Integer userStatusCount;
	
	
	@JsonProperty("friends_count")
	private Integer userFriendsCount;
	
	@JsonProperty("listed_count")
	private Integer userListedCount;
	
	@JsonProperty("favourites_count")
	private Integer userFavouriteCount;
	
	
	@JsonProperty("created_at")
	private String userCreatedAt;
	
	@JsonProperty("geo_enabled")
	private boolean userGeoEnabled;
	
	@JsonProperty("lang")
	private String userLanguage;
	

	@JsonProperty("profile_image_url")
	private String userProfileImageURL;
	
	@JsonProperty("default_profile")
	private boolean userDefaultProfile;
	
	@JsonProperty("default_profile_image")
	private boolean userDefaultProfileImage;
	
	public String getUserURL() {
		return userURL;
	}

	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}




	public Integer getUserFollowerCount() {
		return userFollowerCount;
	}

	public void setUserFollowerCount(Integer userFollowerCount) {
		this.userFollowerCount = userFollowerCount;
	}

	public Integer getUserFollowingCount() {
		return userFollowingCount;
	}

	public void setUserFollowingCount(Integer userFollowingCount) {
		this.userFollowingCount = userFollowingCount;
	}

	public Integer getUserStatusCount() {
		return userStatusCount;
	}

	public void setUserStatusCount(Integer userStatusCount) {
		this.userStatusCount = userStatusCount;
	}

	public Integer getUserFriendsCount() {
		return userFriendsCount;
	}

	public void setUserFriendsCount(Integer userFriendsCount) {
		this.userFriendsCount = userFriendsCount;
	}

	public Integer getUserListedCount() {
		return userListedCount;
	}

	public void setUserListedCount(Integer userListedCount) {
		this.userListedCount = userListedCount;
	}

	public Integer getUserFavouriteCount() {
		return userFavouriteCount;
	}

	public void setUserFavouriteCount(Integer userFavouriteCount) {
		this.userFavouriteCount = userFavouriteCount;
	}

	public String getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(String userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public boolean isUserGeoEnabled() {
		return userGeoEnabled;
	}

	public void setUserGeoEnabled(boolean userGeoEnabled) {
		this.userGeoEnabled = userGeoEnabled;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public String getUserProfileImageURL() {
		return userProfileImageURL;
	}

	public void setUserProfileImageURL(String userProfileImageURL) {
		this.userProfileImageURL = userProfileImageURL;
	}

	public boolean isUserDefaultProfile() {
		return userDefaultProfile;
	}

	public void setUserDefaultProfile(boolean userDefaultProfile) {
		this.userDefaultProfile = userDefaultProfile;
	}

	public boolean isUserDefaultProfileImage() {
		return userDefaultProfileImage;
	}

	public void setUserDefaultProfileImage(boolean userDefaultProfileImage) {
		this.userDefaultProfileImage = userDefaultProfileImage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public boolean isUserProtected() {
		return userProtected;
	}

	public void setUserProtected(boolean userProtected) {
		this.userProtected = userProtected;
	}

	public boolean isUserVerified() {
		return userVerified;
	}

	public void setUserVerified(boolean userVerified) {
		this.userVerified = userVerified;
	}
	


	
}
