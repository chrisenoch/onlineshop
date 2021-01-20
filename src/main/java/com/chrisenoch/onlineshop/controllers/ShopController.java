package com.chrisenoch.onlineshop.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.OrderContentsService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	ProductService productService;
	OrderContentsService orderContentsService;
	OrderService orderService;
	
	@Autowired
	public ShopController(ProductService productService, OrderContentsService orderContentsService
			, OrderService orderService) {
		this.productService = productService;
		this.orderContentsService = orderContentsService;
		this.orderService = orderService;
	}

	@GetMapping
	public String displayProducts(HttpSession session, Model model
			, @RequestParam("id") Optional<Integer> id) 
					throws Exception{
		
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		int orderId = theOrder.getId();
		
		if(!id.isPresent()) { //If user has not just added product to basket

			//Send products so they can be displayed
			this.getAllProducts(model);
			
			//calculate how many order_contents there are
			int numberOfItemsInCart = orderContentsService.getOrderContents(theOrder).size();
					
			//add above information to model so can display basket count
			model.addAttribute("numberOfItemsInCart", numberOfItemsInCart);
			
			return "products";
		} else { //If user has added a product to basket. Add product to basket and display products
			
			int requestId = id.get();
			
			//Send products so they can be displayed
			getAllProducts(model);
	
			System.out.println("productId for basket received: " + id);
			
			//get product that is to be added to basket	
			Product theProduct = productService.getProduct(requestId);
			
			
			if(orderContentsService.checkIfInBasket(theProduct, theOrder)) {
				//redirect to checkout page with custom message
				//Do check using JSTL on checkout page to see if already in basket.
				//Add var to model here so it can be checked using jstl on checkout page
				
				return "already-in-basket";
			} else {
				//default quantity is 1. User can update quanity by viewing the basket.	
				OrderContents orderContents = new OrderContents(theOrder, theProduct, 1); 
				orderContentsService.save(orderContents);
			}
	
			
			//calculate how many order_contents there are
			int numberOfItemsInCart = orderContentsService.getOrderContents(theOrder).size();
					
			//add above information to model so can display basket count
			//model.addAttribute("numberOfItemsInCart", numberOfItemsInCart);

			return "redirect:/shop";
		}
		
		
	}

	
	
	@GetMapping("/product") 
	public String displayProduct(Model model,HttpSession session, @RequestParam("id") int id) {
		//Get user from database
		//User theUser = (User)session.getAttribute("user");
		
			//get product via request attribute
			Product theProduct = productService.getProduct(id);
			model.addAttribute("product", theProduct);
				
			//Need to display how many items(order_contents) in cart. Quantity decided at checkout.
			//So why have quantity in order_contents class? - Because this will be updated when quantity updated.
			//Can't add same product to basket.

			return "product";

	}
	
	
	private void getAllProducts(Model theModel) {
		List<Product> products = productService.getAllProducts();
		theModel.addAttribute("products", products);
	}
	


}
