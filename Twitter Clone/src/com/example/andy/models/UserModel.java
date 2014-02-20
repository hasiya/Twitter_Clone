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

import com.example.andy.lib.*;
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
}