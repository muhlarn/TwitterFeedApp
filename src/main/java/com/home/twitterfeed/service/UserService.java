package com.home.twitterfeed.service;

import java.util.ArrayList;
import java.util.List;

import com.home.twitterfeed.model.User;
import com.home.twitterfeed.util.UserUtil;
import com.home.twitterfeed.validate.FileValidator;

public class UserService {

	private FileValidator validator;
	
	public UserService(FileValidator validator) {
		this.validator = validator;
	}
	
	public List<User> getUsers(String userLine) {
		List<User> users = new ArrayList<User>();
		if(validator.isFileLineValid(userLine)) {			
			users = UserUtil.getUsersFromLine(userLine);
			UserUtil.setUserFollowing(users, userLine);
			UserUtil.setUserFollowers(users, userLine);
		}
		
		return users;
	}

}
