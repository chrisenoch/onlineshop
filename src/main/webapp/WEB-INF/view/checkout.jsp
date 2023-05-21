<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Checkout</title>
</head>
<body>
	<header>
		<c:set var="currentPage" value="checkout" />
		<%@ include file="navigation-bar.jsp"%>
	</header>



	<section class="text-muted py-5 mt-5">
		<div class="container ">



			<c:choose>
				<c:when test="${lineItems == 0 || lineItems == null}">
					<h2>Checkout</h2>
					<p>You have no items in your basket.</p>
					<a href="${pageContext.request.contextPath}/shop">Go to shop</a>
				</c:when>
				<c:otherwise>
					<c:if test="${address == null}">
						<h3 class="mb-3">You need to add/select an address</h3>
						
						
						<form action="${pageContext.request.contextPath}/account/fromcheckout" method="POST">
							<input type="hidden" name="ch" value="ch" />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<input class="btn btn-primary" type="submit" value="Add an address" />
						</form>		
							
					</c:if>

					<c:if test="${address != null}">
						<h3>Delivery address:</h3>
						<p>${address.fullName}</p>
						<p>${address.addressLine1},${address.addressLine2}</p>
						<p>${address.countyOrState},${address.postCode}</p>
						<p>${address.country}</p>

						<a
							href="${pageContext.request.contextPath}/account/addresses?ch=<c:out value="1"/>">Change
							address</a>

						<br>
						<br>
						<form action='${pageContext.request.contextPath}/charge'
							method='POST' id='checkout-form'>
							<input type='hidden' value="${amount}" name='amount' /> <input
								type='hidden' value="${address.id}" name='addressId' /> <input
								type='hidden' value="${orderId}" name='orderId' /> <input
								type='hidden' value="${shippingCost}" name='shippingCost' />
							<h3>Price:</h3>
							<p>${amountFormatted} €</p>
							<!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
							<script src='https://checkout.stripe.com/checkout.js'
								class='stripe-button' data-key="${stripePublicKey}"
								data-amount="${amount}" data-currency="${currency}"
								data-name='Onlineshop' data-description='Checkout'
								data-image='https://www.baeldung.com/wp-content/themes/baeldung/favicon/android-chrome-192x192.png'
								data-locale='auto' data-zip-code='false'>
								
							</script>

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

						</form>
					</c:if>

				</c:otherwise>
			</c:choose>
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