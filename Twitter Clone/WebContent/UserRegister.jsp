<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
</head>
<body>


	<div class="container">
		<!-- <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-4  well-sm"> -->

		<form action="<%=request.getContextPath() %>/Register" method="post" class="form-signin" role="form">
			<legend> Sign up!</legend>
			<input class="form-control" name="name" autocomplete="off" placeholder="Name"
				type="text"  required autofocus /> <input class="form-control"
				name="userName" autocomplete="off" placeholder="Create User Name" type="text" required /> <input
				class="form-control" name="email" autocomplete="off" placeholder="Your Email" type="email" /> <input
				class="form-control" name="password" autocomplete="off" placeholder="Create Password"
				type="password" /> 
			<label class="radio-inline"> <input type="radio" name="gender"
				id="inlineCheckbox1" value="male" /> Male
			</label> <label class="radio-inline"> <input type="radio" name="sex"
				id="inlineCheckbox2" value="female" /> Female
			</label> <br /> <br />
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				Sign up</button>
				<br>
			<center>Or</center>
		</form>
		<form action="<%=request.getContextPath() %>" class="form-signin" role="form">
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				Back to Sign in</button>
		</form>
		<!-- </div>
		</div> -->
	</div>
</body>
</html>