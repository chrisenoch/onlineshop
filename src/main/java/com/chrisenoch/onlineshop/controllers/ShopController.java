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
		
		if(!id.isPresent()) { //If user has not just added product to basket

			//Send products so they can be displayed
			this.getAllProducts(model);
			
			//calculate how many order_contents there are
			int numberOfItemsInCart = orderContentsService.getOrderContents(theOrder).size();
					
			//add above information to model so can display basket count
			model.addAttribute("numberOfItemsInCart", numberOfItemsInCart);
			
			return "products";
		} else { //If user has added a product to basket, add product to basket and display products
			
			int requestId = id.get();
			
			//Send products so they can be displayed
			getAllProducts(model);
			
			//get product that is to be added to basket	
			Product theProduct = productService.getProduct(requestId);
			
			//If already in basket...
			if(orderContentsService.checkIfInBasket(theProduct, theOrder)) {

				return "already-in-basket";
			} else {
				//default quantity is 1. User can update quantity by viewing the basket.	
				OrderContents orderContents = new OrderContents(theOrder, theProduct, 1); 
				orderContentsService.save(orderContents);
			}

			return "redirect:/shop";
		}
		
		
	}

	
	
	@GetMapping("/product") 
	public String displayProduct(Model model,HttpSession session, @RequestParam("id") int id) {
			Product theProduct = productService.getProduct(id);
			model.addAttribute("product", theProduct);

			return "product";

	}
	
	
	private void getAllProducts(Model theModel) {
		List<Product> products = productService.getAllProducts();
		theModel.addAttribute("products", products);
	}
	


}
