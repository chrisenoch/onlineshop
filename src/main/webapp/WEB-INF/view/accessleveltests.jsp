<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
<title>Testing access levels</title>
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
					<h2>Testing access levels</h2>
					<hr>

					<p>Everybody can see this.</p>

					<hr>

					<!-- display user name and role -->

					<p>
						User:
						<security:authentication property="principal.username" />
						<br>
						<br> Role(s):
						<security:authentication property="principal.authorities" />
					</p>

					<security:authorize access="hasRole('MANAGER')">

						<!-- Add a link to point to /leaders ... this is for the managers -->

						<p>
							<a href="${pageContext.request.contextPath}/leaders">Leadership
								Meeting</a> (Only for managers)
						</p>

					</security:authorize>


					<security:authorize access="hasRole('ADMIN')">

						<!-- Add a link to point to /systems ... this is for the admins -->

						<p>
							<a href="${pageContext.request.contextPath}/systems">IT
								Systems Meeting</a> (Only for IT)
						</p>

					</security:authorize>
				</div>
			</div>
		</div>
	</section>




</body>

</html>