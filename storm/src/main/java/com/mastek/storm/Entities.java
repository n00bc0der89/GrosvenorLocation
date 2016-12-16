package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {

	@JsonProperty("hashtags")
	private HashTag hashtags[];
	
	@JsonProperty("urls")
	private EntityUrl[] urls ;

	@JsonProperty("user_mentions")
	private UserMention[] userMentions ;

	public HashTag[] getHashtags() {
		return hashtags;
	}

	public void setHashtags(HashTag[] hashtags) {
		this.hashtags = hashtags;
	}

	public EntityUrl[] getUrls() {
		return urls;
	}

	public void setUrls(EntityUrl[] urls) {
		this.urls = urls;
	}

	public UserMention[] getUserMentions() {
		return userMentions;
	}

	public void setUserMentions(UserMention[] userMentions) {
		this.userMentions = userMentions;
	}


	
	
}
