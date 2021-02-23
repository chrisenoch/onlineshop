<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
	<div class="container">
		<a href="index.html" class="navbar-brand">Company name</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav ml-auto">

				<c:if test="${currentPage != 'logout'}">

					<li class="nav-item"><a
						href="javascript: document.logoutForm.submit()" role="menuitem"
						class="nav-link"> Logout</a></li>

					<form:form name="logoutForm" style="display:inline"
						action="${pageContext.request.contextPath}/logout" method="POST">
						<input hidden="true" type="submit" value="Logout" />
					</form:form>

				</c:if>

				<c:if test="${currentPage != 'checkout'}">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/checkout"
						class="nav-link ">Checkout</a></li>
				</c:if>
				<c:if test="${currentPage != 'basket'}">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/shop/basket"
						class="nav-link">Basket</a></li>
				</c:if>
				<c:if test="${currentPage != 'shop'}">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/shop" class="nav-link">Shop</a></li>
				</c:if>
				<c:if test="${currentPage != 'profilePage'}">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/showProfilePage"
						class="nav-link">Profile Page</a></li>
				</c:if>


			</ul>
		</div>
	</div>
</nav>



