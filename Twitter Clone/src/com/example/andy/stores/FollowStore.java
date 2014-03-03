package com.example.andy.stores;

import java.util.UUID;

public class FollowStore {
	
	UUID id;
	String UserName;
	String FollowerName;
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return the followerName
	 */
	public String getFollowerName() {
		return FollowerName;
	}
	/**
	 * @param followerName the followerName to set
	 */
	public void setFollowerName(String followerName) {
		FollowerName = followerName;
	}

	
	
	
}
