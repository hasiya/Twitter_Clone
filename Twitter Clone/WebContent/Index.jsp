<%@page import="com.sun.org.apache.xpath.internal.operations.Bool"%>
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
<link href="css/signin.css" rel="stylesheet">
</head>
<body>
	<%
		if (session.getAttribute("user") != null) {
			UserStore user = (UserStore) session.getAttribute("user");

			response.sendRedirect(request.getContextPath() + "/Tweets");
		} else {
			Boolean unsuccess = (Boolean)request.getAttribute("unsuccess");
	%>


	<div class="container">
		<form class="form-signin" action="Home" method="post">

			<h2 class="form-signin-heading">Please sign in</h2>
			<input name="usernameTxt" type="text" autocomplete="off"
				class="form-control" placeholder="User Name" required autofocus>
			<input name="passwordTxt" type="password" autocomplete="off"
				class="form-control" placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>				
		</form>
		<form class="form-signin" >
		<%if((unsuccess != null) && (unsuccess==true) ) {%>
			<font color="red">The User name or password you entered is incorrect. Please try again!</font>
			<%} %>
			</form>
		<form class="form-signin" action="UserRegister.jsp">
			<h3 class="form-signin-heading">Or register now!</h3>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form>

	</div>
	<%
		}
	%>
</body>
</html>