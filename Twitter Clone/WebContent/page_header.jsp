<%@page import="com.example.andy.stores.UserStore"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/jumbotron.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/starter-template.css"
	rel="stylesheet">

</head>

<%
	UserStore user = (UserStore) session.getAttribute("user");
	System.out.println("in the header page user name: "
			+ user.getName());
%>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/Tweets"><font face="Andy" size="5px">T_Clone</font></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a
						href="<%=request.getContextPath()%>/Profile/<%=user.getUserName()%>"><font size="3px">Profile
							- <%=user.getName()%></font></a></li>
				</ul>
				<form action="<%=request.getContextPath()%>/Logout.jsp" class="navbar-form navbar-right" role="form">
					<button type="submit" class="btn btn-warning">Log out</button>

				</form>

				<form action="<%=request.getContextPath()%>/Search"
					class="navbar-form navbar-right" role="form">
					<div class="form-group">
						<input class="form-control" id="search_bar" name="searchTxt"
							placeholder="Search User Name" type="text" maxlength="35"
							size="45px" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-info">Search</button>
				</form>
				
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>


</body>
</html>