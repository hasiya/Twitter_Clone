package com.example.andy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.mbeans.UserMBean;

import com.datastax.driver.core.Cluster;
import com.example.andy.lib.CassandraHosts;
import com.example.andy.models.TweetModel;
import com.example.andy.models.UserModel;
import com.example.andy.stores.TweetStore;
import com.example.andy.stores.UserStore;

/**
 * Servlet implementation class users
 */
@WebServlet({ "/Profile/*" })
public class users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	

	int R = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in the user servlet");
		String[] url = request.getRequestURI().split("/");
		
		if(url[2].equals("Profile"))
			showUserPrfile(request, response);
		
		

		/*
		 * System.out.println(user.getName());
		 * out.println("<font size='6' color=\"red\">Ta dah! <br>"+
		 * user.getId()+"<br>" + user.getName()username + "</font>"); if
		 * (url.length > 2) out.println("<font size='6' color=\"red\">"+
		 * url[3]+"<br></font>");
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserStore user = (UserStore) request.getSession().getAttribute("user");

		if (!(request.getParameter("tweet").isEmpty()) || request.getParameter("tweet") == null) {

			TweetModel tm = new TweetModel();
			tm.setCluster(cluster);
			TweetStore ts = new TweetStore();
			ts.setUserName(user.getUserName());
			ts.setTweetBody(request.getParameter("tweet"));

			tm.postTweet(ts);
			
			
		}
		this.doGet(request, response);
	}

	protected void showUserPrfile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserStore logedUser = (UserStore)session.getAttribute("user");
		
		String[] url = request.getRequestURI().split("/");
		String userName = url[(url.length)-1];		

		UserModel um = new UserModel();
		um.setCluster(cluster);
		
		UserStore user = um.getUser(userName);
		
		System.out.println("in the user servlet - got user? " + userName);		

		TweetModel tm = new TweetModel();
		tm.setCluster(cluster);

		LinkedList<TweetStore> tweetList = tm.getTweets(user.getUserName());

		System.out.println("in the user servlet - got user tweets?");

		LinkedList<TweetStore> sortedtTweetList = tm.sortTweets(tweetList);

		System.out.println("in the user servlet - tweets sorted?");

		request.setAttribute("Tweets", sortedtTweetList); // Set a bean with
															// the list in
															// it
		
		if (logedUser.getUserName().equals(user.getUserName())) {
			RequestDispatcher dispatcher = request
				.getRequestDispatcher("/RenderProfile.jsp");
		dispatcher.forward(request, response);
			
		}
		else {
			Boolean isFollowing = um.isFollowing(logedUser.getUserName(), user.getUserName());
			System.out.println(isFollowing);
			request.setAttribute("user", user);
			request.setAttribute("isFollowing", isFollowing);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/RenderFollowProfile.jsp");
			dispatcher.forward(request, response);
		}
	}

}
