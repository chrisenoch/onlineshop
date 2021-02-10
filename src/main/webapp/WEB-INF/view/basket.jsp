<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
<link href="https://fonts.googleapis.com/css2?family=Inter&display=swap" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic' rel='stylesheet' type='text/css'>
<link rel="preconnect" href="https://fonts.gstatic.com">
	<title>SYSTEMS Home Page</title>
</head>
<body>

<header>  
    <div class = "topnav">     
	   <nav>
			<ul> 
                <li> <a href="#LAbout us">About us</a></li>
                <li><a href="#Login/Logout">Login/Logout</a></li>
                <li><a href="#Basket">Basket</a></li>
                <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                <li><a href="#Profile Page">Profile Page</a></li>
                
                
                  <li><h1><i>Company name</i></h1></li>
               
              
                
			</ul>      	
	</nav> 
	</div>
    
    


</header>
<div class = "main-body">



<c:choose>
	    <c:when test="${(orderContents == 0 || orderContents == null) &&
	     (updatedOrderContentsDueToStockShortage == null)}">	
	        <h2>You currently have no items in your basket.</h2> 
	    </c:when>  
	    
	    
	    
	    <c:when test="${updatedOrderContentsDueToStockShortage != null && orderContents == 0 }">	
	        
			 <%-- Refactor code below--%>
			<h2>Shopping basket</h2>
			
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
	        <h2>Shopping basket</h2>
			
			
			<c:if test = "${updatedOrderContentsDueToStockShortage != null}">
			
			<p style="color:red;">Unfortunately our stock levels have changed since you added the items to your basket.
			<br>
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
			
			<form action="${pageContext.request.contextPath}/checkout" class="action-button">
		    <input type="submit" value="Continue to checkout"/>
			</form>
			<br>
			<hr>
			</c:if>
			
			
					<c:forEach var="entry" items= "${orderContentsList}">
					
					<div class = "clearfix">
							
						
						
						<img class = "product-image" src="<c:url value="${entry.product.pictureUrl}"></c:url>"
						alt="image" />
						
						<p class="product-name"><c:out value="${entry.product.name}"/> </p>
	
						<div class ="description-small">${entry.product.description}</div> 		

						<p class="product-price"><c:out value="${entry.product.priceFormatted}"/></p> 

						<form action="" method="POST">
						<input type="hidden"
									   name="id"
									   value="${entry.id}" />
									   
						<!-- <hidden path="id" value ="${entry.id}" />  -->
						
						
						<select name="quantity" class="select-css" onchange=' this.form.submit(); '>
							<c:forEach var="i" begin = "0" end = "10"  varStatus="loop">
								<c:choose> 
								  <c:when test="${entry.quantity == loop.index}">
								    <option value="${loop.index}" selected>&nbsp;&nbsp;&nbsp;${loop.index}</option>
								  </c:when>
								  <c:otherwise>
								    <option value="${loop.index}">&nbsp;&nbsp;&nbsp;${loop.index}</option>
								  </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>

						
						
						<!-- Manually add tokens-->
		
								<input type="hidden"
									   name="${_csrf.parameterName}"
									   value="${_csrf.token}" />
						</form>
						
						<br>
						
						<a class="clearfixx" href="${pageContext.request.contextPath}/shop/basket?del=${entry.product.id}">Delete</a>
						
						</div>
						<hr>
						

						</c:forEach>
		
		
			<br>
			<p>Subtotal: EUR ${totalOrderContentsPrice}</p>
			<p>Shipping Cost: EUR ${shippingCost}</p>
			<p>Total: EUR ${totalOrderPrice}</p>
			
	
			
			<form action="${pageContext.request.contextPath}/checkout" class="action-button">		
		    <input type="submit" value="Go to checkout"/>
			</form>
	
	
	    </c:otherwise>
	</c:choose>
</div>




</body>

</html>