<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css">
<title>Manage addresses</title>

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


	
	<section class=" text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				<div class="col">

					<c:if test="${not empty addresses}">
						<h1>Manage addresses</h1>
					</c:if>

				</div>
			</div>

			<div>
				<div class="row">
					<c:forEach items="${addresses}" var="address">

						<div class="col-md-6 mt-3">

							<p class="mb-2">${address.fullName}</p>
							<p class="mb-1">${address.addressLine1},${address.addressLine2}</p>
							<p class="mb-1">${address.countyOrState},${address.postCode}</p>
							<p class="mb-1">${address.country}</p>
							<p>${address.telephoneNo}</p>

							<a
								href="${pageContext.request.contextPath}/account/updateaddress?edit=<c:out value="${address.id}"/>">Edit</a>
							<a
								href="${pageContext.request.contextPath}/account/addresses?del=<c:out value="${address.id}"/>"
								onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>

							<c:if test="${address.defaultAddress == false}">

								<a
									href="${pageContext.request.contextPath}/account/addresses?default=<c:out value="${address.id}"/>">Set
									as default</a>

							</c:if>

							<form:form method="POST"
								action="${pageContext.request.contextPath}/checkout"
								modelAttribute="address">
								<input type='hidden' value="${address.id}" name='id' />
								<input type="submit" class="btn btn-success mt-2"
									value="Select and checkout" />
							</form:form>

							<br> <br>
						</div>

					</c:forEach>
				</div>
				<div>




					<h2>Add a new address</h2>
					<i>* Asterisk means required.</i> <br> <br>

					<c:choose>
						<c:when test="${fromCheckout != null}">
							<c:set var="addressmodel" value="addressescheckout" />
						</c:when>
						<c:otherwise>
							<c:set var="addressmodel" value="addresses" />
						</c:otherwise>
					</c:choose>
					<!-- If user comes from checkout, direct user back to checkout. 
					If user comes from manageaddresses page, direct user back there. -->




					<form:form action="${addressmodel}" modelAttribute="address">

						<div class="form-group">
							Country/Region (*):
							<form:input path="country" class="form-control-inline" />
							<form:errors path="country" cssClass="error" />
						</div>

						<div class="form-group">
							Full name (*):
							<form:input path="fullName" class="form-control-inline" />
							<form:errors path="fullName" cssClass="error" />
						</div>

						<div class="form-group">
							Phone number (*):
							<form:input path="telephoneNo" class="form-control-inline" />
							<form:errors path="telephoneNo" cssClass="error" />
						</div>

						<div class="form-group">
							Post code (*):
							<form:input path="postCode" class="form-control-inline" />
							<form:errors path="postCode" cssClass="error" />
						</div>

						<div class="form-group">
							Address Line 1 (*):
							<form:input path="addressLine1" class="form-control-inline" />
							<form:errors path="addressLine1" cssClass="error" />
						</div>

						<div class="form-group">
							Address Line 2 (optional) :
							<form:input path="addressLine2" class="form-control-inline" />
							<form:errors path="addressLine2" cssClass="error" />
						</div>

						<div class="form-group">
							Town/City (*):
							<form:input path="city" class="form-control-inline" />
							<form:errors path="city" cssClass="error" />
						</div>

						<div class="form-group">
							County (*):
							<form:input path="countyOrState" class="form-control-inline" />
							<form:errors path="countyOrState" cssClass="error" />
						</div>

						<div class="form-group">
							Delivery instructions (optional):
							<form:input path="extraDeliveryInstructions"
								class="form-control-inline" />
							<form:errors path="extraDeliveryInstructions" cssClass="error" />
						</div>


						<input type="submit" class="btn btn-success " value="Submit" />
					</form:form>

				</div>
			</div>
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