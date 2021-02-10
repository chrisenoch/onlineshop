<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Shop</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Shop</h1>
				<p>All the available products in our store</p>
				<p><b>Basket: ${numberOfItemsInCart}</b></p>
				
				<form style = "display:inline" action="${pageContext.request.contextPath}/shop/basket">
				   <input type="submit" value="Basket" />
				</form>
				<form style = "display:inline" action="${pageContext.request.contextPath}/showProfilePage">
				<input type="submit" value="Profile Page" />
				</form>	
				<form:form style = "display:inline" action="${pageContext.request.contextPath}/logout" 
			   	method="POST">
				<input type="submit" value="Logout" />	
				</form:form>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
					<img src="<c:url value="${product.pictureUrl}"></c:url>" alt="image"  style = "width:100%"/>
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.price/100} €</p>
							
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
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>
