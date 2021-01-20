<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Profile Page</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Profile Page </h1>
				
		<form style = "display:inline" action="${pageContext.request.contextPath}/shop">
		   <input type="submit" value="Shop" />
		</form>
		<form style = "display:inline" action="${pageContext.request.contextPath}/shop/basket">
		   <input type="submit" value="Basket" />
		</form>
		<form:form style = "display:inline" action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
		</form:form>
		
				
			</div>
		</div>
	</section>
	
	<p><spring:message code="profile-page.form.firstName.label" />${user.firstName}</p>
	<p><spring:message code="profile-page.form.lastName.label" />${user.lastName}</p>
	<p><spring:message code="profile-page.form.username.label" />${user.username}</p>
	
	
	
	<div class="col-md-5">
				<img src="<c:url value="${user.pictureURL}"></c:url>"
					alt="image" style="width: 100%" />
			</div>

	
	<c:if test = "${isProfileOwner}">
		<section class="container">
			<form:form method="POST" action="?${_csrf.parameterName}=${_csrf.token}" modelAttribute="newProfile"
				class="form-horizontal" enctype="multipart/form-data">
				<fieldset>
					<legend>Add new profile picture</legend>
					<div class="form-group">
						<label class="control-label col-lg-2" for="profileImage">Profile Image
						</label>
						<div class="col-lg-10">
							<form:input id="profileImage" path="profileImage" type="file"
								class="form:input-large" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
							<input type="submit" id="btnAdd" class="btn btn-primary"
								value="Add" />
						</div>
					</div>
					
					
				</fieldset>
	
			</form:form>
		</section>	
	</c:if>
	
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a>|<a href="?language=es">Spanish</a>
			<a href="?language=de">German</a>
		</div>
	</section>
	
	
	
	
</body>
</html>
