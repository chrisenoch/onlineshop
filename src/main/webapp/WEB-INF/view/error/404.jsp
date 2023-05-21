<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Error</title>
</head>

<body>

	<h2>404. That's an error</h2>
	<h3>The requested URL was not found on this server.</h3>

	<hr>
	
	<br>
	<a href="${pageContext.request.contextPath}/">Go to Home Page</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/showProfilePage">Go to Profile Page</a>
	
</body>

</html>