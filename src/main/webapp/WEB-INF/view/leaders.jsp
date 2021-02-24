<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css">
<title>LEADERS Home Page</title>
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
					<h2>LEADERS Home Page</h2>

					<hr>

					<p>
						See you in Brazil ... for our annual Leadership retreat! <br>
						Keep this trip a secret, don't tell the regular employees LOL :-)
					</p>

					<hr>

					<a href="${pageContext.request.contextPath}/">Back to Profile
						Page</a>

				</div>
			</div>
		</div>
	</section>





</body>

</html>