<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.example.andy.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="page_header.jsp" />
</head>
<body>

	<h1>Tweet</h1>
	<%
		System.out.println("In render");
		List<TweetStore> lTweet = (List<TweetStore>) request.getAttribute("Tweets");
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
			User: <a href="/Tweet/Profile"
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