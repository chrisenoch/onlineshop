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
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.OrderContentsService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;


@Controller
@RequestMapping("/shop/basket")
public class BasketController {
	
	private OrderContentsService orderContentsService;
	private OrderService orderService;
	private ProductService productService;

	@Autowired
	public BasketController(OrderContentsService orderContentsService, OrderService orderService
			, ProductService productDaoService) {
		this.orderContentsService = orderContentsService;
		this.orderService = orderService;
		this.productService = productDaoService;
	}
	 

	@GetMapping 
	public String displayBasket(Model model, HttpSession session
			, @RequestParam("del") Optional<Integer> productId) throws Exception {
		
		//If del present, delete order_contents from order
		if(productId.isPresent()) {
			
			Product theProduct = productService.getProduct(productId.get());

			orderContentsService.delete(theProduct);
			
		} 
		
		
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		double shippingCost = theOrder.shippingCost(theOrder.getOrderContentsTotal());
		//Get order id from session - If user visits basket page before shop page, there may not be an order session available
		int orderId = theOrder.getId();
		double totalOrderContentsPriceInCents = orderService.totalOrderContentsPrice(orderId); 
		double totalOrderContentsPrice = totalOrderContentsPriceInCents/100;
		double totalOrderPrice = totalOrderContentsPrice + shippingCost;
		
		String shippingCostFormatted = String.format("%.2f", shippingCost);
		String totalOrderContentsPriceFormatted = String.format("%.2f", totalOrderContentsPrice);
		String totalOrderPriceFormatted = String.format("%.2f", totalOrderPrice);

		model.addAttribute("totalOrderContentsPrice", totalOrderContentsPriceFormatted);
		model.addAttribute("shippingCost", shippingCostFormatted);
		model.addAttribute("totalOrderPrice", totalOrderPriceFormatted);
		model.addAttribute("orderContents", theOrder.getOrderContents().size()); //If null, user gets a message saying basket is empty in checkout.jsp
		
		List<OrderContents> orderContentsList = orderContentsService.getOrderContents(theOrder); //Don't get via Order because need to have control of the order in which the dataabse records are fetched.
		Collections.reverse(orderContentsList);
		model.addAttribute("orderContentsList", orderContentsList);

		return "basket";
	}
	
	@PostMapping
	public String updateQuantity(HttpServletRequest request, HttpSession session) throws Exception {
		
		String idString = request.getParameter("id");
		String quantityString = request.getParameter("quantity");		
		int id = Integer.parseInt(idString);
		int newQuantity = Integer.parseInt(quantityString);

		OrderContents orderContents = orderContentsService.getOrderContent(id);
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		if (newQuantity < 1) {
			//delete from database
			
			theOrder.getOrderContents().remove(orderContents); //remove association
			orderContentsService.delete(orderContents);
		} else {
			
			//update quantity
			orderContents.setQuantity(newQuantity);
			
			//save in database
			orderContentsService.save(orderContents);
			
		
		}

		return "redirect:/shop/basket";
	}

}
