package com.home.twitterfeed.facade;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.model.Tweet;
import com.home.twitterfeed.model.User;
import com.home.twitterfeed.service.TweetService;
import com.home.twitterfeed.service.UserService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TweeterFeedFacadeTest extends TestCase {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TweeterFeedFacadeTest.class);

	@InjectMocks
	private TweeterFeedFacade tweeterFacade;
	
	@Mock
	private UserService userService;

	@Mock
	private TweetService tweetService;
	
	File mockedUserFile;
	File mockedTweetFile;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockedUserFile = File.createTempFile("tmp1", null);
		mockedTweetFile = File.createTempFile("tmp2", null);

	}

	@After
	public void clear() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetUserForSingleLine() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin";
			List<User> mockedUsers = new ArrayList<User>();
			User mockedUser = new User();
			Set<String> following = new HashSet<String>();
			following.add("Martin");
			mockedUser.setFollowing(following );
			mockedUser.setName("Alan");
			
			mockedUsers.add(mockedUser);
			
			when(userService.getUsers(eq(mockedUserLine))).thenReturn(mockedUsers);

			// WHEN
			List<User> result = tweeterFacade.getUser(mockedUserLine);

			// THEN
			assertEquals("Alan", result.get(0).getName());
			assertEquals("Martin", result.get(0).getFollowing().iterator().next());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetTweetForSingleLine() {
		try {
			// IF
			String mockedTweetLine = "Alan> This is the first tweet from Alan the man!";
			Tweet mockedTweet = new Tweet();
			mockedTweet.setMessage(mockedTweetLine.replace("Alan> ",  ""));
			mockedTweet.setName("Alan");
			
			when(tweetService.getTweet(eq(mockedTweetLine))).thenReturn(mockedTweet);
			
			// WHEN
			Tweet result = tweeterFacade.getTweet(mockedTweetLine);
			
			// THEN
			assertEquals("Alan", result.getName());
			assertEquals(mockedTweet.getMessage(), result.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
}
