<%@page import="com.example.andy.models.UserModel"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.example.andy.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Twitter - Profile</title>
</head>
<body>
	<jsp:include page="page_header.jsp" />

	<div class="container">
		<%
			System.out.println("In render");
			List<TweetStore> lTweet = (List<TweetStore>) request
					.getAttribute("Tweets");

			Boolean isFollowing = (Boolean) request.getAttribute("isFollowing");
			UserStore user = (UserStore) request.getAttribute("user");
		%>
		<h2><%=user.getName()%></h2>
		<h3>
			(<%=user.getUserName()%>)
		</h3>

		<%
			if (isFollowing) {
		%>
		<form
			action="<%=request.getContextPath()%>/Unfollow/<%=user.getUserName()%>"
			method="get">
			<button type="submit" name="unfollowBtn" value="unfollow"
				class="btn btn-danger">Unfollow</button>
		</form>
		<%
			} else {
		%>
		<form
			action="<%=request.getContextPath()%>/Follow/<%=user.getUserName()%>"
			method="get">
			<button type="submit" name="followBtn" value="follow"
				class="btn btn-primary">Follow</button>
		</form>

		<%
			}%>
			
		<br>

		<h4><u><b>Tweets</b></u></h4>
		
		<%
			if (lTweet.isEmpty()) {
		%>

		<p>No Tweets found</p>

		<%
			} else {
		%>


		<%
			Iterator<TweetStore> iterator;

				iterator = lTweet.iterator();
				while (iterator.hasNext()) {
					TweetStore ts = (TweetStore) iterator.next();
		%>
		<h5>
			<%=ts.getUserName()%>:
			<%=ts.getTweetBody()%>
		</h5>
		<h6>
			On:
			<%=ts.getDateTime()%></h6>
		<hr>
		<%
			}
			}
		%>
	</div>
</body>
</html>