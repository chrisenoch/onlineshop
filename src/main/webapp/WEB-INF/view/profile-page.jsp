<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<title>Profile Page</title>
</head>
<body>
	<header>
		<c:set var="currentPage" value="profilePage" />
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<!-- EXPLORE SECTION -->
	<section id="explore-section" class="bg-light text-muted py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-6 mt-5">

					<h1 class="mb-4" >Profile Page</h1>
					<p>
						<spring:message code="profile-page.form.firstName.label" /> ${user.firstName}</p>
					<p>
						<spring:message code="profile-page.form.lastName.label" /> ${user.lastName}</p>
					<p>
						<spring:message code="profile-page.form.username.label" /> ${user.username}</p>


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
									<legend class="pl-3 h6 font-weight-normal "	>Add new profile picture</legend>
									<div class="form-group h6">
										<div class="col-lg-10 ">
											<form:input id="profileImage" path="profileImage" type="file"
												class="form:input-large" />
										</div>
									</div>
									<div class="form-group">
										<div class=" ml-0 col-lg-10 ">
											<input type="submit" id="btnAdd" class="btn btn-primary"
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
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a>|<a href="?language=es">Spanish</a>
			<a href="?language=de">German</a>
		</div>
	</section>




</body>
</html>
