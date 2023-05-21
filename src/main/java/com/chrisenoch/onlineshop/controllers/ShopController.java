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
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.LineItemService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	ProductService productService;
	LineItemService lineItemService;
	OrderService orderService;
	
	@Autowired
	public ShopController(ProductService productService, LineItemService lineItemService
			, OrderService orderService) {
		this.productService = productService;
		this.lineItemService = lineItemService;
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
			int numberOfItemsInCart = lineItemService.getLineItems(theOrder).size();
					
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
			if(lineItemService.checkIfInBasket(theProduct, theOrder)) {

				return "already-in-basket";
			} else {
				//default quantity is 1. User can update quantity by viewing the basket.	
				LineItem lineItem = new LineItem(theOrder, theProduct, 1); 
				lineItemService.save(lineItem);
			}

			return "redirect:/shop";
		}
		
		
	}

	@GetMapping("/product") 
	public String displayProduct(Model model,HttpSession session, @RequestParam("id") Optional<Integer> id) {
			if (id.isPresent()) {	
				Product theProduct = productService.getProduct(id.get());
				model.addAttribute("product", theProduct);
				return "product";
			} else {
				System.out.println("debugging - in else statement");
				return "redirect:/shop";
			}
	}
	
	
	private void getAllProducts(Model theModel) {
		List<Product> products = productService.getAllProducts();
		theModel.addAttribute("products", products);
	}
	


}
