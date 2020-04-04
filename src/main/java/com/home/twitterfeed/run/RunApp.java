package com.home.twitterfeed.run;


import com.home.twitterfeed.facade.TweeterFeedFacade;
import com.home.twitterfeed.service.TweetService;
import com.home.twitterfeed.service.UserService;
import com.home.twitterfeed.validate.FileValidator;
import com.home.twitterfeed.validate.impl.TweetFileValidator;
import com.home.twitterfeed.validate.impl.UserFileValidator;

public class RunApp {

	public static void main(String[] args) {
		
		String folder = args[0];
		FileValidator userValidator = new UserFileValidator();
		FileValidator tweetValidator = new TweetFileValidator();
		
		UserService userService = new UserService(userValidator);
		TweetService tweetService = new TweetService(tweetValidator);
		
		TweeterFeedFacade tweetFeed = new TweeterFeedFacade(userService, tweetService);
		tweetFeed.displayTweets(folder);

	}

}
