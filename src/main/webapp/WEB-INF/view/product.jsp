<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<title>Product</title>
</head>
<body>
	<header>
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<section class="text-muted py-5 mt-5">
		<div class="container">

			<div class="card border border-white">
				<div class="row ">
					<div class="col-6 col-sm-5 col-md-5 col-lg-3">
						<img src="<c:url value="${product.pictureUrl}"></c:url>"
							class="img-fluid" alt="image" style="width: 16rem;">
					</div>
					<div class="col-7 col-sm-6    mt-3 mt-sm-0" >
						<div class="card-block px-2">
							<h4 class="card-title">${product.name}</h4>	

							<p>${product.description}</p>
							<p>
								<strong>Item Code : </strong><span class="label label-warning">${product.id}</span>
							</p>
							<p>
								<strong>Brand</strong> : ${product.brand}
							</p>
							<p>
								<strong>Category</strong> : ${product.category}
							</p>

							<p>
								<c:if test="${product.stock != 0}">
									<strong>Available units in stock </strong> : ${product.stock}
								</c:if>
								<c:if test="${product.stock == 0}">
									<strong>Available units in stock </strong> : Out of stock
								</c:if>
							</p>

							<h4>${product.price/100} €</h4>
							<p>
								<a href="<spring:url value="/shop" />" class="btn btn-default">
									<span class="glyphicon-hand-left glyphicon"></span> back
								</a>
								<c:if test="${product.stock > 0}">
									<a href="<spring:url value="/shop?id=${product.id}" />"
										class="btn btn-primary">Add to basket</a>

								</c:if>
							</p>
						</div>
					</div>
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
