package com.home.twitterfeed.validate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.validate.impl.TweetFileValidator;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TweetFileValidationTest extends TestCase {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TweetFileValidationTest.class);

	@InjectMocks
	private TweetFileValidator tweetFileValidator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void clear() throws Exception {
		super.tearDown();
	}

	@Test
	public void testValidateFileLineWhenLineEquals140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> tweet is going to be more equal 140 characters because we are testing it."
					+ "The hope is that the validator will noteive that we followed";

			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);

			// THEN
			assertTrue(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenLineLessThan140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> This tweet is going to be less than 140 characters because we are testing it. ";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertTrue(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenLineExceeds140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> This tweet is going to be more than 140 characters because we are testing it. "
					+ "The hope is that the validator will catch it and respond accordings";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenLineIsWellFormed() {
		try {
			// IF
			String mockedTweetLine = "Alan> If you have a procedure with 10 parameters, you probably missed some.";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertTrue(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenLineIsNotWellFormed() {
		try {
			// IF
			String mockedTweetLine = "Alan If you have a procedure with 10 parameters, you probably missed some.";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenNoSpaceAfterGreaterSign() {
		try {
			// IF
			String mockedTweetLine = "Alan>If you have a procedure with 10 parameters, you probably missed some.";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileLineWhenNoTweetPosted() {
		try {
			// IF
			String mockedTweetLine = "Alan> ";
			
			// WHEN
			boolean isLineValid = tweetFileValidator.isFileLineValid(mockedTweetLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenLineEquals140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> tweet is going to be more equal 140 characters because we are testing it."
					+ "The hope is that the validator will noteive that we followed";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenLineLessThan140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> This tweet is going to be less than 140 characters because we are testing it. ";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenLineExceeds140Character() {
		try {
			// IF
			String mockedTweetLine = "Alan> This tweet is going to be more than 140 characters because we are testing it. "
					+ "The hope is that the validator will catch it and respond accordings";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenLineIsWellFormed() {
		try {
			// IF
			String mockedTweetLine = "Alan> If you have a procedure with 10 parameters, you probably missed some.";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenLineIsNotWellFormed() {
		try {
			// IF
			String mockedTweetLine = "Alan If you have a procedure with 10 parameters, you probably missed some.";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenNoSpaceAfterGreaterSign() {
		try {
			// IF
			String mockedTweetLine = "Alan>If you have a procedure with 10 parameters, you probably missed some.";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateFileWhenNoTweetPosted() {
		try {
			// IF
			String mockedTweetLine = "Alan> ";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateWhenHasMultipleCorrectLines() {
		try {
			// IF
			String mockedTweetLine1 = "Alan>If you have a procedure with 10 parameters, you probably missed some.";
			String mockedTweetLine2 = "Ward> There are only two hard things in Computer Science: cache invalidation, "
					+ "naming things and off-by-1 errors.";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine1);
			writer.write(mockedTweetLine2);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testValidateWhenHasMultipleInCorrectLines() {
		try {
			// IF
			String mockedTweetLine1 = "Alan>If you have a procedure with 10 parameters, you probably missed some.";
			String mockedTweetLine2 = "Alan If you have a procedure with 10 parameters, you probably missed some.";
			String mockedTweetLine3 = "Ward> There are only two hard things in Computer Science: cache invalidation, "
					+ "naming things and off-by-1 errors.";
			
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedTweetLine1);
			writer.write(mockedTweetLine2);
			writer.write(mockedTweetLine3);
			writer.close();
			
			// WHEN
			boolean isFileValid = tweetFileValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	

}
