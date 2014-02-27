<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Twitter - Welcome</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="form-signin" action="Home" method="post">

			<h2 class="form-signin-heading">Please sign in</h2>
			<input name="usernameTxt" type="text" class="form-control"
				placeholder="User Name" required autofocus> <input
				name="passwordTxt" type="password" class="form-control"
				placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>
		<form class="form-signin" action="UserRegister.jsp">
			<h3 class="form-signin-heading">Or register now!</h3>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form>

	</div>
</body>
</html>