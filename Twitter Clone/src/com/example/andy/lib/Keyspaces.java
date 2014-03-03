package com.example.andy.lib;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.*;

public final class Keyspaces {



	public Keyspaces(){

	}

	public static void SetUpKeySpaces(Cluster c){
		try{
			//Add some keyspaces here
		String createkeyspace="create keyspace if not exists tcolne  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
		String CreateUserTable = "CREATE TABLE if not exists users (id timeuuid, user_name varchar, password varchar, name varchar, email varchar, gender varchar, primary key (id, user_name));";
		String CreateTweetTable = "CREATE TABLE if not exists tweets (id timeuuid, body text, date_time timestamp, user_name text, PRIMARY KEY (id));";
		String CreateFollowerTable = "CREATE TABLE if not exists followers (id timeuuid, follow_user text, user_name text, PRIMARY KEY (id));";
		
		Session session = c.connect();
		try{
			PreparedStatement statement = session
					.prepare(createkeyspace);
			BoundStatement boundStatement = new BoundStatement(
					statement);
			session.execute(boundStatement);

		}catch(Exception et){
			System.out.println("Can't create keyspace2 "+et);
		}

		//now add some column families 
		session.close();
		session = c.connect("keyspace2");
		System.out.println(""+CreateTweetTable);

		try{
			SimpleStatement cqlQuery = new SimpleStatement(CreateUserTable);
			session.execute(cqlQuery);
			cqlQuery = new SimpleStatement(CreateTweetTable);
			session.execute(cqlQuery);
			cqlQuery = new SimpleStatement(CreateFollowerTable);
			System.out.println("all tables created if not exist"); 
			session.execute(cqlQuery);
		}catch(Exception et){
			System.out.println("Can't create DB "+et);
		}
		session.close();

		}catch(Exception et){
			System.out.println("Other keyspace or coulm definition error" +et);
		}

	}
}