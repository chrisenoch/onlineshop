<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

<title>Shop</title>
</head>
<body>
	<header>
		<c:set var="currentPage" value="shop" />
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<section class="text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				<c:forEach items="${products}" var="product">
					<div class="col-6 col-md-3 text-center">
						<div class="thumbnail">
							<h3>${product.name}</h3>
							<img src="<c:url value="${product.pictureUrl}"></c:url>"
								alt="image" style="width: 75%" />


							<p class="ml-2 mt-3"><span class="p-2 badge badge-warning" style="font-size:14px;">${product.price/100} €</span></p>

							<p>
								<c:if test="${product.stock != 0}">
									Available stock : <span class="badge badge-info">${product.stock}</span>
								</c:if>
								<c:if test="${product.stock == 0}">
									<strong>Out of stock</strong>
								</c:if>

							</p>

							<p>
								<a
									href=" <spring:url value="/shop/product?id=${product.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
							</p>


						</div>
					</div>
				</c:forEach>
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
