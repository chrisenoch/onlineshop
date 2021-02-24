<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

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
<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css">

</head>

<body>
<header>
<c:set var = "currentPage" value = "login"/>
<%@ include file="navigation-bar.jsp" %> 
</header>

	<div class="home-inner container mt-5">
		<div class="row">

			<div class="col-lg-4 ">

				<div class=" px-3 py-2 card text-center card-form bg-light mt-5	">

					<div class="card-body">
						<h3>Sign In</h3>

						<!-- Login Form -->
						<form:form
							action="${pageContext.request.contextPath}/authenticateTheUser"
							method="POST" class="form-horizontal">

							<!-- Place for messages: error, alert etc ... -->
							<div class="form-group">
								<div class="col-xs-15">
									<div>

										<!-- Check for login error -->

										<c:if test="${param.error != null}">

											<div class="alert alert-danger">Invalid username and
												password.</div>

										</c:if>

										<!--		            
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out.
									</div>
								    -->

									</div>
								</div>
							</div>

							<!-- User name -->
							<div class="form-group">
								 <input type="text"
									name="username" placeholder="username"
									class="form-control form-control-lg" placeholder="Username">
							</div>

							<!-- Password -->
							<div class="form-group">
								 <input type="password"
									name="password" placeholder="password"
									class="form-control form-control-lg">
							</div>

							<!-- Login/Submit Button -->


							<input type="submit" class="btn btn-success btn-block"
								value="Login"></>
							
						

					</form:form>

					</div>

				</div>

				<div class="row">
					<div class="d-inline col text-center">
						<a
							href="${pageContext.request.contextPath}/register/showRegistrationForm"
							class="  btn btn-primary mt-3" role="button">Register New User</a>
					</div>
				</div>

			</div>


		</div>
	</div>

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