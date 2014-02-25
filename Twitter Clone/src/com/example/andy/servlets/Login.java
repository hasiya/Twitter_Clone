package com.example.andy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.datastax.driver.core.Cluster;
import com.example.andy.lib.*;
import com.example.andy.models.*;
import com.example.andy.stores.*;
/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = { "/Home" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		
		UserModel um = new UserModel();
		um.setCluster(cluster);
		LinkedList<UserStore> userList = um.getUsers();
		String username_txt = request.getParameter("usernameTxt");
		String password_txt = request.getParameter("passwordTxt");
		
		if(checkLogin(userList, username_txt, password_txt)){
			System.out.println("check success");
			//response.setContentType("text/html");
			UserStore uStore = um.getUser(username_txt);
			
			System.out.println();
			System.out.println(uStore.getName());
			System.out.println();
			
			TweetModel tm= new TweetModel();
			tm.setCluster(cluster);
			LinkedList<TweetStore> tweetList = tm.getFollowTweets(uStore.getUserName());
			
			LinkedList<TweetStore> sortedtTweetList =  sortTweets(tweetList);
			
			session.setAttribute("user", uStore);
			
			request.setAttribute("Tweets", sortedtTweetList); //Set a bean with the list in it
			
			
			RequestDispatcher dispatcher =  request.getRequestDispatcher("RenderTweets.jsp");
			dispatcher.forward(request, response);
			
			
			//PrintWriter out = response.getWriter();
			//out.println("<font size='6' color=\"green\">Loggin Success</font>");
		}
		else {

			System.out.println("check not success");
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			out.println("<font size='6' color=\"red\">Loggin Unsuccess</font>");
		}
	}	
	
	protected Boolean checkLogin(LinkedList<UserStore> uList, String username, String password){
		Boolean userName_check = false;
		Boolean password_check = false;
		
		Iterator<UserStore> iterator = uList.iterator();
		//System.out.println(uList.get(0).getName());
		System.out.println(username);
		System.out.println(password);
		
		
		while (iterator.hasNext()){
			UserStore uStore = (UserStore)iterator.next();
			
			System.out.println();
			System.out.println(uStore.getUserName());
			
			if(username.equals(uStore.getUserName())){
				userName_check = true;
				System.out.println(uStore.getUserName() + " Checked");
				
				if(password.equals(uStore.getPassword())){
					password_check = true;
					System.out.println(uStore.getPassword() + " Checked");
				}
				else{
					password_check = false;
					System.out.println("Password Fail");
				}
			}
			else{
				userName_check = false;
				System.out.println("Username Fail");
			}
			
			if((password_check) && (userName_check))
				break;
		}
		
		if((password_check) && (userName_check))
			return true;
		else 
			return false;	

			
	}
	
	public LinkedList<TweetStore> sortTweets(LinkedList<TweetStore> tweets){
			
		LinkedList<TweetStore> SortedTweets = new LinkedList<>();
		
		 System.out.println("in the Sort Method.");
		
		
		Collections.sort(tweets, new Comparator<TweetStore>(){
			@Override
			public int compare(TweetStore tweets1, TweetStore tweets2){				
				return tweets2.getDateTime().compareTo(tweets1.getDateTime());				
			}
			
		});
		
		System.out.println(tweets.size());
		for(int i = 0; i < tweets.size(); i++){
			TweetStore tmpTweet = tweets.get(i);
			System.out.println(tmpTweet.getTweetBody());
			SortedTweets.add(tmpTweet);
			//System.out.println(SortedTweets.get(i).getTweetBody());
		}
			
			System.out.println("in the for loop");
			//System.out.println(Arrays.toString(SortedTweets.toArray()));
			
		
		
		return SortedTweets;
	}

}
