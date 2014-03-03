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
		<h1>Users...</h1>
		<%
			System.out.println("In render");
			List<UserStore> userLst = (List<UserStore>) request
					.getAttribute("searchUsers");
			if (userLst == null || userLst.isEmpty()) {
		%>

		<p>Sorry, No users can be found in that name....</p>
		<%
			} else {
		%>


		<%
			Iterator<UserStore> iterator;

				iterator = userLst.iterator();
				while (iterator.hasNext()) {
					UserStore us = (UserStore) iterator.next();
		%>
		<h5>
			<%=us.getName()%>
			(<a
				href="<%=request.getContextPath()%>/Profile/<%=us.getUserName()%>"><%=us.getUserName()%></a>)
		</h5>
		<hr>
		<%
			}
			}
		%>
	</div>
</body>
</html>