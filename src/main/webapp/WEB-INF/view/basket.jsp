<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
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
<title>Shopping Basket</title>
</head>

<body>
	<header>
		<c:set var="currentPage" value="basket" />
		<%@ include file="navigation-bar.jsp"%>
	</header>

	<section class="text-muted py-5 mt-5">
		<div class="container ">

			<c:choose>
				<c:when
					test="${(lineItems == 0 || lineItems == null) &&
	     (updatedLineItemsDueToStockShortage == null)}">
					<h2>You currently have no items in your basket.</h2>
				</c:when>


				<c:when
					test="${updatedLineItemsDueToStockShortage != null && lineItems == 0 }">

					<%-- Refactor code below--%>
					<h1 class="h2">Shopping basket</h1>
					<hr>
					<p style="color: red; font-weight: bold;">Unfortunately our
						stock levels have changed since you added the items to your
						basket.</p>

					<c:forEach var="item1"
						items="${updatedLineItemsDueToStockShortage}">
						<c:forEach var="item2" items="${item1.value}">
							<ul>
								<li>You requested <c:out value="${item2.key}" /> <c:if
										test="${item2.key > 1}">
										<c:out value="${item1.key.name}s." />
									</c:if> <c:if test="${item2.key == 1}">
										<c:out value="${item1.key.name}." />
									</c:if>
							</ul>
						</c:forEach>
						<c:if
							test="${fn:length(updatedLineItemsDueToStockShortage) == 1}">This</c:if>
						<c:if
							test="${fn:length(updatedLineItemsDueToStockShortage) > 1}">These</c:if>  
			        has been removed from your basket. We apologise for the inconvenience.	
			</c:forEach>
					<br>
					<br>


				</c:when>
				<c:otherwise>
					<h1 class="h2">Shopping basket</h1>
					<hr>

					<c:if test="${updatedLineItemsDueToStockShortage != null}">

						<p style="color: red; font-weight: bold;">Unfortunately our
							stock levels have changed since you added the items to your
							basket. We have suggested a change to your order to reflect our
							current stock levels:</p>

						<c:forEach var="item1"
							items="${updatedLineItemsDueToStockShortage}">
							<c:forEach var="item2" items="${item1.value}">
								<ul>

									<li>You requested <c:out value="${item2.key}" /> <c:if
											test="${item2.key > 1}">
											<c:out value="${item1.key.name}s." />
										</c:if> <c:if test="${item2.key == 1}">
											<c:out value="${item1.key.name}." />
										</c:if> This has been changed to <c:out value="${item2.value}." />
								</ul>
							</c:forEach>
						</c:forEach>

						<a class="btn btn-primary mb-5"
							href="${pageContext.request.contextPath}/checkout" role="button">
							Continue to checkout</a>

					</c:if>










					<c:forEach var="entry" items="${lineItemsList}">
						<div class="row mb-5">
							<div class="col-auto">
								<img src="<c:url value="${entry.product.pictureUrl}"></c:url>"
									class="img-fluid" alt="image" style="width: 7rem;">
							</div>

							<div class="col-8 d-flex flex-column">

								<h2 class="h5" >
									<c:out value="${entry.product.name}" />
								</h2>

								${entry.product.description}


								<div class="d-flex mt-auto	">
									<form action="" method="POST" class="mr-2">
										<input type="hidden" name="id" value="${entry.id}" />

										<!-- <hidden path="id" value ="${entry.id}" />  -->


										<select name="quantity" onchange=' this.form.submit(); '>
											<c:forEach var="i" begin="0" end="10" varStatus="loop">
												<c:choose>
													<c:when test="${entry.quantity == loop.index}">
														<option value="${loop.index}" selected>${loop.index}</option>
													</c:when>
													<c:otherwise>
														<option value="${loop.index}">${loop.index}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										<!-- Manually add tokens-->
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>

									<a class="text-muted pr-1 pl-1  align-self-start"
										href="${pageContext.request.contextPath}/shop/basket?del=${entry.product.id}"><small>Delete</small>
									</a>
									
									<p class=" d-lg-none ml-2 font-weight-bold""><c:out value="${entry.product.priceFormatted} €" /></p>
								</div>


							</div>

							<div class="col d-none d-lg-block ">
								
									<p class="font-weight-bold"><c:out value="${entry.product.priceFormatted} €" /></p>
								
							</div>


						</div>
					</c:forEach>


					<hr>
					<br>
					<p>Subtotal: ${totalLineItemsPrice} €</p>
					<p>Shipping Cost: ${shippingCost} €</p>
					<p>Total: ${totalOrderPrice} €</p>



					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/checkout" role="button">
						Go to checkout</a>


				</c:otherwise>
			</c:choose>

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