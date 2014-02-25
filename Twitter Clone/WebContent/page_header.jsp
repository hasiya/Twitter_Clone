<%@page import="com.example.andy.stores.UserStore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css\page_header.CSS" type="text/css" />


</head>

<%
	UserStore user = (UserStore) session.getAttribute("user");
	System.out.println("in the header page user name: "
			+ user.getName());
	session.setAttribute("user", user);
%>

<body>
	<div id="navigationBar" style="background-color: white">
		<center>
			<table width="90%" border="2">
				<tr>
					<td width="25%" align="center">
						<form action="Tweets">
							<input id="nav_btn" type="submit" value="Home">
						</form>
					</td>
					<td width="25%" align="center">
						<form action="Profile" id="profile" >
							<a href="javascript:;" onclick="document.getElementById(profile)"  > </a>
							<input name="userName" id="profile_btn" type="submit"  value="Profile - <%=user.getName()%>">
						</form>
					</td>
					<td width="35%" align="right"><input id="search_bar"
						maxlength="35" type="text"></td>
					<td width="15%" align="left"><input id="nav_btn" type="submit"
						value="Search"></td>

				</tr>
			</table>
		</center>
	</div>
	<br>
	<br>
</body>
</html>