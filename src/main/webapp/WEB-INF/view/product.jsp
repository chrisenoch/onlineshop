<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">

				
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
			<div class="col-md-5">
				<img src="<c:url value="${product.pictureUrl}"></c:url>"
					alt="image" style="width: 100%" />
			</div>

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
					<strong>Availble units in stock </strong> : ${product.stock}
				</c:if>
				<c:if test="${product.stock == 0}">
					<strong>Availble units in stock </strong> : Out of stock
				</c:if>
				
				</p>
				
				<h4>EUR ${product.price/100}</h4>
				<p>
					<a href="<spring:url value="/shop" />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span> back
					</a> 
					<c:if test = "${product.stock > 0}">
					<a href="<spring:url value="/shop?id=${product.id}" />"
						class="btn btn-warning btn-large"> <span
						class="glyphicon-shopping-cart glyphicon"></span> Add to basket
					</a>
					</c:if>
				</p>
			</div>
		</div>
	</section>
	
</body>
</html>
