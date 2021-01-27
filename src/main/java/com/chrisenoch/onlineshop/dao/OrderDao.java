package com.chrisenoch.onlineshop.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.User;

 public interface OrderDao {
	
	 void save(Order theOrder);
	 void delete(Order theOrder);
	 List<Order> getAllOrders(int userId);
	 List<Order>getUnprocessedOrders(int userId);
	 Order getOrder(int orderId);

}
