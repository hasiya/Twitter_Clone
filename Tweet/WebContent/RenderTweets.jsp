<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.example.andy.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweeter</title>
</head>
<body>

<h1>Tweet</h1>
<%
System.out.println("In render");
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
	<p>No Tweet found</p>
	<% 
}else{
%>


<% 
Iterator<TweetStore> iterator;


iterator = lTweet.iterator();     
while (iterator.hasNext()){
	TweetStore ts = (TweetStore)iterator.next();

	%>
	<h5>User: <%=ts.getUser()%></h5> 
	<h5>Tweet: <%=ts.getTweet()%> </h5>
	<%-- <a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%=ts.getTweet() %></a><br/> --%><%

}
}
%>

</body>
</html>