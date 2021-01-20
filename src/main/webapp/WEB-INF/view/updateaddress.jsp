<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Update addresses</title>
	<style> 	
		.error {color:red}
	</style>
</head>

<body>
<h1>Update addresses</h1>

<i>* Asterisk means required.</i>
<br><br>

	<form:form modelAttribute="address">
	
	<!-- need to associate this data with addressId -->
			<form:hidden path="id" />

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