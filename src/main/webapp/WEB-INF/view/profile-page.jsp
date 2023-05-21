<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="es">
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

<title>Profile Page</title>
</head>
<body>
	<header>
		<c:set var="currentPage" value="profilePage" />		
		<%@ include file="navigation-bar.jsp"%>
	</header>

	
	<section class="text-muted py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-6 mt-5">

					<h1 class="mb-4">Profile Page</h1>
					<p>
						<spring:message code="profile-page.form.firstName.label" />
						${user.firstName}
					</p>
					<p>
						<spring:message code="profile-page.form.lastName.label" />
						${user.lastName}
					</p>
					<p>
						<spring:message code="profile-page.form.username.label" />
						${user.username}
					</p>


				</div>
			</div>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-md-6">

					<img src="<c:url value="${user.pictureURL}"></c:url>" alt="image"
						style="width: 100%" />

					<c:if test="${isProfileOwner}">

						<form:form method="POST"
							action="?${_csrf.parameterName}=${_csrf.token}"
							modelAttribute="newProfilePicture" class="form-horizontal"
							enctype="multipart/form-data">
							<fieldset class="mt-2">
								<legend class="pl-3 h6 font-weight-light ">Add new
									profile picture</legend>


								<div class="input-group mb-3">
									<div class="custom-file">
										<form:input type="file" class="custom-file-input"
											id="profileImage" path="profileImage" /> <label
											class="custom-file-label" for="profileImage">Choose
											file</label>
									</div>
									<div class="input-group-append">
											<input type="submit" id="profileImage" class="btn btn-primary"
											value="Add" />								
									</div>
								</div>

							</fieldset>

						</form:form>

					</c:if>

				</div>
			</div>
		</div>
	</section>


	<section>
		<div class="text-right" style="padding-right: 50px">
			<a href="?language=en">English</a>|<a href="?language=es">Spanish</a>
			<a href="?language=de">German</a>
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
