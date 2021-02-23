<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<title>Temporary landing page</title>
</head>

<body>
<header>
<%@ include file="navigation-bar.jsp" %> 
</header>



	<!-- EXPLORE SECTION -->
	<section id="explore-section" class=" text-muted py-5 mt-5">
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>This item is already in your basket.</h1>
					<a class="d-block py-1" href="${pageContext.request.contextPath}/shop/">Back to Shop</a>	
					<a class="d-block pb-1" href="${pageContext.request.contextPath}/shop/basket">Go to basket</a>
					<a class="d-block pb-1" href="${pageContext.request.contextPath}/">Back to Profile Page</a>
	
				</div>
			</div>
		</div>
	</section>

	
	
</body>

</html>