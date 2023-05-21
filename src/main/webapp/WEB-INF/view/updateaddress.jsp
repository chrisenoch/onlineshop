<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css">
<title>Update addresses</title>
<style>
.error {
	color: red
}
</style>
</head>

<body>
	<header>
		<%@ include file="navigation-bar.jsp"%>
	</header>


	<section class="text-muted py-5 mt-5">
		<div class="container ">

			<h1>Update addresses</h1>

			<i>* Asterisk means required.</i> <br> <br>

			<form:form modelAttribute="address">

				<!-- need to associate this data with addressId -->
				<form:hidden path="id" />

		Country/Region (*): <form:input path="country" />
				<form:errors path="country" cssClass="error" />

				<br>
				<br>
		Full name (*): <form:input path="fullName" />
				<form:errors path="fullName" cssClass="error" />

				<br>
				<br>
		Phone number (*): <form:input path="telephoneNo" />
				<form:errors path="telephoneNo" cssClass="error" />

				<br>
				<br>
		Post code (*): <form:input path="postCode" />
				<form:errors path="postCode" cssClass="error" />

				<br>
				<br>
		Address Line 1 (*): <form:input path="addressLine1" />
				<form:errors path="addressLine1" cssClass="error" />

				<br>
				<br>
		Address Line 2 (optional) : <form:input path="addressLine2" />
				<form:errors path="addressLine2" cssClass="error" />

				<br>
				<br>
		Town/City (*): <form:input path="city" />
				<form:errors path="city" cssClass="error" />

				<br>
				<br>
		County (*): <form:input path="countyOrState" />
				<form:errors path="countyOrState" cssClass="error" />

				<br>
				<br>
		Delivery instructions (optional): <form:input
					path="extraDeliveryInstructions" />
				<form:errors path="extraDeliveryInstructions" cssClass="error" />

				<br>
				<br>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>

		</div>
	</section>

	<script src="http://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>
</html>