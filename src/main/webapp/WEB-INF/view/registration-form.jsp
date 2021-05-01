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
<title>Register New User Form</title>

<style>
.error {
	color: red
}
</style>

</head>

<body>
	<header>
		<%@ include file="navigation-bar.jsp"%>
	</header>

	
	<section class="text-muted py-5 mt-5">


		<div class=" container">
			<div class="card " style="width: 25rem;">
				<h5 class="card-header bg-primary text-white text-center">Register New User</h5>
				<div class="card-body border border-primary bg-light">

					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/register/processRegistrationForm"
						modelAttribute="regUser" id="regUser">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col">
								<div>

									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">

											<c:forEach var="error" items="${registrationError}">
												<c:out value="${error}" />
												<br>
											</c:forEach>
										</div>

									</c:if>

								</div>
							</div>
						</div>

						<!-- User name -->
						<div class="form-group">

							<form:errors path="userName" cssClass="error" />
							<form:input path="userName" placeholder="username (*)"
								class="form-control " />
						</div>

						<!-- Password -->
						<div class="form-group">
							<form:errors path="password" cssClass="error" />
							<form:password path="password" placeholder="password (*)"
								class="form-control"  />
						</div>

						<!-- Confirm Password -->
						<div class="form-group">
							<form:errors path="matchingPassword" cssClass="error" />
							<form:password path="matchingPassword"
								placeholder="confirm password (*)" class="form-control" />
						</div>


						<!-- First name -->
						<div class="form-group">
							<form:errors path="firstName" cssClass="error" />
							<form:input path="firstName" placeholder="first name (*)"
								class="form-control"/>
						</div>

						<!-- Last name -->
						<div class="form-group">
							<form:errors path="lastName" cssClass="error" />
							<form:input path="lastName" placeholder="last name (*)"
								class="form-control" />
						</div>

						<!-- Email -->
						<div class="form-group">

							<form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="email (*)"
								class="form-control"  />
						</div>

					</form:form>

					<!-- Register Button -->
					<div class="form-group	d-inline ">
						<button type="submit" class="btn btn-primary btn-block mb-3" form="regUser">Register</button>
					</div>

					<form:form class="d-inline ml-n5 mt-5"
						action="${pageContext.request.contextPath}/showMyLoginPage"
						method="GET">
						<input type="submit" value="Back to login" class="btn btn-sm btn-muted" />
					</form:form>

				</div>

			</div>

		</div>

	</section>

	
	<c:forEach var="error" items="${errors}">
	<p><c:out value="${error}." /></p>
	</c:forEach>


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