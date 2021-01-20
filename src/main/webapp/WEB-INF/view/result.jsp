<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resulte</title>
</head>
<body>

 		<c:if test = "${paymentSuccess == 'paymentSucceeded'}">
		<h3 style='color: green;'>Payment successful.</h3>
		</c:if>
		
		<c:if test = "${paymentSuccess == 'paymentFailed'}">
		<h3 style='color: red;'>Payment attempt failed. Please try again or use a different payment card.</h3>
		</c:if>
		
		<c:if test = "${paymentSuccess == 'paymentSucceededButCheckoutNotSaved'}">
		<h3 style='color: red;'>There has been a problem with your order. Please contact the website 
		administrator. Sorry for the inconvenience.</h3>
		</c:if>

        <a href='${pageContext.request.contextPath}/shop'>Continue shopping</a>
        
        
    </body>
</html>

