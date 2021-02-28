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
	<section id="explore-section" class="text-muted py-5 mt-5">


		<div class=" container">
			<div class="row  col-5 ">
				<div class="card-body border border-primary bg-primary" >
					<div class="card-title h2">Register New User</div>
			</div>
			<div class="row ">

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
								class="form-control" style="width:100%;"/>
						</div>

					</form:form>
					
						<!-- Register Button -->
			<div class="form-group	d-inline ">
					<button type="submit" class="btn btn-primary w-0" form="regUser">Register</button>
			</div>

			<form:form class="d-inline ml-n5"
				action="${pageContext.request.contextPath}/showMyLoginPage"
				method="GET">
				<input type="submit" value="Back to login" class="d-inline" />
			</form:form>

				</div>

			</div>

		





		</div>






	</section>





	<div class="d-flex justify-content-center">
		<div class="p-2">Flex item 1</div>
		<div class="p-2 ">Flex item 2</div>
		<div class="p-2 ">Flex item 3</div>
	</div>
	<div class="d-flex flex-row-reverse">
		<div class="p-2">Flex item 1</div>
		<div class="p-2">Flex item 2</div>
		<div class="p-2">Flex item 3</div>
	</div>





	<section>

		<div>
			<form style="float: left;">
				<label for="fname">First name:</label><br> <input type="text"
					id="fname" name="fname"><br> <label for="lname">Last
					name:</label><br> <input type="text" id="lname" name="lname">
			</form>
			<form style="float: left;">
				<label for="fname">First name:</label><br> <input type="text"
					id="fname" name="fname"><br> <label for="lname">Last
					name:</label><br> <input type="text" id="lname" name="lname">
			</form>
		</div>

	</section>



</body>
</html>