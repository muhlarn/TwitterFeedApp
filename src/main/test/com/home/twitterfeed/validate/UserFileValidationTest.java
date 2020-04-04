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

import com.home.twitterfeed.validate.impl.UserFileValidator;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class UserFileValidationTest extends TestCase {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserFileValidationTest.class);

	@InjectMocks
	private UserFileValidator userValidator;
	
	//File mockedFile;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockedFile = File.createTempFile("tmp", null);
	}

	@After
	public void clear() throws Exception {
		super.tearDown();
	}

	@Test
	public void testReadUserFileWhenLineHasLessThanThreeWords() {
		try {
			// IF
			String mockedUserLine = "Ward follows ";

			// WHEN
			boolean isFileValid = userValidator.isFileLineValid(mockedUserLine);

			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenLineHasThreeWords() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan";
			
			// WHEN
			boolean isFileValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenSecondWordIsNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward talks Alan";
			
			// WHEN
			boolean isFileValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenThirdWordIsNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan Martin";
			
			// WHEN
			boolean isLineValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenThirdWordIsCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin";
			
			// WHEN
			boolean isLineValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertTrue(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenWordAfterThirdWordAreCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin, John";
			
			// WHEN
			boolean isLineValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertTrue(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenWordAfterThirdWordAreNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin John";
			
			// WHEN
			boolean isLineValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testReadUserFileWhenLastWordHasComma() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin, John,";
			
			// WHEN
			boolean isLineValid = userValidator.isFileLineValid(mockedUserLine);
			
			// THEN
			assertFalse(isLineValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenLineHasLessThanThreeWords() {
		try {
			// IF
			String mockedUserLine = "Ward follows ";	
			File mockedFile = new File("tmp");
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);			
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenLineHasThreeWords() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan";
			File mockedFile = new File("tmp");
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenSecondWordIsNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward talks Alan";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenThirdWordIsNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan Martin";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenThirdWordIsCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenWordAfterThirdWordAreCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin, John";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertTrue(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenWordAfterThirdWordAreNotCorrect() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin John";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenLastWordHasComma() {
		try {
			// IF
			String mockedUserLine = "Ward follows Alan, Martin, John,";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenMultipleCorrectLines() {
		try {
			// IF
			String mockedUserLine1 = "Ward follows Alan, Martin, John";
			String mockedUserLine2 = "Martin follows Alan";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine1);
			writer.write(mockedUserLine2);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testFileWhenMultipleInCorrectLines() {
		try {
			// IF
			String mockedUserLine1 = "Ward follows Alan, Martin, John";
			String mockedUserLine2 = "Martin follows Alan";
			String mockedUserLine3 = "Alan tweets Alan";
			File mockedFile = File.createTempFile("tmp", null);
			BufferedWriter writer = new BufferedWriter(new FileWriter(mockedFile));
			writer.write(mockedUserLine1);
			writer.write(mockedUserLine2);
			writer.write(mockedUserLine3);
			writer.close();
			
			// WHEN
			boolean isFileValid = userValidator.isFileValid(mockedFile);
			mockedFile.delete();
			
			// THEN
			assertFalse(isFileValid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

}
