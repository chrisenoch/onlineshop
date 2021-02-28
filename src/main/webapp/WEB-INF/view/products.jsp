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

	<section id="explore-section" class="text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				<c:forEach items="${products}" var="product">
					<div class="col-sm-6 col-md-3 text-center">
						<div class="thumbnail">
							<h3>${product.name}</h3>
							<img src="<c:url value="${product.pictureUrl}"></c:url>"
								alt="image" style="width: 75%" />


							<p class="ml-2 mt-3">EUR ${product.price/100}</p>

							<p>
								<c:if test="${product.stock != 0}">
									<strong>Available units in stock </strong> : ${product.stock}
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
</body>
</html>
