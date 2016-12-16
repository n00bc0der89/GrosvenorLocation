package com.mastek.storm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TweetParseHelper {
	public static final String delimeter="|";
	public static final String blank="";
	public static final String[] blankArray={};
	public static final char starCharacter='*';   
	public static String createObjectRecord(TweetData obj) throws ParseException {
		StringBuilder  record=new StringBuilder("");
		
		if(obj!=null){
			User objUser=obj.getUser();
			Place objPlace=obj.getPlace();
			Entities objEntities=obj.getEntities();
			record.append(obj.getTweetId().toString());record.append(delimeter);
			record.append(parseTwitterUTC(obj.getCreatedAt()));record.append(delimeter);
			if(obj.getTweet() !=null && !("".equals(obj.getTweet()))){
                record.append(obj.getTweet().replace(delimeter.charAt(0), starCharacter));record.append(delimeter);
	          }
	          else{
	                record.append(obj.getTweet());record.append(delimeter);
	          }

			record.append(obj.getSource());record.append(delimeter);			
			record.append(obj.getInRelyToStatusId());record.append(delimeter);
			record.append(obj.getInReplyToUserId());record.append(delimeter);
			record.append(obj.getInReplyToUserName());record.append(delimeter);
			if(objUser !=null){
				record.append(objUser.getUserId());record.append(delimeter);
				record.append(objUser.getUserFullName());record.append(delimeter);
				record.append(objUser.getUserScreenName());record.append(delimeter);
				record.append(objUser.getUserLocation());record.append(delimeter);
				record.append(objUser.getUserDescription());record.append(delimeter);
				record.append(objUser.getUserURL());record.append(delimeter);
				record.append(objUser.isUserProtected());record.append(delimeter);
				record.append(objUser.isUserVerified());record.append(delimeter);
				record.append(objUser.getUserFollowerCount());record.append(delimeter);
				record.append(objUser.getUserFollowingCount());record.append(delimeter);
				record.append(objUser.getUserStatusCount());record.append(delimeter);
				record.append(objUser.getUserFriendsCount());record.append(delimeter);
				record.append(objUser.getUserListedCount());record.append(delimeter);
				record.append(objUser.getUserFavouriteCount());record.append(delimeter);
				record.append(parseTwitterUTC(objUser.getUserCreatedAt()));record.append(delimeter);
				record.append(objUser.isUserGeoEnabled());record.append(delimeter);
				record.append(objUser.getUserLanguage());record.append(delimeter);
				record.append(objUser.getUserProfileImageURL());record.append(delimeter);
				record.append(objUser.isUserDefaultProfile());record.append(delimeter);
				record.append(objUser.isUserDefaultProfileImage());record.append(delimeter);
			}
			
			
			if(obj.getGeo() !=null){
				record.append(Arrays.toString(obj.getGeo().getCoordinates()));record.append(delimeter);
			}
			else{
				record.append(Arrays.toString(blankArray));record.append(delimeter);
			}
			if(obj.getCoordinates() !=null){
				record.append(Arrays.toString(obj.getCoordinates().getCoordinates()));record.append(delimeter);
			}
			else{
				record.append(Arrays.toString(blankArray));record.append(delimeter);
			}
			record.append(obj.getRetweetCount());record.append(delimeter);
			record.append(obj.getFavoriteCount());record.append(delimeter);
			record.append(obj.isRetweeted());record.append(delimeter);
			record.append(obj.isFavorited());record.append(delimeter);
			record.append(obj.getFilterLevel());record.append(delimeter);
			record.append(obj.getLang());record.append(delimeter);
			if(objPlace !=null){
	
				record.append(objPlace.getPlaceType());record.append(delimeter);
				record.append(objPlace.getPlaceName());record.append(delimeter);
				record.append(objPlace.getPlaceFullName());record.append(delimeter);
				record.append(objPlace.getPlaceCountryCode());record.append(delimeter);
				record.append(objPlace.getPlaceCountry());record.append(delimeter);
				BoundingBox objBoundingBox=objPlace.getBoundingBox();
				if(objBoundingBox !=null){
					if(objBoundingBox.getCoordinates()!=null){
					record.append(Arrays.deepToString(objBoundingBox.getCoordinates()));record.append(delimeter);
					}
					else{
						record.append(Arrays.toString(blankArray));record.append(delimeter);
					}
						
				}
				else{
					record.append(Arrays.toString(blankArray));record.append(delimeter);
				}
				
			}
			else{
				record.append(blank);record.append(delimeter);
				record.append(blank);record.append(delimeter);
				record.append(blank);record.append(delimeter);
				record.append(blank);record.append(delimeter);
				record.append(blank);record.append(delimeter);
				record.append(Arrays.toString(blankArray));record.append(delimeter);
			}
			if(objEntities !=null){
				HashTag[] hashTags=objEntities.getHashtags();
				UserMention[] userMentions=objEntities.getUserMentions();
				EntityUrl[] urls=objEntities.getUrls();
				if(hashTags!=null && hashTags.length>0){
					String tags[]=buildHashtagArray(hashTags);
					record.append(Arrays.toString(tags));record.append(delimeter);
				}
				else{
					record.append(Arrays.toString(hashTags));record.append(delimeter);
				}
				if(userMentions!=null && userMentions.length>0){
					String mentions[]=buildUserMentionArray(userMentions);
					record.append(Arrays.toString(mentions));record.append(delimeter);
				}
				else{
					record.append(Arrays.toString(userMentions));record.append(delimeter);
				}
				
				if(urls!=null && urls.length>0){
					String urlArray[]=buildUrlsArray(urls);
					record.append(Arrays.toString(urlArray));
				}
				else{
					record.append(Arrays.toString(urls));
				}
			}
			else{
				record.append(Arrays.toString(blankArray));record.append(delimeter);
				record.append(Arrays.toString(blankArray));record.append(delimeter);
				record.append(Arrays.toString(blankArray));;
			}
		}
		return record.toString();
	}

	public static String[] buildHashtagArray(HashTag[] hashTags) {
		String out[]=new String[hashTags.length];
		for(int x=0; x<hashTags.length;x++){
			out[x]=(hashTags[x]).getText();
		}
		return out;
	}
	public static String[] buildUserMentionArray(UserMention[] userMentions) {
		String out[]=new String[userMentions.length];
		for(int x=0; x<userMentions.length;x++){
			out[x]=(userMentions[x]).getName();
		}
		return out;
	}
	public static String[] buildUrlsArray(EntityUrl[] entityUrls) {
		String out[]=new String[entityUrls.length];
		for(int x=0; x<entityUrls.length;x++){
			out[x]=(entityUrls[x]).getUrl();
		}
		return out;
	}
	public static String parseTwitterUTC(String date) throws ParseException {
		String out="";
		if(date !=null && !("".equals(date))){
           String twitterFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
           // Important note. Only ENGLISH Locale works.
            SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
            sf.setLenient(true);
            Date dt=sf.parse(date);
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            out= sdf.format(dt);
		}
		return out;
     }

	
}
