    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class = "topnav">     
	   <nav>
			<ul> 		
			 <c:if test = "${currentPage != 'logout'}"><li><a href="#Login/Logout">Logout</a></li></c:if>
			 <c:if test = "${currentPage != 'checkout'}"><li><a href="#Checkout">Checkout</a></li></c:if>
			 <c:if test = "${currentPage != 'basket'}"><li><a href="#Basket">Basket</a></li></c:if>
			 <c:if test = "${currentPage != 'shop'}"><li><a href="${pageContext.request.contextPath}/shop">Shop</a></li></c:if>
			 <c:if test = "${currentPage != 'profilePage'}"><li><a href="#Profile Page">Profile Page</a></li></c:if>
			     
             <li><h1><i>Company name</i></h1></li>
          
			</ul>      	
	</nav> 
	</div>