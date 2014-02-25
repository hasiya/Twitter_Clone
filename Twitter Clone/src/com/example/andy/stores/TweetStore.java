package com.example.andy.stores;

import java.util.Date;
import java.util.UUID;


public class TweetStore {
    
	UUID id;
	String tweetBody;
	Date dateTime;
	String userName;
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
	 * @return the tweetBody
	 */
	public String getTweetBody() {
		return tweetBody;
	}
	/**
	 * @param tweetBody the tweetBody to set
	 */
	public void setTweetBody(String tweetBody) {
		this.tweetBody = tweetBody;
	}
	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		
		return dateTime;
	}
	/**
	 * @param date the dateTime to set
	 */
	public void setDateTime(Date date) {
		this.dateTime = date;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}