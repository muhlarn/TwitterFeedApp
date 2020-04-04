package com.home.twitterfeed.model;

import java.util.List;
import java.util.Set;

public class User implements Comparable< User > {
	
	private String name;
	private Set<String> followers;
	private Set<String> following;
	private List<Tweet> tweets;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the followers
	 */
	public Set<String> getFollowers() {
		return followers;
	}
	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(Set<String> followers) {
		this.followers = followers;
	}
	/**
	 * @return the following
	 */
	public Set<String> getFollowing() {
		return following;
	}
	/**
	 * @param following the following to set
	 */
	public void setFollowing(Set<String> following) {
		this.following = following;
	}
	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}
	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", followers=" + followers + ", following=" + following + ", tweets=" + tweets
				+ "]";
	}
	@Override
	public int compareTo(User o) {
		return this.getName().compareTo(o.getName());
	}
	
	

}
