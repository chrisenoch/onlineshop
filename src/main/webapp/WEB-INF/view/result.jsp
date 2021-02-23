<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>Payment result</title>
</head>

<body>
	<header>
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<!-- EXPLORE SECTION -->
	<section id="explore-section" class=" text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				<div class="col">

					<c:if test="${paymentSuccess == 'paymentSucceeded'}">
						<h3 style='color: green;'>Payment successful</h3>
					</c:if>

					<c:if test="${paymentSuccess == 'paymentFailed'}">
						<h3 class="alert alert-danger" role="alert">Payment attempt failed. Please try
							again or use a different payment card.</h3>
					</c:if>

					<c:if
						test="${paymentSuccess == 'paymentSucceededButCheckoutNotSaved'}">
						<h3 class="alert alert-success" role="alert">There has been a problem with your
							order. Please contact the website administrator. Sorry for the
							inconvenience.</h3>
					</c:if>

					<a class="d-block py-1"
						href="${pageContext.request.contextPath}/shop/">Continue
						shopping</a> <a class="d-block pb-1"
						href="${pageContext.request.contextPath}/shop/basket">Go to
						basket</a> <a class="d-block pb-1"
						href="${pageContext.request.contextPath}/">Back to Profile
						Page</a>

				</div>
			</div>
		</div>
	</section>


</body>
</html>

