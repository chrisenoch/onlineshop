<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

</head>

<body>
	<header>
		<c:set var="currentPage" value="login" />
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<section class="text-muted py-5 mt-5">

		<div class=" container">

			<div class="row ml-2">

					<div class="  card bg-light mt-5" style="width: 20rem;">
						<h5 class="card-header bg-primary text-white text-center">Sign
							In</h5>

						<div class="card-body pt-3">

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
									<label for="username">Username</label> <input type="text"
										name="username" class="form-control">
								</div>

								<!-- Password -->
								<div class="form-group">
									<labelfor"password">Password</label> <input type="password"
										name="password" class="form-control">
								</div>

								<!-- Login/Submit Button -->
								<input type="submit" class="btn btn-success btn-block p-2 mt-4 "
									value="Login"></>

					</form:form>

						</div>

					</div>
				

			</div>
			<div class="row text-center">
				<div div style="width: 20rem;">
					<a
						href="${pageContext.request.contextPath}/register/showRegistrationForm"
						class="btn btn-primary mt-3" role="button">Register New User</a>
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