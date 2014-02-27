package com.example.andy.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.andy.stores.TweetStore;

public class TweetModel {
	Cluster cluster;

	public TweetModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	
	public void postTweet(TweetStore ts)
	{
		Session session = cluster.connect("keyspace2");
		PreparedStatement statement = session.prepare("insert into tweets (id, user_name, body, date_time) values (now(), ?, ?, dateof(now()));");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(ts.getUserName(), ts.getTweetBody());

		session.execute(boundStatement);
		session.close();
	}
	
	public LinkedList<TweetStore> getFollowTweets(String userName) {
		LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
		Session session = cluster.connect("keyspace2");

		ArrayList<String> Followers = new ArrayList<String>();
		Followers = getFollowersList(userName);

		for (int i = 0; i < Followers.size(); i++) {
			String user_name = Followers.get(i);

			PreparedStatement statement = session
					.prepare("SELECT * from tweets where user_name = ?;");
			BoundStatement boundStatement = new BoundStatement(statement);
			boundStatement.bind(user_name);
			ResultSet rs = session.execute(boundStatement);
			if (rs.isExhausted()) {
				System.out.println("No Tweets returned");
			} else {
				for (Row row : rs) {
					TweetStore ts = new TweetStore();
					ts.setId(row.getUUID("id"));
					ts.setTweetBody(row.getString("body"));
					ts.setUserName(row.getString("user_name"));
					ts.setDateTime(row.getDate("date_time"));
					tweetList.add(ts);
					System.out.println("one Tweet returned");
				}
				System.out.println();
			}

		}
		session.close();
		return tweetList;
		

	}

	public LinkedList<TweetStore> getTweets(String userName) {
		LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session
				.prepare("SELECT * from tweets where user_name = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement.bind(userName);
		ResultSet rs = session.execute(boundStatement);
		
		if (rs.isExhausted()) {
			System.out.println("No Tweets returned");
		} else {
			for (Row row : rs) {
				TweetStore ts = new TweetStore();
				ts.setId(row.getUUID("id"));
				ts.setTweetBody(row.getString("body"));
				ts.setUserName(row.getString("user_name"));
				ts.setDateTime(row.getDate("date_time"));
				tweetList.add(ts);
				System.out.println("one Tweet returned");
			}
			System.out.println();
		}

		/*
		 * PreparedStatement statement =
		 * session.prepare("SELECT * from tweets"); BoundStatement
		 * boundStatement = new BoundStatement(statement); ResultSet rs =
		 * session.execute(boundStatement); if (rs.isExhausted()) {
		 * System.out.println
		 * ("No Tweets returned - Tweet model(getFollowTweets()"); } else { for
		 * (Row row : rs) { TweetStore ts = new TweetStore();
		 * ts.setId(row.getUUID("id")); ts.setTweetBody(row.getString("body"));
		 * ts.setUserName(row.getString("user_name"));
		 * ts.setDateTime(row.getDate("date_time")); tweetList.add(ts); } }
		 */
		session.close();
		return tweetList;
	}

	public ArrayList<String> getFollowersList(String userName) {

		ArrayList<String> FollowersList = new ArrayList<String>();

		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session
				.prepare("SELECT follow_user from followers where user_name= ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement.bind(userName);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out
					.println("No Followers Returned - Tweet model(GetFollowersList()");
		} else {
			for (Row row : rs) {

				FollowersList.add(row.getString("follow_user"));
				System.out.println(row.getString("follow_user")
						+ " - Tweet model(GetFollowersList()");
			}
		}
		session.close();
		return FollowersList;
	}

	public LinkedList<TweetStore> sortTweets(LinkedList<TweetStore> tweets) {

		LinkedList<TweetStore> SortedTweets = new LinkedList<>();

		System.out.println("in the Sort Method.");

		Collections.sort(tweets, new Comparator<TweetStore>() {
			@Override
			public int compare(TweetStore tweets1, TweetStore tweets2) {
				return tweets2.getDateTime().compareTo(tweets1.getDateTime());
			}

		});

		System.out.println(tweets.size());
		for (int i = 0; i < tweets.size(); i++) {
			TweetStore tmpTweet = tweets.get(i);
			System.out.println(tmpTweet.getTweetBody());
			SortedTweets.add(tmpTweet);
			// System.out.println(SortedTweets.get(i).getTweetBody());
		}

		System.out.println("in the for loop");
		// System.out.println(Arrays.toString(SortedTweets.toArray()));

		return SortedTweets;
	}

}