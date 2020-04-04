package com.home.twitterfeed.validate.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.validate.FileValidator;

public class TweetFileValidator implements FileValidator {

	private static Logger LOGGER = LoggerFactory.getLogger(TweetFileValidator.class);

	@Override
	public boolean isFileLineValid(String tweetLine) {
		
		if(StringUtils.length(tweetLine) > 140) {
			return false;
		} else {
			String[] tweetUserLine = StringUtils.split(tweetLine, " ");
			if(tweetUserLine.length > 1) {				
				String user = tweetUserLine[0];
				String tweet = tweetUserLine[1];
				
				if(StringUtils.isNotEmpty(user) 
						&& StringUtils.isNotEmpty(tweet)
						&& StringUtils.endsWith(user, ">")) {
					return true;
				} else {
					return false;
				}
				
			} else {
				return false;
			}
		}
		
	}

	@Override
	public boolean isFileValid(File file) {
		boolean isValidFile = false;
		try {
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			for(String line : lines) {
				isValidFile = isFileLineValid(line);
				if(!isValidFile) {
					break;
				}
			}
		} catch (IOException e) {
			LOGGER.error("File " + file.getAbsolutePath() + " could not be found!");
		}
		
		return isValidFile;
	}

}
