package com.home.twitterfeed.validate.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.validate.FileValidator;

public class UserFileValidator implements FileValidator {

	private static Logger LOGGER = LoggerFactory.getLogger(UserFileValidator.class);

	@Override
	public boolean isFileLineValid(String userLine) {
		boolean isLineValid = true;
		if(StringUtils.split(userLine, " ").length < 3) {
			return false;
		}
		
		if(StringUtils.split(userLine, " ").length == 3) {
			isLineValid = true;
		}
		
		if(StringUtils.split(userLine, " ").length > 3) {
			String[] users = StringUtils.split(userLine, " ");
			for(int i=2; i<users.length; i++) {
				String user = users[i];
				boolean isComma = user.endsWith(","); 
				
				if(i < users.length - 1) {
					if(!isComma ) {
						return false;
					} else {
						isLineValid = true;
					}
				} else {
					if(users[i].endsWith(",")) {
						return false;
					}
				}
			}
			
			
		}		
		
		return isLineValid;
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
