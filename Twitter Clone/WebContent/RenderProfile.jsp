<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.example.andy.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Twitter - Profile</title>


<style type="text/css">
textarea {
	resize: none;
}
</style>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="css/bootstrap-theme.css" /> "/>
<link rel="stylesheet" type="text/css" href="<c:url value="css/bootstrap-theme.min.css" /> "/> --%>


</head>
<body>
	<jsp:include page="page_header.jsp" />
	<div class="container">
		<%
			UserStore user = (UserStore) session.getAttribute("user");
		%>
		<br>
		<form
			action="<%=request.getContextPath()%>/Profile/<%=user.getUserName()%>"
			method="post">
			<textarea class="form-control"
				placeholder="Tweet... (150 Characters only!)" rows="3" cols="50"
				maxlength="150" name="tweet"></textarea>
			<button class="btn btn-success " type="submit">Post!</button>

		</form>


		<h1>Tweets</h1>
		<%
			System.out.println("In render - user Profile");
			List<TweetStore> lTweet = (List<TweetStore>) request
					.getAttribute("Tweets");
			if (lTweet == null) {
		%>

		<p>No Tweet found</p>
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

		<%-- <a href="/Tweet/<%=ts.getUserName() %>" ><%=ts.getTweetBody() %></a><br/> --%>

		<%
			}
			}
		%>
	</div>
</body>
</html>