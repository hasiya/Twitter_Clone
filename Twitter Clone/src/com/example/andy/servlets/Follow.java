package com.example.andy.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.example.andy.lib.CassandraHosts;
import com.example.andy.models.UserModel;
import com.example.andy.stores.UserStore;

/**
 * Servlet implementation class Follow
 */
@WebServlet({"/Follow/*","/Unfollow/*"})
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Cluster cluster;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	
	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = request.getRequestURI().split("/");
		UserStore user = (UserStore) request.getSession().getAttribute("user");
		String Follower = url[(url.length-1)];
		System.out.println("in the Follower servlet Follower:" + Follower);
		System.out.println("in the Follower servlet follow parameter:" + request.getParameter("followBtn"));
		System.out.println("in the Follower servlet follow parameter:" + request.getParameter("unfollowBtn"));
		
		UserModel um = new UserModel();
		um.setCluster(cluster);
		
		if((request.getParameter("followBtn") != (null)) && request.getParameter("followBtn").equals("follow")){
			System.out.println("Followerbtn Checked");
			um.followUser(user.getUserName(), Follower);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/Profile/"+Follower);
			dispatcher.forward(request, response);
			
		}
		else if( (request.getParameter("unfollowBtn") != (null)) && request.getParameter("unfollowBtn").equals("unfollow")){
			System.out.println("Followerbtn Checked");
			um.unfollowUser(user.getUserName(), Follower);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/Profile/"+Follower);
			dispatcher.forward(request, response);
		}
	}



	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
