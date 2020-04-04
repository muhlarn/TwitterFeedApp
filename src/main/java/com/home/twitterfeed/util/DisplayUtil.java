package com.home.twitterfeed.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.home.twitterfeed.model.Tweet;
import com.home.twitterfeed.model.User;

public class DisplayUtil {

	public static void showTweeterFeed(List<List<User>> userList, List<Tweet> tweetList) {

		Set<String> otherUsers = new HashSet<String>();
		List<User> newUsers = new ArrayList<User>();
		for (List<User> users : userList) {
			User user = users.get(0);
			User newUser = new User();
			newUser.setName(user.getName());

			Set<String> newFollowing = new HashSet<String>();
			if (null != user.getFollowing()) {
				for (String following : user.getFollowing()) {
					newFollowing.add(following);
					otherUsers.add(following);
				}
			}
			Set<String> newFollowers = new HashSet<String>();
			if (null != user.getFollowers()) {
				for (String followers : user.getFollowers()) {
					newFollowers.add(followers);
					otherUsers.add(followers);
				}
			}

			newUser.setFollowing(newFollowing);
			newUser.setFollowers(newFollowers);
			
			UserUtil.updateExistingUser(newUsers, newUser);
			newUsers.add(newUser);
		}
		
		UserUtil.addNonFollowingUsers(otherUsers, newUsers);
		Collections.sort(newUsers);
		printToConsole(newUsers, tweetList);

	}


	/**
	 * @param tweetList
	 * @param newUsers
	 */
	private static void printToConsole(List<User> newUsers, List<Tweet> tweetList) {
		for (User user : newUsers) {
			System.out.println(user.getName());
			for (Tweet tweet : tweetList) {
				if (StringUtils.equals(user.getName(), tweet.getName())
						|| user.getFollowing().contains(tweet.getName())) {
					System.out.println("\t@" + tweet.getName() + " " + tweet.getMessage());
				}
			}
		}
	}

}
