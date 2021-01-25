package com.chrisenoch.onlineshop.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chrisenoch.onlineshop.dao.jpa.OrderDaoJPAImpl;
import com.chrisenoch.onlineshop.dao.jpa.PracticeDaoJPAImpl;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.User;

@Controller
@RequestMapping("/practice")
public class PracticeController {
	
	private OrderDaoJPAImpl pJPA;
	private PracticeDaoJPAImpl pracDao;
	
	@Autowired
	public PracticeController(OrderDaoJPAImpl pJPA, PracticeDaoJPAImpl pracDao) {;
		this.pJPA = pJPA;
		this.pracDao = pracDao;
	}
	
	@GetMapping("/savecheckout")
	public String savecheckout() {
		pracDao.save();
		return "profile-page";
	}
	
	@GetMapping("/savecheckoutandorder")
	public String savecheckoutandorder() {
		pracDao.saveCheckoutAndOrder();
		return "profile-page";
	}
	
	@GetMapping("/saveallcheckout")
	public String saveallcheckout() {
		pracDao.saveallcheckout();
		
		return "profile-page";
	}
	
	
	
//	@GetMapping("/jpa")
//	public String getOrdersJPA() {
//		List<Order> orders = pJPA.getAllOrdersJPA();
//		System.out.println("JPA: Print order ids");
//		orders.stream().forEach(a-> System.out.println(a.getId()));
//		return "profile-page";
//	}
//	
//	@GetMapping("/saveorderjpa")
//	public String saveOrderJPA(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		
//		Order order = new Order();
//		order.setProcessed(false);
//		order.setOrderDate(new Date());
//		order.setUser(user);
//		pJPA.save(order);
//		return "profile-page";
//	}
//	
//	@GetMapping("/deleteorderjpa")
//	public String deleteOrderJPA(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		
//		//get order
//		Order order = pJPA.getOrder(101);
//
//		//delete order
//		pJPA.delete(order);
//		
//		System.out.println("First order isProcessed: " + order.isProcessed());
//		
//		return "profile-page";
//	}
//	
//	@GetMapping("/mergeremovemerge")
//	public String mergeremovermergeJPA(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		
//		Order order = new Order();
//		order.setId(107);
//
//		Order order2 = pJPA.save(order);
//		System.out.println("Order id: " + order2.getId());
//
//		pJPA.delete(order2);
//		order2.setId(107);
//		
//		Order order3 =pJPA.save(order2);
//		System.out.println("Order id2: " + order2.getId());
//		System.out.println("Order id3: " + order3.getId());
//		
//		
//		return "profile-page";
//	}
//	
//	@GetMapping("/getunprocessedorderse")
//	public String getunproOrdersA(HttpSession session) {
//		int userId =  (int) session.getAttribute("userId");
//		List<Order> orders = pJPA.getUnprocessedOrders(userId);
//		System.out.println("unprocess orders");
//		orders.stream().forEach(a-> System.out.println(a.getId()));
//		
//		
//		return "profile-page";
//	}
//	
//	@GetMapping("/saveorupdateorderjpa")
//	public String saveOrUpdateOrderJPA(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		
//		//get order
//		Order order = pJPA.getOrder(101);
//
//		//save same order
//		pJPA.save(order);
//		
//		//update order	
//		order.setProcessed(true);
//		Order updatedOrder = pJPA.save(order);
//		
//		System.out.println("First order isProcessed: " + order.isProcessed());
//		System.out.println("Updated order isProcessed: " + updatedOrder.isProcessed());
//		
//		return "profile-page";
//	}

}
