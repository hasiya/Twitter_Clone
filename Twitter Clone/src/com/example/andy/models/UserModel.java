/**
 * 
 */
package com.example.andy.models;

/**
 * @author Rajitha Hasith
 *
 */
import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.andy.stores.UserStore;;

public class UserModel {
	Cluster cluster;
	public UserModel(){

	}

	public void setCluster(Cluster cluster){
		this.cluster=cluster;
	}

	public LinkedList<UserStore> getUsers() {
		LinkedList<UserStore> userList = new LinkedList<UserStore>();
		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session.prepare("SELECT * from users");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Tweets returned");
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
		
		Session session = cluster.connect("keyspace2");
		UserStore user_store = new UserStore();
		
		PreparedStatement statement = session.prepare("SELECT * from users where user_name = \'"+userName+"\';");
		BoundStatement boundStatement = new BoundStatement(statement);
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
	
}