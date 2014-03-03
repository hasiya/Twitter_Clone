/**
 * 
 */
package com.example.andy.models;

/**
 * @author Rajitha Hasith
 *
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.apache.cassandra.thrift.Cassandra.AsyncProcessor.system_add_column_family;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.andy.stores.FollowStore;
import com.example.andy.stores.TweetStore;
import com.example.andy.stores.UserStore;;

public class UserModel {
	Cluster cluster;
	public UserModel(){

	}

	public void setCluster(Cluster cluster){
		this.cluster=cluster;
	}

	public void RegisterUser(UserStore user)
	{
		Boolean register = false;
		Session session = cluster.connect("tclone");
		PreparedStatement statement = session.prepare("insert into users (id, user_name, email, gender, name, password) values (now(), ?, ?, ?, ?, ?);");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(user.getUserName(), user.getEmail(), user.getGender(), user.getName(), user.getPassword()); 

		session.execute(boundStatement);
		if(session.execute(boundStatement).isFullyFetched());
		{
			register = true;
		}
	}
	
	public LinkedList<UserStore> getUsers() {
		LinkedList<UserStore> userList = new LinkedList<UserStore>();
		Session session = cluster.connect("tclone");

		PreparedStatement statement = session.prepare("SELECT * from users");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Users returned");
		} else {
			for (Row row : rs) {
				UserStore us = new UserStore();
				us.setId(row.getUUID("id"));
				us.setUserName(row.getString("user_name"));
				us.setPassword(row.getString("password"));
				us.setName(row.getString("name"));
				us.setEmail(row.getString("email"));
				us.setGender(row.getString("gender"));
				userList.add(us);
			}
		}
		session.close();
		return userList;
	}
	
	public UserStore getUser(String userName){
		
		Session session = cluster.connect("tclone");
		UserStore user_store = new UserStore();
		
		PreparedStatement statement = session.prepare("SELECT * from users where user_name = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(userName);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No User returned");
		} else {
			for (Row row : rs) {
				
				user_store.setId(row.getUUID("id"));
				user_store.setUserName(row.getString("user_name"));
				user_store.setPassword(row.getString("password"));
				user_store.setName(row.getString("name"));
				user_store.setEmail(row.getString("email"));
				user_store.setGender(row.getString("gender"));
				
				System.out.println("Getting result");
				System.out.println(user_store.getName());
			//	userList.add(us);
			}
		}
		session.close();
		
		return user_store;
	}
	
	
	public LinkedList<UserStore> searchUser(String searchName) {
		LinkedList<UserStore> userList = new LinkedList<UserStore>();
		Session session = cluster.connect("tclone");

		PreparedStatement statement = session.prepare("SELECT * from users where name = ?");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(searchName);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Users returned");
		} else {
			for (Row row : rs) {
				UserStore us = new UserStore();
				us.setId(row.getUUID("id"));
				us.setUserName(row.getString("user_name"));
				us.setPassword(row.getString("password"));
				us.setName(row.getString("name"));
				us.setEmail(row.getString("email"));
				us.setGender(row.getString("gender"));
				userList.add(us);
			}
		}
		session.close();
		return userList;
	}
	
	
	public void followUser(String User, String Follower)
	{
		Boolean register = false;
		Session session = cluster.connect("tclone");
		PreparedStatement statement = session.prepare("insert into followers(id, user_name, follow_user) values (now(), ?, ?);");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(User, Follower); 

		session.execute(boundStatement);
		if(session.execute(boundStatement).isFullyFetched());
		{
			register = true;
		}
	}
	
	public void unfollowUser(String User, String Follower)
	{
		TweetModel tm = new TweetModel();
		tm.setCluster(cluster);
		
		LinkedList<FollowStore> followList = new LinkedList<FollowStore>();
		
		Session session = cluster.connect("tclone");

		PreparedStatement statement = session
				.prepare("SELECT * from followers where user_name= ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement.bind(User);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out
					.println("No Followers Returned - Tweet model(GetFollowersList()");
		} else {
			for (Row row : rs) {
				FollowStore fs = new FollowStore();
				fs.setId(row.getUUID("id"));
				fs.setUserName(row.getString("user_name"));
				fs.setFollowerName(row.getString("follow_user"));
				followList.add(fs);
				System.out.println(row.getString("user_name")
						+ " - Tweet model(GetFollowersList()");
			}
		}
		
		Iterator<FollowStore> iterator = followList.iterator();
		
		while(iterator.hasNext()){
			FollowStore fs = (FollowStore)iterator.next();
			
			if(Follower.equals(fs.getFollowerName())){
				System.out.println("unfollowing");
				statement = session.prepare("delete from followers where id = ?");
				boundStatement = new BoundStatement(statement);

				boundStatement.bind(fs.getId()); 

				session.execute(boundStatement);
				System.out.println("unfollowed");
				
			}			
		}	
		session.close();
		
	
		/*if(FollowersList.contains(Follower)){
			System.out.println("is following");
		}
		else {
			System.out.println("is not following");
		}
		
		Boolean register = false;
		Session session = cluster.connect("keyspace2");
		PreparedStatement statement = session.prepare("delete from followers where user_name = ? and follow_user = ?");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(User,Follower); 

		session.execute(boundStatement);
		if(session.execute(boundStatement).isFullyFetched());
		{
			register = true;
		}*/
	}
	
	
	
	public Boolean isFollowing(String logedUser, String otherUser){
		TweetModel tm = new TweetModel();
		tm.setCluster(cluster);
		ArrayList<String> FollowersList = tm.getFollowersList(logedUser);
		
		if(FollowersList.contains(otherUser)){
			System.out.println("is following");
			return true;
		}
		else {
			System.out.println("is not following");
			return false;
		}
		
		
		
	}
	
	
}