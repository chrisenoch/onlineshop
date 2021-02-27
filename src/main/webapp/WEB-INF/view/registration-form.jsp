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

	<!-- EXPLORE SECTION -->
	<section id="explore-section" class=" text-muted py-5 mt-5">
		<div class="container">
			<div class="row">

				<div class="card-body col-12">
					<div class="card-title h2">Register New User</div>



					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/register/processRegistrationForm"
						modelAttribute="regUser">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-12">
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
								class="form-control w-25" />
						</div>

						<!-- Password -->
						<div class="form-group">
							<form:errors path="password" cssClass="error" />
							<form:password path="password" placeholder="password (*)"
								class="form-control  w-25" />
						</div>

						<!-- Confirm Password -->
						<div class="form-group">
							<form:errors path="matchingPassword" cssClass="error" />
							<form:password path="matchingPassword"
								placeholder="confirm password (*)" class="form-control  w-25" />
						</div>


						<!-- First name -->
						<div class="form-group">
							<form:errors path="firstName" cssClass="error" />
							<form:input path="firstName" placeholder="first name (*)"
								class="form-control  w-25" />
						</div>

						<!-- Last name -->
						<div class="form-group">
							<form:errors path="lastName" cssClass="error" />
							<form:input path="lastName" placeholder="last name (*)"
								class="form-control  w-25" />
						</div>

						<!-- Email -->
						<div class="form-group">

							<form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="email (*)"
								class="form-control  w-25" />
						</div>
						
						</div>
						<div class="row">

						<!-- Register Button -->
						<div class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary d-flex-inline">Register</button>
							</div>
						</div>

					</form:form>

					<div class="form-group">
						<form:form class="d-flex-inline"
							action="${pageContext.request.contextPath}/showMyLoginPage"
							method="GET">
							<input type="submit" value="Back to login" />
						</form:form>
					</div>
					
					</div>

				</div>

			</div>

		</div>

	</section>




	<section>
		<div class="container">
			<div class="row">
				<div class="col">
					<form>
						<div class="form-group"">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control w-25" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter email">
							<small id="emailHelp" class="form-text text-muted">We'll
								never share your email with anyone else.</small>
						</div>
						<button type="submit" class="btn btn-primary w-25">Submit</button>
					</form>
				</div>

				<div class="col">
					<form>
						<div class="form-group"">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control w-25" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter email">
							<small id="emailHelp" class="form-text text-muted">We'll
								never share your email with anyone else.</small>
						</div>
						<button type="submit" class="btn btn-primary w-25">Submit</button>
					</form>
				</div>

			</div>

		</div>


	</section>



</body>
</html>