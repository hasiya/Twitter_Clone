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
		String createkeyspace="create keyspace if not exists keyspace2  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
		String CreateTweetTable = "CREATE TABLE if not exists Tweets ("+
				"user varchar,"+
				" interaction_time timeuuid,"+
				" tweet varchar,"+
				" PRIMARY KEY (user,interaction_time)"+
				") WITH CLUSTERING ORDER BY (interaction_time DESC);";
		Session session = c.connect();
		try{
			PreparedStatement statement = session
					.prepare(createkeyspace);
			BoundStatement boundStatement = new BoundStatement(
					statement);
			ResultSet rs = session
					.execute(boundStatement);

		}catch(Exception et){
			System.out.println("Can't create keyspace2 "+et);
		}

		//now add some column families 
		session.close();
		session = c.connect("keyspace2");
		System.out.println(""+CreateTweetTable);

		try{
			SimpleStatement cqlQuery = new SimpleStatement(CreateTweetTable);
			session.execute(cqlQuery);
		}catch(Exception et){
			System.out.println("Can't create tweet table "+et);
		}
		session.close();

		}catch(Exception et){
			System.out.println("Other keyspace or coulm definition error" +et);
		}

	}
}