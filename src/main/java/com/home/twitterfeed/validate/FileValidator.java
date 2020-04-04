package com.home.twitterfeed.validate;

import java.io.File;

public interface FileValidator {
	
	public boolean isFileLineValid(String line);
	public boolean isFileValid(File file);
}
