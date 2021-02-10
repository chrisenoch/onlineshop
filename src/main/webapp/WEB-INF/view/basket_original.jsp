<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>Shopping Basket</title>
</head>

<body>

	<c:choose>
	    <c:when test="${(orderContents == 0 || orderContents == null) &&
	     (updatedOrderContentsDueToStockShortage == null)}">	
	        <h2>You currently have no items in your basket.</h2> 
	    </c:when>  
	    
	    
	    
	    <c:when test="${updatedOrderContentsDueToStockShortage != null && orderContents == 0 }">	
	        
			 <%-- Refactor code below--%>
			<h2>This is your shopping basket</h2>
			<hr>
	        <p style="color:red;font-weight: bold;">Unfortunately our stock levels have changed since you added the items to your basket.
			
			</p>
			
			<c:forEach var="item1" items="${updatedOrderContentsDueToStockShortage}">
			    <c:forEach var="item2" items="${item1.value}">
			    <ul>
			        <li>You requested <c:out value="${item2.key}" /> 
			        <c:if test="${item2.key > 1}">
			        <c:out value="${item1.key.name}s."/>
			        </c:if>
			        
			        <c:if test="${item2.key == 1}">
			        <c:out value="${item1.key.name}."/>
			        </c:if>
			        		        	   
			    </ul>
			    </c:forEach>
			    <c:if test="${fn:length(updatedOrderContentsDueToStockShortage) == 1}">This</c:if>
			        <c:if test="${fn:length(updatedOrderContentsDueToStockShortage) > 1}">These</c:if>  
			        has been removed from your basket. We apologise for the inconvenience.	
			</c:forEach>
	        <br><br>
	        
	        
	    </c:when> 
	    <c:otherwise>
	        <h2>This is your shopping basket</h2>
			<hr>
			
			<c:if test = "${updatedOrderContentsDueToStockShortage != null}">
			
			<p style="color:red;font-weight: bold;">Unfortunately our stock levels have changed since you added the items to your basket.
			We have suggested a change to your order to reflect our current stock levels:
			</p>
			
			<c:forEach var="item1" items="${updatedOrderContentsDueToStockShortage}">
			    <c:forEach var="item2" items="${item1.value}">
			    <ul>
			    
			    <li>You requested <c:out value="${item2.key}" /> 
			        <c:if test="${item2.key > 1}">
			        <c:out value="${item1.key.name}s."/>
			        </c:if>
			        
			        <c:if test="${item2.key == 1}">
			        <c:out value="${item1.key.name}."/>
			        </c:if>  
			        This has been changed to <c:out value="${item2.value}." /> 		   
			    </ul>
			    </c:forEach>
			</c:forEach>
			
			<form action="${pageContext.request.contextPath}/checkout">
		    <input type="submit" value="Continue to checkout"/>
			</form>
			</c:if>
			
			<table> 
			<thead>
				<tr>
					<th> </th>
					<th>Quantity</th>
					<th></th>
				</tr>
			</thead>
			
					<c:forEach var="entry" items= "${orderContentsList}">
					
						<tr>
							
							<td><c:out value="${entry.product.name}"/> </td>
							<td><a href="${pageContext.request.contextPath}/shop/basket?del=${entry.product.id}">Delete</a></td>
		
						</tr> 
						<tr>
							<td>${entry.product.description}</td> 
							<td><c:out value="${entry.quantity}"/></td>			
						</tr> 
						<tr>
						<td><c:out value="${entry.product.priceFormatted}"/></td> 
						</tr>
						
						<tr>
						<td>
						<form action="" method="POST">
						<input type="hidden"
									   name="id"
									   value="${entry.id}" />
									   
						<!-- <hidden path="id" value ="${entry.id}" />  -->
						
						
							<select name="quantity" onchange=' this.form.submit(); '>
								<c:forEach var="i" begin = "0" end = "10"  varStatus="loop">
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
		
								<input type="hidden"
									   name="${_csrf.parameterName}"
									   value="${_csrf.token}" />
						</form>
						</td>
						</tr>
						</c:forEach>
		
							
			</table>
			
		
		
			<hr>
			<br>
			<p>Subtotal: EUR ${totalOrderContentsPrice} €</p>
			<p>Shipping Cost: EUR ${shippingCost}</p>
			<p>Total: EUR ${totalOrderPrice}</p>
			
		
			
			
			<form action="${pageContext.request.contextPath}/checkout">
			
		    <input type="submit" value="Go to checkout"/>
			</form>
	
	
	    </c:otherwise>
	</c:choose>


	
	<a href="${pageContext.request.contextPath}/">Back to Profile Page</a>
	<br>
	<a href="${pageContext.request.contextPath}/shop/">Back to Shop</a>
	
	
	
</body>

</html>