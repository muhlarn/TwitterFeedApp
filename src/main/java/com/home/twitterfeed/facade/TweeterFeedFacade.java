package com.home.twitterfeed.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.model.Tweet;
import com.home.twitterfeed.model.User;
import com.home.twitterfeed.service.TweetService;
import com.home.twitterfeed.service.UserService;
import com.home.twitterfeed.util.DisplayUtil;
import com.home.twitterfeed.util.TweetUtil;
import com.home.twitterfeed.util.UserUtil;
import com.home.twitterfeed.validate.impl.TweetFileValidator;
import com.home.twitterfeed.validate.impl.UserFileValidator;

public class TweeterFeedFacade {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TweeterFeedFacade.class);
	private UserService userService;
	private TweetService tweetService;
	
	
	public TweeterFeedFacade(UserService userService, TweetService tweetService) {
		this.userService = userService;
		this.tweetService = tweetService;
	}

	public List<User> getUser(String userLine) {
		List<User> users = userService.getUsers(userLine);
		
		return users;
	}

	public Tweet getTweet(String tweetLine) {
		Tweet tweet = tweetService.getTweet(tweetLine);
		
		return tweet;
	}

	public boolean displayTweets(String fileFolder) {
		boolean isSuccessful = false;
		UserFileValidator userValidator = new UserFileValidator();
		TweetFileValidator tweetValidator = new TweetFileValidator();
		
		File userFile = UserUtil.getUserFile(fileFolder);
		File tweetFile = TweetUtil.getTweetFile(fileFolder);
		
		if(userValidator.isFileValid(userFile) && tweetValidator.isFileValid(tweetFile)) {		
			try {
				List<List<User>> userList = new ArrayList<List<User>>();
				List<Tweet> tweetList = new ArrayList<Tweet>();
				for (String userLine : FileUtils.readLines(userFile, "UTF-8")) {
					userList.add(userService.getUsers(userLine));
				}
				for (String tweetLine : FileUtils.readLines(tweetFile, "UTF-8")) {
					tweetList.add(tweetService.getTweet(tweetLine));
				}
				
				DisplayUtil.showTweeterFeed(userList, tweetList);
				isSuccessful = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			LOGGER.info("Either User File or Tweet File is not valid!");
		}
		
		
		return isSuccessful;
	}


}
