<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
    
<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css">
	
	<title>Members Directory</title>
</head>

<body>
<header>
<%@ include file="navigation-bar.jsp" %> 
</header>

<div class="container py-5 mt-5">
	<h3>Members Directory</h3>
	
	<hr>
	
		<table class="table table-bordered table-striped">
		<thead class="thead-dark">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
			</tr>
		</thead>
		
		<tbody>
		
		<c:forEach items="${members}" var="tempMember">
		<tr>
		    <td>${tempMember.firstName}</td>
		    <td>${tempMember.lastName}</td>
		    <td>${tempMember.email}</td>
		</tr>
		</c:forEach>

		</tbody>		
	</table>
	
	<c:set var="startIndex" value="${param.page}" />

	<c:if test = "${startIndex > 1}">
        <a href="${pageContext.request.contextPath}/members?page=<c:out value="${startIndex - 1}"/>">Previous</a>
    </c:if>
    
	<c:set var="j" value="${startIndex + 1}" />
	<c:set var="k" value="${numOfPages}" />
	
	 <c:forEach var = "i" begin = "1" end = "${k}">
		 <c:if test="${i != startIndex}">
	          <a href="${pageContext.request.contextPath}/members?page=<c:out value="${i}"/>">${i}</a>
	    </c:if>
      </c:forEach>
    
 	<c:if test = "${startIndex < numOfPages}">
        <a href="${pageContext.request.contextPath}/members?page=<c:out value="${startIndex + 1}"/>">Next</a>
    </c:if>

</div>
</body>
</html>




