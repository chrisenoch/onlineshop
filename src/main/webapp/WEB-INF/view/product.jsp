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
<title>Products</title>
</head>
<body>
	<header>
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<section id="explore-section" class="text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				
				<img src="<c:url value="${product.pictureUrl}"></c:url>"
					alt="image" style="width: 25%" />
				

				<div class="col-md-5">
					<h3>${product.name}</h3>
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

					<h4>EUR ${product.price/100}</h4>
					<p>
						<a href="<spring:url value="/shop" />" class="btn btn-default">
							<span class="glyphicon-hand-left glyphicon"></span> back
						</a>
						<c:if test="${product.stock > 0}">
							<a href="<spring:url value="/shop?id=${product.id}" />"
								class="btn btn-warning btn-large"> <span
								class="glyphicon-shopping-cart glyphicon"></span> Add to basket
							</a>
						</c:if>
					</p>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
