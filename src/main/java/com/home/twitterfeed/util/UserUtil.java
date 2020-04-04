package com.home.twitterfeed.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.model.User;

public class UserUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);

	public static List<User> getUsersFromLine(String userLine) {
		List<User> userList = new ArrayList<User>();
		String[] users = userLine.split("follows");
		for (String userStr : users) {
			String[] seperateUser = userStr.split(",");
			for (String user : seperateUser) {
				User user_obj = new User();
				user_obj.setName(user.trim());
				userList.add(user_obj);
			}
		}

		return userList;
	}

	public static void setUserFollowing(List<User> users, String userLine) {
		String following = userLine.split("follows")[1];
		String[] userList = following.split(",");
		Set<String> followingList = new HashSet<String>();

		for (String follow : userList) {
			followingList.add(follow.trim());
		}
		users.get(0).setFollowing(followingList);
	}

	public static void setUserFollowers(List<User> users, String userLine) {
		String followedList = userLine.split("follows")[1];
		String follower = userLine.split("follows")[0];
		String[] userList = followedList.split(",");
		for(String followed : userList) {
			users.forEach(user_obj -> {
				if (StringUtils.equalsAnyIgnoreCase(user_obj.getName(), followed.trim())) {
					Set<String> followersList = new HashSet<String>();
					followersList.add(follower.trim());
					user_obj.setFollowers(followersList);
				}
			});				
		}
		
	}

	public static File getUserFile(String folder) {
		return FileUtils.getFile(folder + "/user.txt");
	}


	/**
	 * @param otherUsers
	 * @param newUsers
	 */
	public static void addNonFollowingUsers(Set<String> otherUsers, List<User> newUsers) {
		for(String user : otherUsers) {
			User otherUser = new User();
			otherUser.setFollowers(new HashSet<String>());
			otherUser.setFollowing(new HashSet<String>());
			otherUser.setName(user);
			if(!newUsers.contains(otherUser)) {
				newUsers.add(otherUser);
			}
		}
	}
	
	/**
	 * @param newUsers
	 * @param newUser
	 */
	public static void updateExistingUser(List<User> newUsers, User newUser) {
		if(newUsers.contains(newUser)) {
			newUsers.remove(newUser);
		}
	}

}
