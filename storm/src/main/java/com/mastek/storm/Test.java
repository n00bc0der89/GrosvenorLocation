package com.mastek.storm;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	
	public static final String delimeter="|";
	public static final String blank="";
	public static final String[] blankArray={};

	public static void main(String[] args) throws ParseException, JsonParseException, JsonMappingException, IOException {
		String filePath="D:\\test\\message.txt";
		String test = readFile(filePath);
		System.out.println(blankArray);
	/*	String dateString="Fri Nov 25 06:18:57 +0000 2016";
		String formattedDate=parseTwitterUTC(dateString);
		System.out.println(formattedDate);*/
		convertToJSon(test);
	}

	private static void convertToJSon(String test) throws JsonParseException, JsonMappingException, IOException, ParseException {
		 
		//System.out.println("test is "+test);
		
		TweetData obj = new ObjectMapper().readValue(test, TweetData.class);
		String objectRecord=createObjectRecord(obj);
		String formattedString =objectRecord.replace("\n", "");
		System.out.println(formattedString);		
		System.out.println(calculateCount(formattedString));
	}
	private static int calculateCount(String formattedString) {
		String test[]=formattedString.split(delimeter);
		int count=0;
		for(int x=0;x<formattedString.length();x++){
			char c=formattedString.charAt(x);
			if(c=='|'){
				count++;
			}
		}
		return count;
	}

	public static String parseTwitterUTC(String date) 
			throws ParseException {
		 	String twitterFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		 	// Important note. Only ENGLISH Locale works.
			SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
			sf.setLenient(true);
			Date dt=sf.parse(date);
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		  	return sdf.format(dt);
		}
	
	private static String createObjectRecord(TweetData obj) throws ParseException {
		StringBuilder  record=new StringBuilder("");
		
		if(obj!=null){
			User objUser=obj.getUser();
			Place objPlace=obj.getPlace();
			Entities objEntities=obj.getEntities();
			record.append(obj.getTweetId().toString());record.append(delimeter);
			record.append(parseTwitterUTC(obj.getCreatedAt()));record.append(delimeter);
			record.append(obj.getTweet());record.append(delimeter);
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


	private static String[] buildHashtagArray(HashTag[] hashTags) {
		String out[]=new String[hashTags.length];
		for(int x=0; x<hashTags.length;x++){
			out[x]=(hashTags[x]).getText();
		}
		return out;
	}
	private static String[] buildUserMentionArray(UserMention[] userMentions) {
		String out[]=new String[userMentions.length];
		for(int x=0; x<userMentions.length;x++){
			out[x]=(userMentions[x]).getName();
		}
		return out;
	}
	private static String[] buildUrlsArray(EntityUrl[] entityUrls) {
		String out[]=new String[entityUrls.length];
		for(int x=0; x<entityUrls.length;x++){
			out[x]=(entityUrls[x]).getUrl();
		}
		return out;
	}
	private static String readFile(String filePath){
		BufferedReader br = null;
		String sCurrentLine="";
		String out="";
		try {
		br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				out=out+sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return out;	
	}
	
}
