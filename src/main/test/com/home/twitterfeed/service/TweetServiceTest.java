package com.home.twitterfeed.service;

import static org.mockito.Mockito.when;

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
import com.home.twitterfeed.service.TweetService;
import com.home.twitterfeed.validate.impl.TweetFileValidator;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest extends TestCase {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TweetServiceTest.class);

	@InjectMocks
	private TweetService tweeterService;
	
	@Mock
	TweetFileValidator validator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void clear() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetTweet() {
		try {
			// IF
			String mockedMessage = "Alan> This is the first tweet from Alan the man!";
			Tweet mockedTweet = new Tweet();
			mockedTweet.setName("Alan");
			mockedTweet.setMessage(mockedMessage);
			
			when(validator.isFileLineValid(mockedMessage)).thenReturn(true);
			
			// WHEN
			Tweet result = tweeterService.getTweet(mockedMessage);

			// THEN
			assertEquals(mockedTweet.getMessage().replace("Alan> ", ""), result.getMessage());
			assertEquals(mockedTweet.getName(), result.getName());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
}
