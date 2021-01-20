<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Manage addresses</title>
	<style> 	
		.error {color:red}
	</style>
</head>

<body>
<h1>Manage addresses</h1>
<h2>Addresses</h2>

<c:forEach items="${addresses}" var="address">
<p>${address.fullName}</p>
<p>${address.addressLine1}, ${address.addressLine2}</p>
<p>${address.countyOrState}, ${address.postCode}</p>
<p>${address.country}</p>
<p>${address.telephoneNo}</p>

<a href="${pageContext.request.contextPath}/account/updateaddress?edit=<c:out value="${address.id}"/>">Edit</a>
<a href="${pageContext.request.contextPath}/account/addresses?del=<c:out value="${address.id}"/>" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>

<c:if test = "${address.defaultAddress == false}">

<a href="${pageContext.request.contextPath}/account/addresses?default=<c:out value="${address.id}"/>">Set as default</a>

</c:if>

<form:form method="POST" action="${pageContext.request.contextPath}/checkoutchanged" modelAttribute="address">
  <input type='hidden' value="${address.id}" name='id' />
<input type="submit" value="Select and checkout" />
</form:form>

<br><br>
</c:forEach>

<br>
<h2>Add a new address</h2>
<i>* Asterisk means required.</i>
<br><br>
	
    <c:choose> 
	  <c:when test="${fromCheckout != null}">
	    <c:set var="addressmodel" value="addressescheckout" />
	  </c:when>
	  <c:otherwise>
	    <c:set var="addressmodel" value="addresses" />
	  </c:otherwise>
	</c:choose> <!-- If user comes from checkout, direct user back to checkout. 
	If user comees from manageaddresses page, direct user back there. -->

	<form:form action ="${addressmodel}" modelAttribute="address">

		Country/Region (*): <form:input path="country" /> 
		<form:errors path="country" cssClass="error"/>
		
		<br><br>
		Full name (*): <form:input path="fullName" />
		<form:errors path="fullName" cssClass="error"/>
		
		<br><br>
		Phone number (*): <form:input path="telephoneNo" /> 
		<form:errors path="telephoneNo" cssClass="error"/>

		<br><br>
		Post code (*): <form:input path="postCode" /> 
		<form:errors path="postCode" cssClass="error"/>
		
		<br><br>
		Address Line 1 (*): <form:input path="addressLine1" /> 
		<form:errors path="addressLine1" cssClass="error"/>
		
		<br><br>
		Address Line 2 (optional) : <form:input path="addressLine2" /> 
		<form:errors path="addressLine2" cssClass="error"/>
		
		<br><br>
		Town/City (*): <form:input path="city" /> 
		<form:errors path="city" cssClass="error"/>
		
		<br><br>
		County (*): <form:input path="countyOrState" /> 
		<form:errors path="countyOrState" cssClass="error"/>
		
		<br><br>
		Delivery instructions (optional): <form:input path="extraDeliveryInstructions" /> 
		<form:errors path="extraDeliveryInstructions" cssClass="error"/>
		
		<br><br>
		<input type="submit" value="Submit" />
	</form:form>

</body>
</html>