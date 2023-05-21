 package com.chrisenoch.onlineshop.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/shop/basket")
public class BasketController {
	
	private LineItemService lineItemService;
	private OrderService orderService;
	private ProductService productService;

	@Autowired
	public BasketController(LineItemService lineItemService, OrderService orderService
			, ProductService productDaoService) {
		this.lineItemService = lineItemService;
		this.orderService = orderService;
		this.productService = productDaoService;
	}
	 

	@GetMapping 
	public String displayBasket(Model model, HttpSession session
			, @RequestParam("del") Optional<Integer> productId) throws Exception {
		
		//If del present, delete lineItem from Order
		if(productId.isPresent()) {
			
			Product theProduct = productService.getProduct(productId.get());

			lineItemService.delete(theProduct);
			
		} 
		
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		double shippingCost = theOrder.shippingCost(theOrder.getLineItemsTotal());
		//Get Order from session - If user visits basket page before shop page, there may not be an order session available
		double totalLineItemsPriceInCents = lineItemService.totalLineItemsPrice(theOrder); 
		double totalLineItemsPrice = totalLineItemsPriceInCents/100;
		double totalOrderPrice = totalLineItemsPrice + shippingCost;
		
		String shippingCostFormatted = String.format("%.2f", shippingCost);
		String totalLineItemsPriceFormatted = String.format("%.2f", totalLineItemsPrice);
		String totalOrderPriceFormatted = String.format("%.2f", totalOrderPrice);

		model.addAttribute("totalLineItemsPrice", totalLineItemsPriceFormatted);
		model.addAttribute("shippingCost", shippingCostFormatted);
		model.addAttribute("totalOrderPrice", totalOrderPriceFormatted);
		model.addAttribute("lineItems", theOrder.getLineItems().size()); //If null, user gets a message saying basket is empty in checkout.jsp
		
		List<LineItem> lineItemsList = lineItemService.getLineItems(theOrder); //Don't get via Order because need to have control of the order in which the database records are fetched.
		Collections.reverse(lineItemsList);
		model.addAttribute("lineItemsList", lineItemsList);

		return "basket";
	}
	
	@PostMapping
	public String updateQuantity(HttpServletRequest request, HttpSession session) throws Exception {
		
		String idString = request.getParameter("id");
		String quantityString = request.getParameter("quantity");		
		int id = Integer.parseInt(idString);
		int newQuantity = Integer.parseInt(quantityString);

		LineItem lineItem = lineItemService.getLineItem(id);
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		if (newQuantity < 1) {
			//delete from database
			
			theOrder.getLineItems().remove(lineItem); //remove association
			lineItemService.delete(lineItem);
		} else {
			
			//update quantity
			lineItem.setQuantity(newQuantity);
			
			//save in database
			lineItemService.save(lineItem);
			
		
		}

		return "redirect:/shop/basket";
	}

}
