<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.example.andy.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweeter</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="page_header.jsp" />
	<h1>Tweet</h1>
	<%
		System.out.println("In render");
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
	<form action="Profile" id="profile" method="get">
		<h5>
			User: <a href="<%=request.getContextPath() %>/Profile/<%=ts.getUserName()%>"
				onclick="document.getElementById(profile)" name="username1"><%=ts.getUserName()%></a>
		</h5>

		<h5>
			Tweet:
			<%=ts.getTweetBody()%>
		</h5>
		<h6><%=ts.getDateTime()%></h6>
		<hr>
	</form>

	<%-- <a href="/Tweet/<%=ts.getUserName() %>" ><%=ts.getTweetBody() %></a><br/> --%>
	<%
		}
		}
	%>

</body>
</html>