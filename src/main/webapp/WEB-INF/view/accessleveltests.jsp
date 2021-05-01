
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

	
	<section class=" text-muted py-5 mt-5">
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
						<br> <br> Role(s):
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