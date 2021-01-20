<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<h2>Checkout</h2>

<c:choose> 
  <c:when test="${orderContents == 0 || orderContents == null}">	
      <p>You have no items in your basket.</p>
    	<a href="${pageContext.request.contextPath}/shop">Go to shop</a>
  </c:when>
  <c:otherwise>
	  <c:if test = "${address == null}">
		<h3>Delivery address:</h3>
		<p>You need to add/select an address</p>
		<a href="${pageContext.request.contextPath}/account/addresses?ch=<c:out value="1"/>">Add an address</a>
		</c:if>
	
		<c:if test = "${address != null}">
		<h3>Delivery address:</h3>
		<p>${address.fullName}</p>
		<p>${address.addressLine1}, ${address.addressLine2}</p>
		<p>${address.countyOrState}, ${address.postCode}</p>
		<p>${address.country}</p>
		
		<a href="${pageContext.request.contextPath}/account/addresses?ch=<c:out value="1"/>">Change address</a>
	
		<br><br>
		<form action='/funtestapp/charge' method='POST' id='checkout-form'>
		    <input type='hidden' value="${amount}" name='amount' />
		    <input type='hidden' value="${address.id}" name='addressId' />
		    <input type='hidden' value="${orderId}" name='orderId' />
		    <input type='hidden' value="${shippingCost}" name='shippingCost' />
		    <h3>Price:</h3>
		    <p>EUR ${amountFormatted}</p>
		    <!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
		    <script
		       src='https://checkout.stripe.com/checkout.js' 
		       class='stripe-button'
		       data-key="${stripePublicKey}" 
		         data-amount="${amount}" 
		         data-currency="${currency}"
		       data-name='Funtestapp'
		       data-description='Funtestapp checkout'
		       data-image
		         ='https://www.baeldung.com/wp-content/themes/baeldung/favicon/android-chrome-192x192.png'
		       data-locale='auto'
		       data-zip-code='false'>
		   </script>
		   
		   <input type="hidden"
									   name="${_csrf.parameterName}"
									   value="${_csrf.token}" />
		   
		</form>
		</c:if>
  
  </c:otherwise>
</c:choose>





</body>
</html>