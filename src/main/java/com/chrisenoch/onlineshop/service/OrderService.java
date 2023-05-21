package com.chrisenoch.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.User;

public interface OrderService {
	
	public void save(Order theOrder);
	public void delete(Order theOrder);
	public List<Order> getAllOrders(int userId);
	public List<Order> getUnprocessedOrders(int userId);
	public Order getCorrectOrder(User theUser, int userId) throws Exception;
	public Order getOrder(int orderId);

}
