package com.home.twitterfeed.service;

import com.home.twitterfeed.model.Tweet;
import com.home.twitterfeed.util.TweetUtil;
import com.home.twitterfeed.validate.FileValidator;

public class TweetService {

	private FileValidator validator;
	
	public TweetService(FileValidator validator) {
		this.validator = validator;
	}
	
	public Tweet getTweet(String message) {
		Tweet tweet = null; 
		if(validator.isFileLineValid(message)) {	
			tweet = TweetUtil.getTweet(message);
		}
		
		return tweet;
	}

}

