package com.home.twitterfeed.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.home.twitterfeed.model.Tweet;

public class TweetUtil {

	public static Tweet getTweet(String message) {
		Tweet tweet = new Tweet();
		String name = message.split(">")[0];
		String tweetMessage = message.split(">")[1].trim();			
		tweet.setName(name);
		tweet.setMessage(tweetMessage);		
		
		return tweet;
	}
	
	public static File getTweetFile(String folder) {
		return FileUtils.getFile(folder + "/tweet.txt");
	}

}
